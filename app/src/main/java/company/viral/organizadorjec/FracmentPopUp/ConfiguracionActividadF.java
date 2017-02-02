package company.viral.organizadorjec.FracmentPopUp;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import company.viral.organizadorjec.Clases.SQLite;
import company.viral.organizadorjec.R;


public class ConfiguracionActividadF extends Fragment {

    private EditText etcomentarioactividadf;
    private Button btnagregaractividadf,btnagregarfecha;
    private TextView fechaentrega;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_configuracion_actividad, container, false);
        //inicializamos las variables

        fechaentrega=(TextView)view.findViewById(R.id.fechaentrega);
        etcomentarioactividadf = (EditText)view.findViewById(R.id.etcomentarioactividad);

        //boton
        btnagregaractividadf = (Button)view.findViewById(R.id.btnagregaractividad);
        btnagregarfecha = (Button)view.findViewById(R.id.btnfragfecha);

        //capturamos el usuario desde la activity
        Bundle bundle=getActivity().getIntent().getExtras();
        final int identificar = bundle.getInt("identificador");
        //inicializamos la bd
        SQLite admin = new SQLite(getContext(),"administracion",null,1);
        final SQLiteDatabase bd = admin.getWritableDatabase();
        //creamos los cursores para recorrido y almacenamiento de la info de la bd
        Cursor materias,tareas;
        //capturamos la informacion que necesitamos para los spiner
        materias=bd.rawQuery("select id, nombre from materias where id_usuario='"+identificar+"'",null);
        tareas=bd.rawQuery("select id, nombre from tarea where id_usuario='"+identificar+"'",null);
        //creamos los arreglos para el llenado de los spinner con la info de los contenedores
        String[] listamaterias = new String[materias.getCount()];
        String[] listatareas = new String[tareas.getCount()];
        //ordenamos la info de los string
        // (materia)
        int organizador1=0;
        while (materias.moveToNext()){
            String contenedormateria = materias.getString(materias.getColumnIndex("nombre"));
            listamaterias[organizador1]=contenedormateria;
            organizador1++;
        }
        // (tarea)
        int organizador2=0;
        while (tareas.moveToNext()){
            String contenerdortareas = tareas.getString(tareas.getColumnIndex("nombre"));
            listatareas[organizador2]=contenerdortareas;
            organizador2++;
        }
        //instanciamos el spinner de profesores y cargamos
        final Spinner materiaS = (Spinner)view.findViewById(R.id.spimateria);
        ArrayAdapter<String> listavistmateria = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,listamaterias);
        materiaS.setAdapter(listavistmateria);
        //instanciamos el spinner de periodo y cargamos
        final Spinner tarea = (Spinner)view.findViewById(R.id.spitarea);
        ArrayAdapter<String> listavistatarea = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,listatareas);
        tarea.setAdapter(listavistatarea);

        bd.close();


        btnagregarfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);

                fechaentrega.setText("");
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        String mejordia=String.valueOf(dayOfMonth);
                        String mejormes=String.valueOf(monthOfYear+1);

                        if (dayOfMonth<10){
                            mejordia=String.valueOf("0"+dayOfMonth);
                        }

                        if ((monthOfYear+1)<10){
                            mejormes=String.valueOf("0"+(monthOfYear+1));
                        }


                        fechaentrega.setText(mejordia + "-" + mejormes + "-" + year);

                    }
                }, year, month, day);
                datePickerDialog.setTitle("Seleccione la Fecha");
                datePickerDialog.show();
            }
        });



        //onclick de aceptar botton
        btnagregaractividadf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle=getActivity().getIntent().getExtras();
                int identificar = bundle.getInt("identificador");

                String fechamateria = fechaentrega.getText().toString();
                String comentario = etcomentarioactividadf.toString();

                SQLite admin = new SQLite(getContext(),"administracion",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();


                //cursor para guardar profesores y periodos
                Cursor idmateria,idtarea;
                //enteros para guardar los valores de los cursores
                int materiaid=0;
                String tareaid="";
                idmateria=bd.rawQuery("select id from materias where nombre= '"+materiaS.getSelectedItem().toString()+"'",null);
                idtarea=bd.rawQuery("select nombre from tarea where nombre= '"+tarea.getSelectedItem().toString()+"'",null);
                //guardamos los id encontrados
                if (idtarea.moveToFirst()==true){
                    tareaid=idtarea.getString(0);
                }
                if (idmateria.moveToFirst()==true){
                    materiaid=idmateria.getInt(0);
                }


                ContentValues registromateria = new ContentValues();

                registromateria.put("fecha",fechamateria);
                registromateria.put("id_usuario",identificar);
                registromateria.put("detalle",comentario);
                registromateria.put("id_materia",materiaid);
                registromateria.put("nombre_tarea",tareaid);



                bd.insert("asignacion",null,registromateria);

                bd.close();

                fechaentrega.setText("*Fecha de entrega*");

                Toast.makeText(getContext(), "Periodo Anexado", Toast.LENGTH_LONG).show();

                bd.close();
            }
        });



        return view;
    }



}







