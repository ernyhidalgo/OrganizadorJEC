package company.viral.organizadorjec.ActivitysPrincipales;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import company.viral.organizadorjec.R;

public class Registro extends AppCompatActivity {

    private TextView aetidr,aetpassr,aetpasscr;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);


        aetidr = (EditText) findViewById(R.id.etidr);
        aetpassr = (EditText) findViewById(R.id.etpassr);
        aetpasscr = (EditText) findViewById(R.id.etpasscr);


    }




    public void onClickAceptarR (View view){

        //metodo utilizado para guardar en la BD
        //1)las variables usadas para llenar la tabla usuario

        String auxn = aetidr.getText().toString();  //tomamos el nombre
        String auxp = aetpassr.getText().toString();//tomamos la clave
        String auxc = aetpasscr.getText().toString();//con este validamos la clave

        //verificamos QUE NADA QUEDE SIN LLENAR!!!

        //si la variable que acepta el nombre esta vacia nos pedira ingresar usuario
        if (auxn.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Ingrese un Usuario", Toast.LENGTH_LONG).show();


        } else if (auxp.equals("")) {

            Toast.makeText(getApplicationContext(), "Ingrese una contraseña", Toast.LENGTH_LONG).show();


            //si la clave de confirmacion es igual a la clave entonces guardalo en el sistema
        } else if (auxp.equals(auxc)) {

            //abrimos la base de datos
            SQLite admin = new SQLite(this,"administracion", null, 1);

            //creamos variable re-escribible
            SQLiteDatabase bd = admin.getWritableDatabase();

            //creamos un contenedor llamado registro
            ContentValues registro = new ContentValues();

            //cargamos los datos en el contenedor..
            //diciendo... "registro se cargue con variable "usuario" y el valor de auxn
            registro.put("nombre", auxn);
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
        } else {
            Toast.makeText(getApplicationContext(), "La confirmacion no coincide", Toast.LENGTH_LONG).show();
        }

    }

    public void onClickRegresar (View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Desea Salir de la Aplicación?");
        builder.setTitle("Alerta!");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog=builder.create();
        dialog.show();
    }


}
