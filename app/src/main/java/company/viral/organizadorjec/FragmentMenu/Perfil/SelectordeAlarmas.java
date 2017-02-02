package company.viral.organizadorjec.FragmentMenu.Perfil;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import java.util.Calendar;

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
import android.widget.TimePicker;
import android.widget.Toast;

import company.viral.organizadorjec.ActivitysPrincipales.MainActivity;
import company.viral.organizadorjec.Clases.SQLite;
import company.viral.organizadorjec.Clases.AlarmaNotificacionReciver;
import company.viral.organizadorjec.FragmentMenu.PerfilF;
import company.viral.organizadorjec.R;


public class SelectordeAlarmas extends Fragment {

    private PendingIntent pendingIntent;
    private Intent myintent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //creamos la vista del fragment
        View view = inflater.inflate(R.layout.selector_alarma, container, false);

        final AlarmManager alarma;
        //cramos las variables del layout
        Button acepalarm = (Button) view.findViewById(R.id.btnaceptaralarma);
        Button boralarm = (Button) view.findViewById(R.id.btnborraralarma);
        Button cancelar = (Button) view.findViewById(R.id.btncancelaralarma);
        //los TV
        final TextView alarmview = (TextView)view.findViewById(R.id.avisoalarm);
        //alarma
        final TimePicker timePicker = (TimePicker)view.findViewById(R.id.timePicker);
        //base de datos y cursos y usuario
        Bundle bundle=getActivity().getIntent().getExtras();
        final int identificar = bundle.getInt("identificador");
        Cursor alarmabd;
        SQLite admin = new SQLite(getContext(),"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        alarmabd=bd.rawQuery("select id, hora, minuto from alarmas where id_usuario='"+identificar+"'",null);
        if (alarmabd.moveToFirst()==true){
            int hora=alarmabd.getInt(1);
            int minuto=alarmabd.getInt(2);
            alarmview.setText("La alarma sonara a las "+hora+":"+minuto);

        }else{
            alarmview.setText("La alarma no esta establecida");

        }
        bd.close();


        //instanciamos la alarma
        alarma=(AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);


        //necesitamos un calendar para la seleccion de tiempo

        final Calendar calendar=Calendar.getInstance();

        //implementar el metodo de aceptar alarma y cambiar aviso
        acepalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLite admin = new SQLite(getContext(),"administracion",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                Cursor alarmabd2;
                ContentValues registraralarma = new ContentValues();
                alarmabd2=bd.rawQuery("select id from alarmas where id_usuario='"+identificar+"'",null);
                if (alarmabd2.moveToFirst()==true){
                    Toast.makeText(getContext(), "Alarma ya existe debe borrar la actual", Toast.LENGTH_LONG).show();

                }else{

                    String mejorhora=String.valueOf(timePicker.getCurrentHour());
                    String mejorminuto=String.valueOf(timePicker.getCurrentMinute());

                    if (timePicker.getCurrentHour()<10){
                        mejorhora=String.valueOf("0"+timePicker.getCurrentHour());
                    }

                    if (timePicker.getCurrentMinute()<10){
                        mejorminuto=String.valueOf("0"+timePicker.getCurrentMinute());
                    }

                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());

                    myintent = new Intent(getContext(), AlarmaNotificacionReciver.class);
                    pendingIntent = PendingIntent.getBroadcast(getContext(),0,myintent,0);

                    alarma.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            AlarmManager.INTERVAL_DAY, pendingIntent);

                    registraralarma.put("hora",mejorhora);
                    registraralarma.put("minuto",mejorminuto);
                    registraralarma.put("id_usuario",identificar);

                    bd.insert("alarmas",null,registraralarma);


                    alarmview.setText("La alarma sonara a las "+mejorhora+":"+mejorminuto);
                }


               bd.close();
            }
        });

        boralarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLite admin = new SQLite(getContext(),"administracion",null,1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                Cursor alarmabd3;
                alarmabd3=bd.rawQuery("select id from alarmas where id_usuario='"+identificar+"'",null);
                if (alarmabd3.moveToFirst()==true){
                    bd.delete("alarmas","id="+alarmabd3.getInt(0),null);

                    Toast.makeText(getContext(), "Alarma borrada", Toast.LENGTH_LONG).show();
                    alarmview.setText("La alarma no esta establecida");

                }else{
                    Toast.makeText(getContext(), "No existe alarma establecida", Toast.LENGTH_LONG).show();


                }
                bd.close();
            }
        });

        //implementamos el motodo del boton de cerrar sesion
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager gestiondefragment = getFragmentManager();
                gestiondefragment.beginTransaction().replace(R.id.contenedor,new PerfilF()).commit();
            }
        });
        return view;
    }

}
