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


public class CambioNombre extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //creamos la vista del fragment
        View view = inflater.inflate(R.layout.cambio_nombre, container, false);

        //validamos los edittex
        final TextView tvsunom = (TextView)view.findViewById(R.id.etsunom);
        final TextView tvnuevonom = (TextView)view.findViewById(R.id.etnuevousuario);
        final TextView tvconfirnom = (TextView)view.findViewById(R.id.etconfirmanuevousu);
        //validamos los botones
        Button acepcam = (Button)view.findViewById(R.id.btnaceptarcambio);
        Button canccam = (Button)view.findViewById(R.id.btncancelarcambio);



        //implementamos los onclick
        acepcam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sunom = tvsunom.getText().toString();
                String nuevonom = tvnuevonom.getText().toString();
                String confirnom = tvconfirnom.getText().toString();
                int validador=0;

                //capturamos el usuario desde la activity
                Bundle bundle=getActivity().getIntent().getExtras();
                int identificar = bundle.getInt("identificador");
                //inicializamos la bd
                SQLite admin = new SQLite(getContext(),"administracion",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                //creamos cursor
                Cursor verficarnom;
                //exploramos la bd a ver si el usuario es el mismo
                verficarnom=bd.rawQuery("select id from usuarios where nombre= '"+sunom+"'",null);
                if (verficarnom.moveToFirst()==true){
                    validador=verficarnom.getInt(0);
                    if (validador==identificar){
                        //de ser el usuario verificamos si el nuevo usuario a ingresar ya existe
                        //vemos si esta vacio (primero)
                        if (nuevonom.isEmpty()){
                            //de estar vacio avisamos
                            Toast.makeText(getContext(), "Ingrese nuevo usuario", Toast.LENGTH_LONG).show();
                            tvsunom.setText("");
                            tvnuevonom.setText("");
                            tvconfirnom.setText("");
                        }else {
                            //sino esta vacio verificamos si esta disponible
                            Cursor todouser;
                            todouser = bd.rawQuery("select id from usuarios where nombre= '" + nuevonom + "'", null);
                            //de no estar disponible avisamos que no puede ingresar el usuario
                            if (todouser.moveToFirst() == true) {
                                Toast.makeText(getContext(), "Intente otro usuario", Toast.LENGTH_LONG).show();
                                tvsunom.setText("");
                                tvnuevonom.setText("");
                                tvconfirnom.setText("");
                                // de tener el cupo de usuario verificamos que el usuario nuevo y la confirmacion sean iguales
                            } else if (nuevonom.equals(confirnom)) {
                                //de ser positivos modificamos la bd
                                ContentValues subida = new ContentValues();
                                subida.put("nombre", nuevonom);

                                int up = bd.update("usuarios", subida, "id='" + identificar + "'", null);
                                if (up == 1) {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setMessage("Debe cerrar sesion para cargar los cambios");
                                    builder.setTitle("Alerta!");
                                    AlertDialog dialog=builder.create();
                                    dialog.show();
                                    tvsunom.setText("");
                                    tvnuevonom.setText("");
                                    tvconfirnom.setText("");
                                }
                            }else {
                                Toast.makeText(getContext(), "La confirmacion no coincide", Toast.LENGTH_LONG).show();
                                tvsunom.setText("");
                                tvnuevonom.setText("");
                                tvconfirnom.setText("");
                            }
                        }
                    }else {
                        Toast.makeText(getContext(), "Error al validar su usuario", Toast.LENGTH_LONG).show();
                        tvsunom.setText("");
                        tvnuevonom.setText("");
                        tvconfirnom.setText("");
                    }
                }else {
                    Toast.makeText(getContext(), "Error al validar su usuario", Toast.LENGTH_LONG).show();
                    tvsunom.setText("");
                    tvnuevonom.setText("");
                    tvconfirnom.setText("");
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
