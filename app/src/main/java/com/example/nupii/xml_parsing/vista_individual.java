package com.example.nupii.xml_parsing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class vista_individual extends Activity {
    static final String ID_ALUMNO = "id_alumno";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_APELLIDO_P = "apellido_p";
    static final String KEY_APELLIDO_M = "apellido_m";
    static final String KEY_CARRERA = "carrera";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_individual);
        Intent in = getIntent();

        // Get XML values from previous intent
        String id_alumno = in.getStringExtra(ID_ALUMNO);
        String nombre = in.getStringExtra(KEY_NOMBRE);
        String apellido_p = in.getStringExtra(KEY_APELLIDO_P);
        String apellido_m = in.getStringExtra(KEY_APELLIDO_M);
        String carrera = in.getStringExtra(KEY_CARRERA);

        TextView lblid_alumno = (TextView) findViewById(R.id.txt_id_alumno);
        TextView lblnombre = (TextView) findViewById(R.id.txt_nombre);
        TextView lblapellido_p = (TextView) findViewById(R.id.txt_apellido_p);
        TextView lblapellido_m = (TextView) findViewById(R.id.txt_apellido_m);
        TextView lblcarrera = (TextView) findViewById(R.id.txt_carrera);

        lblid_alumno.setText(id_alumno);
        lblnombre.setText(nombre);
        lblapellido_p.setText(apellido_p);
        lblapellido_m.setText(apellido_m);
        lblcarrera.setText(carrera);
    }

}