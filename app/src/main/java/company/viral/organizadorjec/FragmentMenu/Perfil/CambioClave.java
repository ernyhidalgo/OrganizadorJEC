package company.viral.organizadorjec.FragmentMenu.Perfil;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import company.viral.organizadorjec.Clases.SQLite;
import company.viral.organizadorjec.FragmentMenu.PerfilF;
import company.viral.organizadorjec.R;


public class CambioClave extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //creamos la vista del fragment
        View view = inflater.inflate(R.layout.cambio_clave, container, false);

        //validamos los edittex
        final TextView tvsuclave = (TextView)view.findViewById(R.id.etsuclave);
        final TextView tvnuevaclave = (TextView)view.findViewById(R.id.etnuevaclave);
        final TextView tvconfirmarclave = (TextView)view.findViewById(R.id.etconfirmaclave);
        //validamos los botones
        Button acepcam = (Button)view.findViewById(R.id.btnaceptarcambio);
        Button canccam = (Button)view.findViewById(R.id.btncancelarcambio);



        //implementamos los onclick
        acepcam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String suclave = tvsuclave.getText().toString();
                String nuevaclave = tvnuevaclave.getText().toString();
                String confirmarclave = tvconfirmarclave.getText().toString();
                String validador="";

                //capturamos el usuario desde la activity
                Bundle bundle=getActivity().getIntent().getExtras();
                int identificar = bundle.getInt("identificador");
                //inicializamos la bd
                SQLite admin = new SQLite(getContext(),"administracion",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                //creamos cursor
                Cursor verficarnom;
                //exploramos la bd a ver si el usuario es el mismo
                verficarnom=bd.rawQuery("select clave from usuarios where id='"+identificar+"'",null);
                if (verficarnom.moveToFirst()==true){
                    validador=verficarnom.getString(0);
                    if (validador.equals(suclave)){
                        //vemos si esta vacio (primero)
                        if (nuevaclave.isEmpty()){
                            //de estar vacio avisamos
                            Toast.makeText(getContext(), "Ingrese su nueva clave", Toast.LENGTH_LONG).show();
                            tvsuclave.setText("");
                            tvnuevaclave.setText("");
                            tvconfirmarclave.setText("");
                            bd.close();
                            }else if (nuevaclave.equals(confirmarclave)) {
                                //de ser positivos modificamos la bd
                                ContentValues subida = new ContentValues();
                                subida.put("clave", nuevaclave);

                                int up = bd.update("usuarios", subida, "id='" + identificar + "'", null);
                                if (up == 1) {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setMessage("Debe cerrar sesion para cargar los cambios");
                                    builder.setTitle("Alerta!");
                                    AlertDialog dialog=builder.create();
                                    dialog.show();
                                    tvsuclave.setText("");
                                    tvnuevaclave.setText("");
                                    tvconfirmarclave.setText("");
                                }
                            }else{
                            Toast.makeText(getContext(), "Error al validar nu nueva clave", Toast.LENGTH_LONG).show();
                            tvsuclave.setText("");
                            tvnuevaclave.setText("");
                            tvconfirmarclave.setText("");
                        }

                    }else {
                        Toast.makeText(getContext(), "Error al validar su usuario", Toast.LENGTH_LONG).show();
                        tvsuclave.setText("");
                        tvnuevaclave.setText("");
                        tvconfirmarclave.setText("");
                    }
                }else {
                    Toast.makeText(getContext(), "Error al validar su usuario"+validador, Toast.LENGTH_LONG).show();
                    tvsuclave.setText("");
                    tvnuevaclave.setText("");
                    tvconfirmarclave.setText("");
                }
                bd.close();
            }
        });

        canccam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager gestiondefragment = getFragmentManager();
                gestiondefragment.beginTransaction().replace(R.id.contenedor,new PerfilF()).commit();

            }
        });
        return view;
    }

}
