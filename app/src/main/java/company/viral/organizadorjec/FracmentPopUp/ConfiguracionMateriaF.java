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
import android.widget.Toast;

import company.viral.organizadorjec.R;
import company.viral.organizadorjec.Clases.SQLite;


public class ConfiguracionMateriaF extends Fragment {

    private EditText etmateriaf,etcomentariomateriaf;
    private Button btnagregarmateriaf;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_configuracion_materia, container, false);
        //inicializamos las variables
        etmateriaf = (EditText)view.findViewById(R.id.etnombremateria);
        etcomentariomateriaf = (EditText)view.findViewById(R.id.etcomentariomateria);
        //boton
        btnagregarmateriaf = (Button)view.findViewById(R.id.btnagregarmateriaf);

        //capturamos el usuario desde la activity
        Bundle bundle=getActivity().getIntent().getExtras();
        final int identificar = bundle.getInt("identificador");
        //inicializamos la bd
        SQLite admin = new SQLite(getContext(),"administracion",null,1);
        final SQLiteDatabase bd = admin.getWritableDatabase();
        //creamos los cursores para recorrido y almacenamiento de la info de la bd
        Cursor profesores,periodos;
        //capturamos la informacion que necesitamos para los spiner
        profesores=bd.rawQuery("select id, nombre from profesores where id_usuario='"+identificar+"'",null);
        periodos=bd.rawQuery("select id, nombre from periodo where id_usuario='"+identificar+"'",null);
        //creamos los arreglos para el llenado de los spinner con la info de los contenedores
        String[] listaprofesores = new String[profesores.getCount()];
        String[] listaperiodos = new String[periodos.getCount()];
        //ordenamos la info de los string
        // (profesor)
        int organizador1=0;
        while (profesores.moveToNext()){
            String contenedorprofesor = profesores.getString(profesores.getColumnIndex("nombre"));
            listaprofesores[organizador1]=contenedorprofesor;
            organizador1++;
        }
        // (periodo)
        int organizador2=0;
        while (periodos.moveToNext()){
            String contenerdorperiodo = periodos.getString(periodos.getColumnIndex("nombre"));
            listaperiodos[organizador2]=contenerdorperiodo;
            organizador2++;
        }
        //instanciamos el spinner de profesores y cargamos
        final Spinner profesor = (Spinner)view.findViewById(R.id.spiprofe);
        ArrayAdapter<String> listavistaprofe = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,listaprofesores);
        profesor.setAdapter(listavistaprofe);
        //instanciamos el spinner de periodo y cargamos
        final Spinner periodo = (Spinner)view.findViewById(R.id.spientidad);
        ArrayAdapter<String> listavistaperiodo = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,listaperiodos);
        periodo.setAdapter(listavistaperiodo);



        //onclick de aceptar botton
        btnagregarmateriaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLite admin = new SQLite(getContext(),"administracion",null,1);
                final SQLiteDatabase bd = admin.getWritableDatabase();
                //cursor para guardar profesores y periodos
                Cursor idprofe,idperiodo;
                //enteros para guardar los valores de los cursores
                int idprof=0,idperi=0;
                idprofe=bd.rawQuery("select id from profesores where nombre= '"+profesor.getSelectedItem().toString()+"'",null);
                idperiodo=bd.rawQuery("select id from periodo where nombre= '"+periodo.getSelectedItem().toString()+"'",null);
                //guardamos los id encontrados
                if (idperiodo.moveToFirst()==true){
                    idperi=idperiodo.getInt(0);
                }
                if (idprofe.moveToFirst()==true){
                    idprof=idprofe.getInt(0);
                }
                //creamos un contenedor para el llenado
                ContentValues registromate = new ContentValues();
                //variables auxiliares para el llenado de la base de datos
                String auxnm = etmateriaf.getText().toString();
                String auxcm = etcomentariomateriaf.getText().toString();
                //cargamos el contenerdor con las variables e insertamos la informacion
                registromate.put("nombre",auxnm);
                registromate.put("detalle",auxcm);
                registromate.put("id_usuario",identificar);
                registromate.put("id_profesor",idprof);
                registromate.put("id_periodo",idperi);
                bd.insert("materias",null,registromate);

                //para demostrar la llenada limpiamos los campos y avisamos al usuario
                etmateriaf.setText("");
                etcomentariomateriaf.setText("");
                Toast.makeText(getContext(), "Materia anexada", Toast.LENGTH_LONG).show();
                //cerramos la bd
                bd.close();
            }
        });
        return view;
    }



}







