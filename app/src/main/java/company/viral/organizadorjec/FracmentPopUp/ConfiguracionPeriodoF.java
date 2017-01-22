package company.viral.organizadorjec.FracmentPopUp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import java.util.Calendar;
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
        final TextView inifecha = (TextView)view.findViewById(R.id.fechaini);
        final TextView finalfecha = (TextView)view.findViewById(R.id.fechafinal);
        //botones
        Button btnini = (Button)view.findViewById(R.id.btnfragfecha);
        Button btnfinal = (Button)view.findViewById(R.id.btnfragfinal);
        //variables para el datepick

        //onclick para la insercion de fecha
        btnini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendario = Calendar.getInstance();
                int yy = calendario.get(Calendar.YEAR);
                int mm = calendario.get(Calendar.MONTH);
                int dd = calendario.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String fecha = String.valueOf(dayOfMonth) +"-"+String.valueOf(monthOfYear+01)
                                +"-"+String.valueOf(year);
                        
                        inifecha.setText(fecha);

                    }
                }, yy, mm, dd);

                datePicker.show();

            }
        });

        btnfinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendario = Calendar.getInstance();
                int yy = calendario.get(Calendar.YEAR);
                int mm = calendario.get(Calendar.MONTH);
                int dd = calendario.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String fecha = String.valueOf(dayOfMonth) +"-"+String.valueOf(monthOfYear+1)
                                +"-"+String.valueOf(year);
                        finalfecha.setText(fecha);

                    }
                }, yy, mm, dd);

                datePicker.show();

            }
        });


        return view;
    }
}
