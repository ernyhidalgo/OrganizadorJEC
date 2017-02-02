package company.viral.organizadorjec.FracmentPopUp;

import android.app.DatePickerDialog;

import java.util.Calendar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import company.viral.organizadorjec.Clases.SQLite;
import company.viral.organizadorjec.R;


public class ConfiguracionPeriodoF extends Fragment {

    //para que todos la usen instanciamos una sola vez afuera para todo los metodos
    private EditText nombreperiodof;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // mandamos a cargar la imagen del fragment
        View view = inflater.inflate(R.layout.fragment_configuracion_periodo, container, false);

        //creamos las variables que van a interactuar con el layout
        //ediitext a usar
        nombreperiodof = (EditText)view.findViewById(R.id.etnombreperiodo);
        //TextView
        final TextView inifecha = (TextView)view.findViewById(R.id.fechaini);
        final TextView finalfecha = (TextView)view.findViewById(R.id.fechafinal);
        //botones
        Button btnini = (Button)view.findViewById(R.id.btnfragfecha);
        Button btnfinal = (Button)view.findViewById(R.id.btnfragfinal);
        Button btnAcep = (Button)view.findViewById(R.id.agregarperiodo);
        Button btnEditar = (Button)view.findViewById(R.id.editarperiodo);


        //onclick para la insercion de fecha
        btnini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);

                inifecha.setText("");
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        String mejordia=String.valueOf(dayOfMonth);
                        String mejormes=String.valueOf(monthOfYear+1);

                        if (dayOfMonth<10){
                            mejordia=String.valueOf("0"+dayOfMonth);
                        }

                        if ((monthOfYear+1)<10){
                            mejormes=String.valueOf("0"+(monthOfYear+1));
                        }


                        inifecha.setText(mejordia + "-" + mejormes + "-" + year);

                    }
                }, year, month, day);
                datePickerDialog.setTitle("Seleccione la Fecha");
                datePickerDialog.show();
            }
        });

        btnfinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);



                finalfecha.setText("");
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                        String mejordia=String.valueOf(dayOfMonth);
                        String mejormes=String.valueOf(monthOfYear+1);

                        if (dayOfMonth<10){
                            mejordia=String.valueOf("0"+dayOfMonth);
                        }

                        if ((monthOfYear+1)<10){
                            mejormes=String.valueOf("0"+(monthOfYear+1));
                        }


                        finalfecha.setText(mejordia + "-" + mejormes + "-" + year);

                    }
                }, year, month, day);
                datePickerDialog.setTitle("Seleccione la Fecha");
                datePickerDialog.show();
            }
        });


        btnAcep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=getActivity().getIntent().getExtras();
                int identificar = bundle.getInt("identificador");

                String nombreperiodo = nombreperiodof.getText().toString();

                SQLite admin = new SQLite(getContext(),"administracion",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                ContentValues registroperiodo = new ContentValues();

                registroperiodo.put("nombre",nombreperiodo);
                registroperiodo.put("fechainicio", String.valueOf(inifecha.getText()));
                registroperiodo.put("fechacierre", String.valueOf(finalfecha.getText()));
                registroperiodo.put("id_usuario",identificar);

                bd.insert("periodo",null,registroperiodo);

                bd.close();

                nombreperiodof.setText("");
                inifecha.setText("");
                finalfecha.setText("");

                Toast.makeText(getContext(), "Periodo Anexado", Toast.LENGTH_LONG).show();

                bd.close();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //momentaneamente para ver la cargada de datos
                Bundle bundle=getActivity().getIntent().getExtras();
                int identificar = bundle.getInt("identificador");

                Cursor yomero;
                String aver = nombreperiodof.getText().toString();
                SQLite admin = new SQLite(getContext(),"administracion",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                yomero=bd.rawQuery("select nombre, fechainicio, fechacierre from periodo where nombre='"+aver+"'and id_usuario='"+identificar+"'",null);

                if (yomero.moveToFirst()==true){
                    inifecha.setText(yomero.getString(1));
                    finalfecha.setText(yomero.getString(2));
                } else{
                    nombreperiodof.setText("perido fallido");
                    inifecha.setText("periodo no encontrado");
                    finalfecha.setText("periodo no encontrado");
                }

            }
        });

        return view;
    }
}
