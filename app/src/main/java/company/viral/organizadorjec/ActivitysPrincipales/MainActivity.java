package company.viral.organizadorjec.ActivitysPrincipales;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import company.viral.organizadorjec.Clases.SQLite;
import company.viral.organizadorjec.R;

//aqui empieza...
public class MainActivity extends AppCompatActivity {

    //creamos variables EditText para capturar los datos
    private EditText aetid,aetpass;
    private Cursor fila;


    //en este metodo SIEMPRE se dibuja la app correspondiente
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //antes de dibujar definimos las variables y a quienes pertecen en el layout

        aetid = (EditText) findViewById(R.id.etid);
        aetpass = (EditText) findViewById(R.id.etpass);

    }

    //creamos los metodos con los que reaccionan los btn (onClick)
    /*metodo para entrar y buscar (en construccion.... explorando metodos)*/

    public void onClickAcepta (View view) {
        String auxn = aetid.getText().toString();
        String auxp = aetpass.getText().toString();

        SQLite admin = new SQLite(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //modifique el llamado de la base de datos agregando el id
        fila=bd.rawQuery("select nombre, clave, id from usuarios where nombre='"+auxn+"'and clave='"+auxp+"'",null);



        if(fila.moveToFirst()==true){

            //capturamos los valores del cursos y lo almacenamos en variable
            String usua=fila.getString(0);
            String pass=fila.getString(1);
            //y almacenandolo en esta variable
            int id=fila.getInt(2);

            //preguntamos si los datos ingresados son iguales
            if (auxn.equals(usua)&&auxp.equals(pass)){

                //si son iguales entonces vamos a otra ventana
                //Menu es una nueva actividad empty
                Intent ven=new Intent(this,MenuCentral.class);

                //agregue el put extra aqui...----
                ven.putExtra("identificador",id);
                //--------------------------------
                startActivity(ven);

                //limpiamos las las cajas de texto
                aetid.setText("");
                aetpass.setText("");

                finish();

            }

        }else {
            aetid.setText("");
            aetpass.setText("");

            Toast.makeText(getApplicationContext(), "Usuario o contraseña erroneo", Toast.LENGTH_LONG).show();
        }

        bd.close();

    }

    //metodo para entrar a la actividad de registro

    public void onClickRegistro(View view){
        Intent i = new Intent(this,Registro.class);
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