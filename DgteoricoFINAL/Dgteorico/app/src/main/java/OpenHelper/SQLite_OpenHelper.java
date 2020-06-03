package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//Esta clase va a servir para armar la estructura de la base de datos ( tablas, metodos...)
//Extendemos la clase a la clase SQLiteOpenHelper

public class SQLite_OpenHelper extends SQLiteOpenHelper {

    //Constructor
    public SQLite_OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Para crear la estructura de la bbdd
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creamos la tabla
        String query="create table usuarios(_ID integer primary key autoincrement, Nombre text, Edad number, Correo text, Contraseña text);";
        //Permite ejecutar la sentencia
        db.execSQL(query);

    }
    //Para modificar la estructura de la bbdd
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Metodo que permite abrir la base de datos para escribir en ella
    public void abrir(){
        this.getWritableDatabase();
    }

    //Metodo que permite cerrar la base de datos
    public void cerrar(){
        this.close();
    }

    //Metodo que permite insertar registros en la tabla usuarios
    public void insertarReg(String nom, String eda, String cor, String contra){
        //Colocamos los valores dentro de un contentvalues
        ContentValues valores=new ContentValues();
        //Como el contentvalues esta vacio, le agregamos valores
        //Ponemos el nombre de cada uno de los campos de nuestra tabla con su parámetro de insertarReg
        valores.put("Nombre",nom);
        valores.put("Edad",eda);
        valores.put("Correo",cor);
        valores.put("Contraseña",contra);
        //Solo estan almacenados en el contentvalues, ahora los grabamos en la tabla
        //Primero ponemos la tabla donde insertar los valores, luego null, luego los valores a insertar en la tabla
        this.getWritableDatabase().insert("usuarios",null,valores);
    }

    //Metodo que permite validar si el usuario existe
    public Cursor ConsultarUsuPas(String usu, String pas) throws SQLException{
        //Declaramos el cursor que va a ser devuelto (vacio)
        Cursor mcursor=null;
        //Leemos la base de datos y la variable la guardamos en mcursor
        //La tabla usuarios con sus columnas y la consulta
        mcursor=this.getReadableDatabase().query("usuarios", new String[]{"_ID","Nombre","Edad","Correo","Contraseña"},"Correo like '"+usu+"' and Contraseña like '"+pas+"'", null,null,null,null);

        return mcursor;
    }
}
