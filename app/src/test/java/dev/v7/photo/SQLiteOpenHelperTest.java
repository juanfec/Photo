package dev.v7.photo;


import android.database.Cursor;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import dev.v7.photo.persistence.DBHelper;
import dev.v7.photo.persistence.entidades.Photo;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,   sdk = {21},
        packageName = "dev.v7.photo")
public class SQLiteOpenHelperTest{
    public DBHelper dbHelper;


    @Before
    public void setUpDatabase(){
        dbHelper = new DBHelper(RuntimeEnvironment.application);
    }

    @Test
    public void testDbInsertion() throws Exception {
        String nombre = "juan";
        String arroba = "@juan";

        dbHelper.addPhoto(nombre, arroba,"");
        Cursor cursor = dbHelper.readAllData();
        List<Photo> lista =new ArrayList<>();
        if(cursor.getCount() == 0){
        }else{
            while (cursor.moveToNext()){
                Photo photo = new Photo(cursor.getString(0)
                        ,cursor.getString(1)
                        ,cursor.getString(2)
                        ,cursor.getString(3));
                lista.add(photo);
            }
        }

        Assert.assertEquals(lista.get(0).getNombre(),nombre);
        Assert.assertEquals(lista.get(0).getArroba(),arroba);
    }

    @Test
    public void test(){
        Assert.assertEquals(1,1);
    }

    @After
    public void borrarDb(){
        dbHelper.deleteAll();
    }
}