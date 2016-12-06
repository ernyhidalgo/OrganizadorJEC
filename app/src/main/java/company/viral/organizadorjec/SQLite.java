package company.viral.organizadorjec;

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
        db.execSQL("create table Usuarios (id_usuario integer primary key autoincrement, " +
                "usuario text, clave text)");

        db.execSQL("create table PeriodoC (id_periodo integer primary key autoincrement, " +
                "nombre_periodo text, id_usuario2 integer");

        db.execSQL("create table Materias (id_materia integer primary key autoincrement, " +
                "nombre_materia text, id_periodo2 integer, id_profesor2 integer");

        db.execSQL("create table Asignaciones (id_asignacion integer primary key autoincrement, " +
                "nombre_asignacion text,");

        db.execSQL("create table Tareas (id_tarea integer primary key autoincrement, " +
                "id_asignacion integer, id_materia2 integer, id_profesor3 integer, " +
                "descripcion_tarea text");

        db.execSQL("create table Profesores (id_profesor integer primary key autoincrement," +
                "nombre_profesor text");

        db.execSQL("create table Caracteristicas (id_caracteristica integer primary key " +
                "autoincrement, nombre_caracteristica text");

        db.execSQL("create table ProfesorCaracteristica (id_profesor4 integer, id_caracteristica2 integer");


        db.execSQL("insert into usuarios values('0','admin','admin')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table Usuarios (id_usuario integer primary key autoincrement, " +
                "usuario text, clave text)");

        db.execSQL("create table PeriodoC (id_periodo integer primary key autoincrement, " +
                "nombre_periodo text, id_usuario2 integer");

        db.execSQL("create table Materias (id_materia integer primary key autoincrement, " +
                "nombre_materia text, id_periodo2 integer, id_profesor2 integer");

        db.execSQL("create table Asignaciones (id_asignacion integer primary key autoincrement, " +
                "nombre_asignacion text,");

        db.execSQL("create table Tareas (id_tarea integer primary key autoincrement, " +
                "id_asignacion integer, id_materia2 integer, id_profesor3 integer, " +
                "descripcion_tarea text");

        db.execSQL("create table Profesores (id_profesor integer primary key autoincrement," +
                "nombre_profesor text");

        db.execSQL("create table Caracteristicas (id_caracteristica integer primary key " +
                "autoincrement, nombre_caracteristica text");

        db.execSQL("create table ProfesorCaracteristica (id_profesor4 integer, id_caracteristica2 integer");
        db.execSQL("insert into usuarios values('0','admin','admin')");


    }
}
