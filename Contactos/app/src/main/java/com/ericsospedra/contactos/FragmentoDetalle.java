package com.ericsospedra.contactos;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentoDetalle extends Fragment {
    private String nombre;
    private String apellidos;
    private String birthday;
    private String empresa;
    private String email;
    private String telefono1;
    private String telefono2;
    private String direccion;
    private TextView tvNombre;
    private TextView tvApellidos;
    private TextView tvBirthday;
    private TextView tvEmpresa;
    private TextView tvEmail;
    private TextView tvTelefono1;
    private TextView tvTelefono2;
    private TextView tvDireccion;
public interface IonAttach{
    String[] getDatos();
}
    public FragmentoDetalle() {
        super(R.layout.fragmento_detalle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.tvNombre = view.findViewById(R.id.tvNombreInput);
        this.tvApellidos = view.findViewById(R.id.tvApellidosInput);
        this.tvBirthday = view.findViewById(R.id.tvFechaNacimiento);
        this.tvEmpresa = view.findViewById(R.id.tvEmpresaInput);
        this.tvEmail = view.findViewById(R.id.tvEmailInput);
        this.tvTelefono1 = view.findViewById(R.id.tvTelefono1Input);
        this.tvTelefono2 = view.findViewById(R.id.tvTelefono2Input);
        this.tvDireccion = view.findViewById(R.id.tvDireccionInput);
        mostrarContacto(nombre,apellidos,birthday,empresa,email,telefono1,telefono2,direccion);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IonAttach ionAttach = (IonAttach) context;
        String[] datos = ionAttach.getDatos();
        this.nombre = datos[0];
        this.apellidos = datos[1];
        this.birthday = datos[2];
        this.empresa = datos[3];
        this.email = datos[4];
        this.telefono1 = datos[5];
        this.telefono2 = datos[6];
        this.direccion = datos[7];
    }

    public void mostrarContacto(String nombre, String apellidos, String birthday, String empresa, String email, String telefono1, String telefono2, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.birthday = birthday;
        this.empresa = empresa;
        this.email = email;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.direccion = direccion;
        this.tvNombre.setText(nombre);
        this.tvApellidos.setText(apellidos);
        this.tvBirthday.setText(birthday);
        this.tvEmpresa.setText(empresa);
        this.tvEmail.setText(email);
        this.tvTelefono1.setText(telefono1);
        this.tvTelefono2.setText(telefono2);
        this.tvDireccion.setText(direccion);
    }
}
