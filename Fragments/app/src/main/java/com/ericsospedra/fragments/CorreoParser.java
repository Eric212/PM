package com.ericsospedra.fragments;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CorreoParser {
    private final Context context;
    private ArrayList<Correo> correos;

    public CorreoParser(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<Correo> getCorreos() {
        return correos;
    }

    public void setCorreos(ArrayList<Correo> correos) {
        this.correos = correos;
    }

    public boolean parser() {
        try {
            InputStream datos = getContext().getResources().openRawResource(R.raw.correos);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(datos);
            Element root = document.getDocumentElement();
            NodeList items = root.getElementsByTagName("correo");
            ArrayList<Correo> correos = new ArrayList<>();
            for (int i = 0; i < items.getLength(); i++) {
                Node item = items.item(i);
                NodeList correo = item.getChildNodes();
                correos.add( new Correo(correo.item(1).getTextContent(), correo.item(3).getTextContent()));
            }
            setCorreos(correos);
            return true;

        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.err.println("Error");
            return false;
        }
    }
}
