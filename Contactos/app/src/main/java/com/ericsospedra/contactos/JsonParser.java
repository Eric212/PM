package com.ericsospedra.contactos;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class JsonParser {
    private final Context context;
    private ArrayList<Contacto> contactos;

    public JsonParser(Context context) {
        this.context = context;
    }

    public boolean parsear(){
        contactos = null;
        InputStream file = getContext().getResources().openRawResource(R.raw.contacts);
        String jsonContactos = null;
        try{
            int sizeContactos = file.available();
            byte[] bufferContactos = new byte[sizeContactos];
            file.read(bufferContactos);
            jsonContactos = new String(bufferContactos, StandardCharsets.UTF_8);
            JSONTokener tokener = new JSONTokener(jsonContactos);
            JSONArray jsonArray = new JSONArray(tokener);
            contactos = new ArrayList<>(jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                contactos.add(new Contacto(jsonObject.getInt("id"),jsonObject.getString("name"),
                        jsonObject.getString("firstSurname")+" "+jsonObject.getString("secondSurname"),
                        jsonObject.getString("birth"),
                        jsonObject.getString("company"),jsonObject.getString("email"),
                        jsonObject.getString("phone1"),jsonObject.getString("phone2"),jsonObject.getString("address")));
            }
            return true;
        } catch (IOException | JSONException e) {
            return false;
        }
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(ArrayList<Contacto> contactos) {
        this.contactos = contactos;
    }
}
