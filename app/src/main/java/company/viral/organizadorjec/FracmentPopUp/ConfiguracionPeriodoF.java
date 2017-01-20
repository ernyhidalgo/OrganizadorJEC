package company.viral.organizadorjec.FracmentPopUp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import company.viral.organizadorjec.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class ConfiguracionPeriodoF extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_configuracion_periodo, container, false);


        //codigo
        //creamos las variables que van a interactuar con el layout
        //edittext

        //botones con su metodo onclick

        //aqui pienso que debe implementarse los onclick para el data picker

        return view;
    }

}
