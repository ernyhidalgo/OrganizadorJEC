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
        db.execSQL("create table usuarios (id integer primary key autoincremet, usuario text primary key, clave text)");
        db.execSQL("insert into usuarios values('0','admin','admin')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table usuarios (id integer primary key autoincremet, usuario text primary key, clave text)");
        db.execSQL("insert into usuarios values('0','admin','admin')");


    }
}
