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

import company.viral.organizadorjec.ActivitysPrincipales.SQLite;
import company.viral.organizadorjec.R;


public class CaracteristicasF extends Fragment {
    private Cursor buscador;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_caracteristicas, container, false);


        SQLite admin = new SQLite(getContext(),"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();


        buscador=bd.rawQuery("select nombre from caracteristicas ",null);

        String [] listamateria = new String[buscador.getCount()];

        int i=0;
        while (buscador.moveToNext()){
            String contenedor = buscador.getString(buscador.getColumnIndex("nombre"));
            listamateria[i]=contenedor;
            i++;
        }

        //adaptadores
        //adaptador dias

        ListView listamate = (ListView) view.findViewById(R.id.listcaracticaf);

        ArrayAdapter<String> listavistamate = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,listamateria);


        listamate.setAdapter(listavistamate);

        return view ;
    }



}




