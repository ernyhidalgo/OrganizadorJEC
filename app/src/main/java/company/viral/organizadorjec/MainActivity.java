package company.viral.organizadorjec;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onClickAceptar (View view) {

        String auxn = aetid.getText().toString();
        String auxp = aetpass.getText().toString();

        SQLite admin = new SQLite(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        fila=bd.rawQuery("select usuario, clave from usuarios where usuario='"+auxn+"'and clave='"+auxp+"'",null);



        if(fila.moveToFirst()==true){

            //capturamos los valores del cursos y lo almacenamos en variable
            String usua=fila.getString(0);
            String pass=fila.getString(1);

            //preguntamos si los datos ingresados son iguales
            if (auxn.equals(usua)&&auxp.equals(pass)){

                //si son iguales entonces vamos a otra ventana
                //Menu es una nueva actividad empty
                Intent ven=new Intent(this,MenuCentral.class);

                startActivity(ven);

                //limpiamos las las cajas de texto
                aetid.setText("");
                aetpass.setText("");

            }

        }else {

            Toast.makeText(getApplicationContext(), "Usuario o contraseña erroneo", Toast.LENGTH_LONG).show();
        }

        bd.close();
    }


    //metodo para entrar a la actividad de registro

    public void onClickRegistro(View view){
        Intent i = new Intent(this,Registro.class);
        startActivity(i);
    }

}
