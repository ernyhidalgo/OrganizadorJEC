package company.viral.organizadorjec.FracmentPopUp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import company.viral.organizadorjec.R;
import company.viral.organizadorjec.Clases.SQLite;


public class ConfiguracionProfesorF extends Fragment {

    private EditText etnombreprofesorf, etcomentarioprofesorf;
    private Button btnagregarpf;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflamos el fragment
        final View view = inflater.inflate(R.layout.fragment_configuracion_profesor, container, false);
        //codigo.....................
        //iniciamos la variables
        etnombreprofesorf = (EditText)view.findViewById(R.id.etnombreprofesor);
        etcomentarioprofesorf = (EditText)view.findViewById(R.id.etcomentarioprofesor);
        //boton
        btnagregarpf = (Button)view.findViewById(R.id.btnagregarprofesor);

        //capturamos el usuario desde la activity
        Bundle bundle=getActivity().getIntent().getExtras();
        final int identificar = bundle.getInt("identificador");
        //inicializamos la bd
        SQLite admin = new SQLite(getContext(),"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        //creamos los cursores para recorrido y almacenamiento de la info de la bd
        Cursor caracteristicas;
        //capturamos la informacion que necesitamos para los spiner
        caracteristicas=bd.rawQuery("select id, nombre from caracteristicas where id_usuario='"+identificar+"'",null);
        //creamos los arreglos para el llenado de los spinner con la info de los contenedores
        String[]listacaracteristica = new String[caracteristicas.getCount()];
        //ordenamos la info
        int organizador=0;
        while (caracteristicas.moveToNext()){
            String contenedorcaracteristica=caracteristicas.getString(caracteristicas.getColumnIndex("nombre"));
            listacaracteristica[organizador]=contenedorcaracteristica;
            organizador++;
        }
        //instanciamos los spiner y cargamos los datos
        //spiner caracteristica1
        final Spinner caracteristica = (Spinner)view.findViewById(R.id.spincaracteristica1);
        ArrayAdapter<String> listavistacaracteristica=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,listacaracteristica);
        caracteristica.setAdapter(listavistacaracteristica);
        //spiner caracteristica2
        final Spinner caracteristica2 = (Spinner)view.findViewById(R.id.spincaracteristica2);
        ArrayAdapter<String> listavistacaracteristica2=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,listacaracteristica);
        caracteristica2.setAdapter(listavistacaracteristica2);

        //metodo para almacenar la info con el boton
        btnagregarpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLite admin = new SQLite(getContext(),"administracion",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                //inicializamos las variables necesarias

                String auxnp = etnombreprofesorf.getText().toString();
                String auxcp = etcomentarioprofesorf.getText().toString();

                int auxidp = 0,auxidp2=0,carac1 = 0,carac2=0;

                //cursor...
                Cursor idcaracteristica,idcaracteristica2,idprofe;
                //creamos un contenedor para cargar
                ContentValues registroprofe = new ContentValues();
                ContentValues caracteristicaprofe = new ContentValues();
                //cargamos en el contenedor
                registroprofe.put("nombre",auxnp);
                registroprofe.put("detalle",auxcp);
                registroprofe.put("id_usuario",identificar);

                bd.insert("profesores",null,registroprofe);


                //cursorllenado
                idprofe=bd.rawQuery("select id from profesores where nombre= '"+auxnp+"'",null);
                if (idprofe.moveToFirst()==true){
                    auxidp = idprofe.getInt(0);

                }
                idcaracteristica=bd.rawQuery("select id from caracteristicas where nombre= '"+caracteristica.getSelectedItem().toString()+"'",null);

                if (idcaracteristica.moveToFirst()==true){
                    carac1=idcaracteristica.getInt(0);
                }
                idcaracteristica2=bd.rawQuery("select id from caracteristicas where nombre= '"+caracteristica2.getSelectedItem().toString()+"'",null);

                if (idcaracteristica2.moveToFirst()==true){
                    carac2=idcaracteristica2.getInt(0);
                }



                caracteristicaprofe.put("id_profesor",auxidp);
                caracteristicaprofe.put("id_caracteristica",carac1);

                bd.insert("caracteristica_profesor",null,caracteristicaprofe);


                caracteristicaprofe.put("id_profesor",auxidp);
                caracteristicaprofe.put("id_caracteristica",carac2);

                bd.insert("caracteristica_profesor",null,caracteristicaprofe);


                etnombreprofesorf.setText("");
                etcomentarioprofesorf.setText("");

                //Toast.makeText(getContext(), "Profesor anexado", Toast.LENGTH_LONG).show();
                bd.close();
            }
        });
        return view;
    }




}







