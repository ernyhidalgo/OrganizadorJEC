package company.viral.organizadorjec.FragmentMenu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import company.viral.organizadorjec.ActivitysPrincipales.MainActivity;
import company.viral.organizadorjec.FragmentMenu.Perfil.CambioClave;
import company.viral.organizadorjec.FragmentMenu.Perfil.CambioNombre;
import company.viral.organizadorjec.FragmentMenu.Perfil.SelectordeAlarmas;
import company.viral.organizadorjec.R;


public class PerfilF extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //creamos la vista del fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        //cramos las variables del layout
        Button cnom = (Button) view.findViewById(R.id.cnusuario);
        Button calarma = (Button)view.findViewById(R.id.ealarmas);
        Button cerrar = (Button) view.findViewById(R.id.cerrarsesion);
        Button cclave = (Button)view.findViewById(R.id.ccusuario);

        //implementar el metodo del cambiar nombre
        cnom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager gestiondefragment = getFragmentManager();
                gestiondefragment.beginTransaction().replace(R.id.contenedor,new CambioNombre()).commit();

            }
        });

        calarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager gestiondefragment = getFragmentManager();
                gestiondefragment.beginTransaction().replace(R.id.contenedor,new SelectordeAlarmas()).commit();

            }
        });

        cclave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager gestiondefragment = getFragmentManager();
                gestiondefragment.beginTransaction().replace(R.id.contenedor,new CambioClave()).commit();

            }
        });

        //implementamos el motodo del boton de cerrar sesion
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //establecemos un mensaje de alerta para la confirmacion
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("¿Desea cerrar sesion?");
                builder.setTitle("Alerta!");
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    //de ser positivo vamos al inicio y cerramo
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i= new Intent(getContext(), MainActivity.class);
                        startActivity(i);
                        getActivity().finish();

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
        });
        return view;
    }

}
