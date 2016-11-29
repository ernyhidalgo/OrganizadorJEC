package company.viral.organizadorjec;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    private TextView aetidr,aetpassr,aetpasscr;
    private Button abtnacepr,abtncanr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        aetidr = (EditText) findViewById(R.id.etidr);
        aetpassr = (EditText) findViewById(R.id.etpassr);
        aetpasscr = (EditText) findViewById(R.id.etpasscr);
        abtnacepr = (Button) findViewById(R.id.btnacepr);
        abtncanr = (Button) findViewById(R.id.btncanr);

    }

    public void onClickAceptar (View view) {
        //metodo utilizado para guardar en la BD
        //1)las variables usadas para llenar la tabla usuario

        String auxn = aetidr.getText().toString();  //tomamos el nombre
        String auxp = aetpassr.getText().toString();//tomamos la clave
        String auxc = aetpasscr.getText().toString();//con este validamos la clave

        //verificamos QUE NADA QUEDE SIN LLENAR!!!

        //si la variable que acepta el nombre esta vacia nos pedira ingresar usuario
        if (auxn.isEmpty()){

            Toast.makeText(getApplicationContext(),"Ingrese un Usuario",Toast.LENGTH_LONG).show();

         //sino... si la clave es vacia entonces te pide que coloques una clave
        }else if (auxp.equals("")){

            Toast.makeText(getApplicationContext(),"Ingrese una contraseña",Toast.LENGTH_LONG).show();


            //si la clave de confirmacion es igual a la clave entonces guardalo en el sistema
        }else if (auxp.equals(auxc)){

            //abrimos la base de datos
            SQLite admin = new SQLite(this,"administracion", null, 1);

            //creamos variable re-escribible
            SQLiteDatabase bd = admin.getWritableDatabase();

            //creamos un contenedor llamado registro
            ContentValues registro = new ContentValues();

            //cargamos los datos en el contenedor..
            //diciendo... "registro se cargue con variable "usuario" y el valor de auxn
            registro.put("usuario", auxn);
            //diciendo... "registro se cargue con variable "clave" y el valor de auxn
            registro.put("clave", auxp);
            //estas variables deben ser EXACTAS a la de la tabla!


            // los inserto en la base de datos diciendo:
            //variable db (creada arriba) insertara los valores EN la tabla "usuarios", null, con el contenido de "registro"

            bd.insert("usuarios", null, registro);

            //cerramos bd
            bd.close();

            // ponemos los campos a vacío para insertar el siguiente usuario
            aetidr.setText("");
            aetpasscr.setText("");
            aetpassr.setText("");

            Toast.makeText(this, "Datos del usuario cargados", Toast.LENGTH_SHORT).show();



            //de ser incorrecto solo queda que las claves no coinciden..
        }else {
        Toast.makeText(getApplicationContext(),"La confirmacion no coincide",Toast.LENGTH_LONG).show();
    }






}

        /*PRUEBA CON SQLITE
        //validaciones correspondientes para el registro de usuario

        //si la variable que acepta el nombre esta vacia nos pedira ingresar usuario
        if (auxn.isEmpty()){

            Toast.makeText(getApplicationContext(),"Ingrese un Usuario",Toast.LENGTH_LONG).show();
        //sino... si la clave es vacia entonces te pide que coloques una clave
        }else if (auxp == null){

            Toast.makeText(getApplicationContext(),"Ingrese una contraseña",Toast.LENGTH_LONG).show();

        //si la clave de confirmacion es igual a la clave entonces guardalo en el sistema
        }else if (auxp.equals(auxc)){



            //al cumplirse todas las validaciones procedemos a guardar el usuario en la tabla correspondiente
            //objeto de tipo clase donde estamos guardando
            //instanciamos y llenamos con getAplo... nombre null xq ya lo tenemos en la BASE DE DATOS
            // null por q factory no importa y 1 el tipo de version
            AyudaBD db = new AyudaBD(getApplicationContext(),null,null,1);

            //llenamos el metodo para enviar los parametros
            String paquete = db.guardar(auxn,auxp);
            //mostramos el mensaje si no se cargobien la BD
            Toast.makeText(getApplicationContext(),paquete,Toast.LENGTH_SHORT).show();

            // de ser positivo entonces limpiamos los campos
            aetidr.setText("");
            aetpassr.setText("");
            aetpasscr.setText("");
            //mostramos mensaje de aceptacion y volvemos a la pantalla principal

            Toast.makeText(getApplicationContext(),"Usuario creado de forma exitosa",Toast.LENGTH_LONG).show();

            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);

        }else {
            Toast.makeText(getApplicationContext(),"La confirmacion no coincide",Toast.LENGTH_LONG).show();
        }*/



    /*accion para el boton cancelar o regresar*/

    public void onClickRegresar (View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }



}
