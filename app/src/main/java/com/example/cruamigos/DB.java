package com.example.cruamigos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

    public class DB extends SQLiteOpenHelper {
        static String nameDB = "db_amigos"; //declaracion de la instancia de la BD
        static String tblAmigos = "CREATE TABLE amigos(idAmigo integer primary key autoincrement, nombre text, telefono text, direccion text, email text, url text)";

        public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, nameDB, factory, version); //nameDB -> Creacion de la BD en SQLite...
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(tblAmigos);
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
        public Cursor mantenimientoAmigos(String accion, String[] data){
            SQLiteDatabase sqLiteDatabaseReadable=getReadableDatabase();
            SQLiteDatabase sqLiteDatabaseWritable=getWritableDatabase();
            Cursor cursor = null;
            switch (accion){
                case "consultar":
                    cursor=sqLiteDatabaseReadable.rawQuery("SELECT * FROM amigos ORDER BY nombre ASC", null);
                    break;
                case "nuevo":
                    sqLiteDatabaseWritable.execSQL("INSERT INTO amigos (nombre,telefono,direccion,email,url) VALUES('"+ data[1] +"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"')");
                    break;
                case "modificar":
                    sqLiteDatabaseWritable.execSQL("UPDATE amigos SET nombre='"+ data[1] +"',telefono='"+data[2]+"',direccion='"+data[3]+"',email='"+data[4]+"', url='"+data[5]+"' WHERE idAmigo='"+data[0]+"'");
                    break;
                case "eliminar":
                    sqLiteDatabaseWritable.execSQL("DELETE FROM amigos WHERE idAmigo='"+ data[0] +"'");
                    break;
            }
            return cursor;
        }
    }


