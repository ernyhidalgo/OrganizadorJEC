package company.viral.organizadorjec.FracmentPopUp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import company.viral.organizadorjec.Clases.SQLite;
import company.viral.organizadorjec.R;


public class ConfiguracionTareaF extends Fragment {


    private EditText nombretarea;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //creamos el fragment en el contenedor layout
        View view = inflater.inflate(R.layout.fragment_configuracion_tareas, container, false);

        //creamos las variables a usar
        //edittext que se dibuja en el layout
        nombretarea = (EditText)view.findViewById(R.id.etnombretarea);
        //creamos los botones que van a interactuar
        Button aceptartarea = (Button)view.findViewById(R.id.agregartarea);
        Button editartarea = (Button)view.findViewById(R.id.editartarea);
        Button borrartarea = (Button)view.findViewById(R.id.borrartarea);


        //implementacion de los onclick
        aceptartarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cargamos el id del usuario desde la activity
                Bundle bundle=getActivity().getIntent().getExtras();
                int identificar = bundle.getInt("identificador");
                //variable contenedora para el nombre
                String nametarea = nombretarea.getText().toString();
                //abrimos la base de datos
                SQLite admin = new SQLite(getContext(),"administracion",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                //creamos un cursor para verificar que ya no este creada el atributo
                Cursor validador;
                //revisamos la tabla
                validador=bd.rawQuery("select id from tarea where nombre='"+nametarea+"'",null);
                //si el valor es positivo
                if (validador.moveToFirst()==true){
                    //avisamos que existe y limpiamos los parametros
                    Toast.makeText(getContext(), "Tarea ya existente", Toast.LENGTH_LONG).show();
                    nombretarea.setText("");
                    //cerramos la bd
                    bd.close();
                    //sino
                }else {
                    //creamos un contenedor para ingresas lo datos a la base de datos
                    ContentValues registrotareas = new ContentValues();
                    //se insertan los datos en el contenedor
                    registrotareas.put("nombre", nametarea);
                    registrotareas.put("id_usuario", identificar);
                    //e insertamos en la base de datos y luego la cerramos
                    bd.insert("tarea", null, registrotareas);
                    bd.close();
                    //avisamos que los datos fueron cargados y limpiamos los campos
                    Toast.makeText(getContext(), "Atriburo Anexado", Toast.LENGTH_LONG).show();
                    nombretarea.setText("");
                }
            }
        });

        editartarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle=getActivity().getIntent().getExtras();
                int identificar = bundle.getInt("identificador");

                Cursor atribu;
                String viendo = nombretarea.getText().toString();
                SQLite admin = new SQLite(getContext(),"administracion",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                ContentValues registroatributos = new ContentValues();

                atribu=bd.rawQuery("select nombre from tarea where id_usuario='"+identificar+"' and nombre='"+viendo+"'",null);

                if (atribu.moveToFirst()==true){
                    Toast.makeText(getContext(), "Atriburo Existe", Toast.LENGTH_LONG).show();

                }else{
                    nombretarea.setText("");
                    Toast.makeText(getContext(), "Atriburo no encontrado", Toast.LENGTH_LONG).show();

                }

                bd.close();

            }
        });


        return view;
    }

}







