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

public class MainActivity extends AppCompatActivity {

    private EditText aetid,aetpass;
    private Button abtnacep,abtnreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aetid = (EditText) findViewById(R.id.etid);
        aetpass = (EditText) findViewById(R.id.etpass);
        abtnacep = (Button) findViewById(R.id.btnacep);
        abtnreg = (Button) findViewById(R.id.btnreg);
    }

    /*metodo para entrar y buscar (en construccion.... explorando metodos)*/
    public void onClickAceptar (View view) {

        String auxn = aetid.getText().toString();
        String auxp = aetpass.getText().toString();

        AyudaBD db = new AyudaBD(getApplicationContext(),null,null,1);
        String buscar = new String();


    /*validamos la cargada de datos*/
        if (auxn.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Ingrese un Usuario", Toast.LENGTH_LONG).show();

        } else if (auxp.isEmpty()){

            Toast.makeText(getApplicationContext(), "Ingrese clave", Toast.LENGTH_LONG).show();
        } else {


            Intent i = new Intent(this,Menusito.class);
            startActivity(i);
        }
    }
    public void onClickRegistro(View view){
        Intent i = new Intent(this,Registro.class);
        startActivity(i);

    }


}
