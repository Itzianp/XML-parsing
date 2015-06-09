package com.example.nupii.xml_parsing;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class MainActivity extends ListActivity {
    static final String URL = "http://resources.260mb.net/bbdd-it.xml";
    // XML node keys
    static final String ALUMNO = "alumno"; // parent node
    static final String ID_ALUMNO = "id_alumno";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_APELLIDO_P = "apellido_p";
    static final String KEY_APELLIDO_M = "apellido_m";
    static final String KEY_CARRERA = "carrera";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

        XMLParse parser = new XMLParse();
        String xml = parser.getXmlFromUrl(URL); // getting XML
        Document doc = parser.getDomElement(xml); // getting DOM element

        NodeList nl = doc.getElementsByTagName(ALUMNO);

        for (int i = 0; i < nl.getLength(); i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // adding each child node to HashMap key => value
            map.put(ID_ALUMNO, "ID: " + parser.getValue(e, ID_ALUMNO));
            map.put(KEY_NOMBRE, "Nombre: " + parser.getValue(e, KEY_NOMBRE));
            map.put(KEY_APELLIDO_P, "Apellido Paterno: " + parser.getValue(e, KEY_APELLIDO_P));
            map.put(KEY_APELLIDO_M, "Apellido Materno: " + parser.getValue(e, KEY_APELLIDO_M));
            map.put(KEY_CARRERA, "Carrera: " + parser.getValue(e, KEY_CARRERA));

            // adding HashList to ArrayList
            menuItems.add(map);

        }
        ListAdapter adapter = new SimpleAdapter(this, menuItems,
                R.layout.lista_usuarios,
                new String[]{ID_ALUMNO, KEY_NOMBRE, KEY_APELLIDO_P, KEY_APELLIDO_M, KEY_CARRERA}, new int[]{
                R.id.txt_id_alumno, R.id.txt_nombre,R.id.apellido_p, R.id.apellido_m, R.id.carrera});

        setListAdapter(adapter);
        ListView lv = getListView();
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String txt_id_alum = ((TextView) view.findViewById(R.id.txt_id_alumno)).getText().toString();
                String txt_key_nom = ((TextView) view.findViewById(R.id.txt_nombre)).getText().toString();
                String apellidop = ((TextView) view.findViewById(R.id.apellido_p)).getText().toString();
                String apellidom = ((TextView) view.findViewById(R.id.apellido_m)).getText().toString();
                String carrera = ((TextView) view.findViewById(R.id.carrera)).getText().toString();


                // Starting new intent
                Intent in = new Intent(getApplicationContext(), vista_individual.class);
                in.putExtra(ID_ALUMNO, txt_id_alum);
                in.putExtra(KEY_NOMBRE, txt_key_nom);
                in.putExtra(KEY_APELLIDO_P, apellidop);
                in.putExtra(KEY_APELLIDO_M, apellidom);
                in.putExtra(KEY_CARRERA, carrera);
                startActivity(in);

            }
        });
    }
}
