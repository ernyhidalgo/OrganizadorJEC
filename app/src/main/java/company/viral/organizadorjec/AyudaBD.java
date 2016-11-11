package company.viral.organizadorjec;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by erny on 06/11/2016.
 */

public class AyudaBD extends SQLiteOpenHelper{
    /*constructor de la base de datos */
    /*cargo directamente desde el super*/
    /* (context cuando llamamos a la clase, el nombre de la base de datos, factory queda igual, version=1*/
    public AyudaBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "BDJEC", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*con este constructor creamos una tabla de la base de datos */
        /*ella recibe un parametro "db" asi q trabajamos con db.execSQL y le decimos q cree la tabla llamada Usuarios y los campos*/
        db.execSQL("create table usuario(nombre text, clave texto)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*Creamos el metodo Guardar el cual en este caso recibira solo el usuario u la clave*/


    public String guardar (String auxn,String auxp){
        /*creamos un string paquete q recibira los datos*/

        String paquete="";
        /*sentenciamos la bd  de forma q sera escrivible */
        SQLiteDatabase database = this.getWritableDatabase();
        /*creamos un contenedor para guardar los datos*/
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre",auxn);
        contenedor.put("clave", auxp);
        /*armamos el try catch para saber si la bae de datos guarda de forma correcta */
        try{
            database.insertOrThrow("usuario",null,contenedor);
        }catch (SQLException e){
            paquete="error en la base de datos";
        }
        return paquete;
    }
}
