package company.viral.organizadorjec.ActivitysPrincipales;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import company.viral.organizadorjec.FragmentMenu.CaracteristicasF;
import company.viral.organizadorjec.FragmentMenu.PeriodosF;
import company.viral.organizadorjec.FracmentPopUp.ConfiguracionActividadF;
import company.viral.organizadorjec.FracmentPopUp.ConfiguracionMateriaF;
import company.viral.organizadorjec.FracmentPopUp.ConfiguracionPeriodoF;
import company.viral.organizadorjec.FracmentPopUp.ConfiguracionProfesorF;
import company.viral.organizadorjec.FragmentMenu.InicioF;
import company.viral.organizadorjec.FragmentMenu.MateriaF;
import company.viral.organizadorjec.FragmentMenu.PerfilF;
import company.viral.organizadorjec.FragmentMenu.ProfesoresF;
import company.viral.organizadorjec.R;

public class MenuCentral extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private PopupWindow popupadicion;

    private DrawerLayout posicionpopup;
    //agregue el cursor----------
    private Cursor nombreid;
    //--------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_central);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        posicionpopup = (DrawerLayout) findViewById(R.id.drawer_layout);



        //colocamos el fragment con que inicia el menu

        //final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor,new InicioF(),"main").commit();


        //este es el apartado para el botonsito flotante

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            //metodo de escucha para el popup
            @Override
            public void onClick(View view) {

               if(popupadicion!=null){

                    popupadicion.dismiss();

               }

               //implementamos el popup
                LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View vistaadicion = inflater.inflate(R.layout.activity_pop_adicion,null);

                popupadicion = new PopupWindow(
                        vistaadicion, RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );

                //luego de clicear y abrir el popup le decimos...
                //si das al profe ve a profe


                LinearLayout btnprofe = (LinearLayout) vistaadicion.findViewById(R.id.btnagregarprofesor);
                btnprofe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.contenedor, new ConfiguracionProfesorF()).addToBackStack(null).commit();
                        popupadicion.dismiss();

                    }
                });
                //si le das actividad ve actividad
                LinearLayout btnactividad = (LinearLayout) vistaadicion.findViewById(R.id.btnagregaractividad);
                btnactividad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.contenedor,new ConfiguracionActividadF()).commit();
                        popupadicion.dismiss();

                    }
                });
                //si le das a materias ve a materias
                LinearLayout btnmaterias = (LinearLayout) vistaadicion.findViewById(R.id.btnagregarmateria);
                btnmaterias.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.contenedor,new ConfiguracionMateriaF()).commit();
                        popupadicion.dismiss();

                    }
                });
                //si le das a periodo ve a periodo
                LinearLayout btnperiodo = (LinearLayout) vistaadicion.findViewById(R.id.btnagregarperiodo);
                btnperiodo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.contenedor,new ConfiguracionPeriodoF()).commit();
                        popupadicion.dismiss();

                    }
                });
                //luego le decimos que cierre el popup con el boton

                Button cerrarboton = (Button) vistaadicion.findViewById(R.id.btnpopupcerrar);
                cerrarboton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupadicion.dismiss();
                        popupadicion=null;
                    }
                });


                //hubicamos donde queremos el popup
                popupadicion.showAtLocation(posicionpopup, Gravity.CENTER,0,0 );

                //aqui



            }
        });

        //apartado del navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        //con esto generamos el usuario en el header del menu-------------------------------
        View hView = navigationView.getHeaderView(0);
        TextView correo = (TextView) hView.findViewById(R.id.textviewcorreo);

        Bundle bundle=getIntent().getExtras();
        int identificar = bundle.getInt("identificador");

        SQLite admin = new SQLite(this,"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        nombreid=bd.rawQuery("select nombre from usuarios where id='"+identificar+"'",null);

        if (nombreid.moveToFirst()==true) {
            String usuarioid = nombreid.getString(0);
            correo.setText(usuarioid);

        }
        //----------------------------------------------------------------------------------
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else if (popupadicion != null) {

            popupadicion.dismiss();
            popupadicion = null;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea Salir de la Aplicación?");
            builder.setTitle("Alerta!");
            builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_central, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_camera) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new InicioF()).commit();

        } else if (id == R.id.nav_gallery) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new ProfesoresF()).commit();

        } else if (id == R.id.nav_materia){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new MateriaF()).commit();

        } else if (id == R.id.nav_slideshow) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new PeriodosF()).commit();

        } else if (id == R.id.nav_manage) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new CaracteristicasF()).commit();

        } else if (id == R.id.nav_share) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new PerfilF()).commit();

        } else if (id == R.id.nav_send) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea Salir de la Aplicación?");
            builder.setTitle("Alerta!");
            builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}