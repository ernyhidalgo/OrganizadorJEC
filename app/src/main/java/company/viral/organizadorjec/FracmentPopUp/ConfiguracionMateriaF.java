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


public class ConfiguracionMateriaF extends Fragment {

    private EditText etmateriaf,etcomentariomateriaf;
    private Button btnagregarmateriaf;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_configuracion_materia, container, false);

        etmateriaf = (EditText)view.findViewById(R.id.etnombremateria);
        etcomentariomateriaf = (EditText)view.findViewById(R.id.etcomentariomateria);

        btnagregarmateriaf = (Button)view.findViewById(R.id.btnagregarmateriaf);
        btnagregarmateriaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle=getActivity().getIntent().getExtras();
                int identificar = bundle.getInt("identificador");


                String auxnm = etmateriaf.getText().toString();
                String auxcm = etcomentariomateriaf.getText().toString();

                SQLite admin = new SQLite(getContext(),"administracion",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                ContentValues registromate = new ContentValues();

                registromate.put("nombre",auxnm);
                registromate.put("detalle",auxcm);


                bd.insert("materias",null,registromate);
                etmateriaf.setText("");
                etcomentariomateriaf.setText("");

                Toast.makeText(getContext(), "Materia anexada", Toast.LENGTH_LONG).show();

                bd.close();
            }
        });


        return view;
    }



}







