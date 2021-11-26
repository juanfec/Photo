package dev.v7.photo.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import dev.v7.photo.persistence.entidades.Photo;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "PhotoDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME_PHOTOS = "photos";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "nombre";
    private static final String COLUMN_ARROBA = "arroba";
    private static final String COLUMN_URL = "url";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME_PHOTOS +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_ARROBA + " TEXT, " +
                COLUMN_URL + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PHOTOS);
        String query = "CREATE TABLE " + TABLE_NAME_PHOTOS +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_ARROBA + " TEXT, " +
                COLUMN_URL + " TEXT);";
        db.execSQL(query);
        onCreate(db);
    }

    public void addPhoto(String name, String arroba, String url){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_ARROBA, arroba);
        cv.put(COLUMN_URL,url);
        long result = db.insert(TABLE_NAME_PHOTOS,null, cv);
        if(result == -1){
            Log.d("Failed", "Failed");
        }else {
            Log.d("Added Successfully!", "Added Successfully!");
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME_PHOTOS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void actualizarPhoto(Photo photo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, photo.getNombre());
        cv.put(COLUMN_ARROBA, photo.getArroba());
        cv.put(COLUMN_URL,photo.getUrl());


        long result = db.update(TABLE_NAME_PHOTOS, cv, "_id=?", new String[]{photo.get_id()});
        if(result == -1){
            Log.d("SQLITE","Updated Failed");
        }else {
            Log.d("SQLITE","Updated Successfully!");
        }

    }

    public Cursor leerDatoPorId(String id){
        String query = "SELECT * FROM " + TABLE_NAME_PHOTOS + " WHERE _id = "+ id;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void deleteAll(){
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PHOTOS);
    }
}
