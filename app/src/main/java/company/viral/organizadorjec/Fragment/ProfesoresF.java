package company.viral.organizadorjec.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import company.viral.organizadorjec.R;


public class ProfesoresF extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profesores, container, false);

        String[] listaProfes = {   "Francisco",
                "Arnoldo",
                "Petrusca",
                "Casimiro",
                "Eugenio",
                "Pedro",
                "Augusto",
                "Perdomo",
                "Moreira"};

      
        //adaptadores
        //adaptador dias

        ListView listaprofe = (ListView) view.findViewById(R.id.listprofe);

        ArrayAdapter<String> listavistaprofes = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,listaProfes);


        listaprofe.setAdapter(listavistaprofes);

        
        return view ;
    }



}




