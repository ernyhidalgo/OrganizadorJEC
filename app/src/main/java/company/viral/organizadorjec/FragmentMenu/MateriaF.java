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

import company.viral.organizadorjec.R;
import company.viral.organizadorjec.Clases.SQLite;


public class MateriaF extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_materia, container, false);
        //cargamos el usuario desde la activity
        Bundle bundle=getActivity().getIntent().getExtras();
        int identificar = bundle.getInt("identificador");
        //inicializamos la bd y el cursor correspondiente para recorrer y almacenar
        SQLite admin = new SQLite(getContext(),"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor buscador;
        //recorremos la bd y almacenamos en el cursor
        buscador=bd.rawQuery("select nombre from materias where id_usuario='"+identificar+"'",null);
        //creamor un arreglo para ser llenado por el buscador
        String [] listamateria = new String[buscador.getCount()];

        int i=0;
        while (buscador.moveToNext()){
            String contenedormateria = buscador.getString(buscador.getColumnIndex("nombre"));
            listamateria[i]=contenedormateria;
            i++;
        }
        //adaptadores
        //adaptador dias
        //--------------------------------------------------------------------------
        ListView listamate = (ListView) view.findViewById(R.id.listmateriaf);
        ArrayAdapter<String> listavistamate = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,listamateria);
        listamate.setAdapter(listavistamate);
        //--------------------------------------------------------------------------
        return view ;
    }
}