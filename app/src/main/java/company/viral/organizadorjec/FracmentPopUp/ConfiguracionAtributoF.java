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


public class ConfiguracionAtributoF extends Fragment {


    private EditText nombreatributo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //creamos el fragment en el contenedor layout
        View view = inflater.inflate(R.layout.fragment_configuracion_atributos, container, false);

        //creamos las variables a usar
        //edittext que se dibuja en el layout
        nombreatributo = (EditText)view.findViewById(R.id.etnombreatributo);
        //creamos los botones que van a interactuar
        Button aceptaratributo = (Button)view.findViewById(R.id.agregaratributo);
        Button editaratributo = (Button)view.findViewById(R.id.editaratributo);
        Button borraratributo = (Button)view.findViewById(R.id.borraratributo);


        //implementacion de los onclick
        aceptaratributo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cargamos el id del usuario desde la activity
                Bundle bundle=getActivity().getIntent().getExtras();
                int identificar = bundle.getInt("identificador");
                //variable contenedora para el nombre
                String nameatributo = nombreatributo.getText().toString();
                //abrimos la base de datos
                SQLite admin = new SQLite(getContext(),"administracion",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                //creamos un cursor para verificar que ya no este creada el atributo
                Cursor validador;
                //revisamos la tabla
                validador=bd.rawQuery("select id from caracteristicas where nombre='"+nameatributo+"'",null);
                //si el valor es positivo
                if (validador.moveToFirst()==true){
                    //avisamos que existe y limpiamos los parametros
                    Toast.makeText(getContext(), "Atriburo ya existente", Toast.LENGTH_LONG).show();
                    nombreatributo.setText("");
                    //cerramos la bd
                    bd.close();
                    //sino
                }else {
                    //creamos un contenedor para ingresas lo datos a la base de datos
                    ContentValues registroatributos = new ContentValues();
                    //se insertan los datos en el contenedor
                    registroatributos.put("nombre", nameatributo);
                    registroatributos.put("id_usuario", identificar);
                    //e insertamos en la base de datos y luego la cerramos
                    bd.insert("caracteristicas", null, registroatributos);
                    bd.close();
                    //avisamos que los datos fueron cargados y limpiamos los campos
                    Toast.makeText(getContext(), "Atriburo Anexado", Toast.LENGTH_LONG).show();
                    nombreatributo.setText("");
                }
            }
        });

        editaratributo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle=getActivity().getIntent().getExtras();
                int identificar = bundle.getInt("identificador");

                Cursor atribu;
                String viendo = nombreatributo.getText().toString();
                SQLite admin = new SQLite(getContext(),"administracion",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                ContentValues registroatributos = new ContentValues();

                atribu=bd.rawQuery("select nombre from caracteristicas where id_usuario='"+identificar+"' and nombre='"+viendo+"'",null);

                if (atribu.moveToFirst()==true){
                    Toast.makeText(getContext(), "Atriburo Existe", Toast.LENGTH_LONG).show();

                }else{
                    nombreatributo.setText("");
                    Toast.makeText(getContext(), "Atriburo no encontrado", Toast.LENGTH_LONG).show();

                }

                bd.close();

            }
        });


        return view;
    }

}







