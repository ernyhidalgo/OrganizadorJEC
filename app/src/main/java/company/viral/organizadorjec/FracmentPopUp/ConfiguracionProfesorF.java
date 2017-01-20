package company.viral.organizadorjec.FracmentPopUp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import company.viral.organizadorjec.R;
import company.viral.organizadorjec.ActivitysPrincipales.SQLite;


public class ConfiguracionProfesorF extends Fragment {

    private EditText etnombreprofesorf;
    private EditText etcomentarioprofesorf;
    private Button btnagregarpf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_configuracion_profesor, container, false);

        //codigo
        etnombreprofesorf = (EditText)view.findViewById(R.id.etnombreprofesor);
        etcomentarioprofesorf = (EditText)view.findViewById(R.id.etcomentarioprofesor);

        btnagregarpf = (Button)view.findViewById(R.id.btnagregarprofesor);
        btnagregarpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle=getActivity().getIntent().getExtras();
                int identificar = bundle.getInt("identificador");

                String auxnp = etnombreprofesorf.getText().toString();
                String auxcp = etcomentarioprofesorf.getText().toString();

                SQLite admin = new SQLite(getContext(),"administracion",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                ContentValues registroprofe = new ContentValues();

                registroprofe.put("nombre",auxnp);
                registroprofe.put("detalle",auxcp);
                registroprofe.put("id_usuario",identificar);

                bd.insert("profesores",null,registroprofe);

                bd.close();

                etnombreprofesorf.setText("");
                etcomentarioprofesorf.setText("");

                Toast.makeText(getContext(), "Profesor anexado", Toast.LENGTH_LONG).show();

                bd.close();

            }
        });



        return view;
    }


}







