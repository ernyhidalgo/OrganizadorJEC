package company.viral.organizadorjec.ActivitysPrincipales;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by erny on 27/10/2016.
 */

public class SQLite extends SQLiteOpenHelper {

    //constructor.......
    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    //aqui se crea la tabla...
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table usuarios (id integer primary key autoincrement, " +
                "nombre text, clave text)");

        db.execSQL("create table profesores (id integer primary key autoincrement, " +
                "nombre text, detalle text,id_usuario integer,foreign key(id_usuario) references usuarios(id))");


        db.execSQL("create table materias (id integer primary key autoincrement, " +
                "nombre text, id_profesor integer, id_periodo integer, detalle text, " +
                "foreign key(id_profesor) references profesores (id)," +
                "foreign key(id_periodo) references periodo(id))");

        db.execSQL("create table periodo (id integer primary key autoincrement, " +
                "nombre text, id_usuario integer, fechainicio text, fechacierre text," +
                "foreign key(id_usuario) references usuarios(id))");

        db.execSQL("create table caracteristicas (id integer primary key autoincrement, " +
                "nombre text)");

        db.execSQL("create table asignacion (id integer primary key autoincrement, " +
                "detalle text, id_materia integer, fecha integer, id_periodo integer, id_tarea integer," +
                "foreign key(id_materia) references materias(id)," +
                "foreign key(id_periodo) references periodo(id)," +
                "foreign key(id_tarea) references tarea(id))");

        db.execSQL("create table tarea (id integer primary key autoincrement, " +
                "nombre text)");

        db.execSQL("create table caracteristica_profesor (id_profesor integer, id_caracteristica integer," +
                "foreign key(id_profesor) references profesores(id)," +
                "foreign key(id_caracteristica) references caracteristicas(id))");


        db.execSQL("insert into usuarios values('0','admin','admin')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("create table usuarios (id integer primary key autoincrement, " +
                "nombre text, clave text)");

        db.execSQL("create table profesores (id integer primary key autoincrement, " +
                "nombre text, detalle text,foreign key(id_usuario) references usuarios(id))");


        db.execSQL("create table materias (id integer primary key autoincrement, " +
                "nombre text, id_profesor integer, id_periodo integer, detalle text, " +
                "foreign key(id_profesor) references profesores (id)," +
                "foreign key(id_periodo) references periodo(id))");

        db.execSQL("create table periodo (id integer primary key autoincrement, " +
                "nombre text, id_usuario integer, fechainicio text, fechacierre text," +
                "foreign key(id_usuario) references usuarios(id))");

        db.execSQL("create table caracteristicas (id integer primary key autoincrement, " +
                "nombre text)");

        db.execSQL("create table asignacion (id integer primary key autoincrement, " +
                "detalle text, id_materia integer, fecha integer, id_periodo integer, id_tarea integer," +
                "foreign key(id_materia) references materias(id)," +
                "foreign key(id_periodo) references periodo(id)," +
                "foreign key(id_tarea) references tarea(id))");

        db.execSQL("create table tarea (id integer primary key autoincrement, " +
                "nombre text)");

        db.execSQL("create table caracteristica_profesor (id_profesor integer, id_caracteristica integer," +
                "foreign key(id_profesor) references profesores(id)," +
                "foreign key(id_caracteristica) references caracteristicas(id))");


        db.execSQL("insert into usuarios values('0','admin','admin')");
    }
}
