package company.viral.organizadorjec.FracmentPopUp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
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
import android.widget.TextView;

import company.viral.organizadorjec.ActivitysPrincipales.MenuCentral;
import company.viral.organizadorjec.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class ConfiguracionPeriodoF extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // mandamos a cargar la imagen del fragment
        View view = inflater.inflate(R.layout.fragment_configuracion_periodo, container, false);

        //creamos las variables que van a interactuar con el layout
        //TextView
        TextView inifecha = (TextView)view.findViewById(R.id.fechaini);
        TextView finalfecha = (TextView)view.findViewById(R.id.fechafinal);
        //botones
        Button btnini = (Button)view.findViewById(R.id.btnfragfecha);
        Button btnfinal = (Button)view.findViewById(R.id.btnfragfinal);
        //variables para el datepick

        //oncli

        btnini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment picker = new DatePickerFragment();
                picker.show(getFragmentManager(), "datePicker");

            }
        });


        return view;
    }
}
