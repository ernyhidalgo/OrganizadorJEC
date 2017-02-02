package company.viral.organizadorjec.FragmentMenu;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import company.viral.organizadorjec.Clases.SQLite;
import company.viral.organizadorjec.R;


public class InicioF extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        Bundle bundle=getActivity().getIntent().getExtras();
        int identificar = bundle.getInt("identificador");
        SQLite admin = new SQLite(getContext(),"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();


        Cursor buscador = bd.rawQuery("select nombre_tarea from asignacion where id_usuario='" + identificar + "' ", null);
        String [] menuDias = new String[buscador.getCount()];

        int i=0;
        while (buscador.moveToNext()){
            String contenedor = buscador.getString(buscador.getColumnIndex("nombre_tarea"));
            menuDias[i]=contenedor;
            i++;
        }
        String[] menu15Das = {  "Tareaaaaaaa",
                                "Trabajo",
                                "Salida"};

        String[] menumes = {    "Tareaaaaaaaaaa",
                                "Trabajo",
                                "Salida",
                                "paseo de perro","comer"};



        //adaptadores
        //adaptador dias
        ListView listdias = (ListView) view.findViewById(R.id.menudias);

        ArrayAdapter<String> listavistadias = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,menuDias);


        listdias.setAdapter(listavistadias);

        //adaptador 15dias

        ListView lista15dias = (ListView) view.findViewById(R.id.menu15dias);

        ArrayAdapter<String> listavista15dias = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,menu15Das);

        lista15dias.setAdapter(listavista15dias);

        //adaptador mes

        ListView listames = (ListView) view.findViewById(R.id.menumes);

        ArrayAdapter<String> listavistames =new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,menumes);

        listames.setAdapter(listavistames);

        return view ;
    }



}
