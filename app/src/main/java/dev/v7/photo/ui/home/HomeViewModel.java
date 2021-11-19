package dev.v7.photo.ui.home;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import dev.v7.photo.persistence.DBHelper;
import dev.v7.photo.persistence.entidades.Photo;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<Photo>> photos;
    private DBHelper dbHelper;
    private Context context = getApplication().getApplicationContext();
    private ToastHomeFragment toastHomeFragment;

    public HomeViewModel(@NonNull Application application) {
        super(application);

    }

    public void setToastHomeFragment(ToastHomeFragment toastHomeFragment) {
        this.toastHomeFragment = toastHomeFragment;
    }

    public LiveData<List<Photo>> getPhotos() {
        if (photos == null) {
            photos = new MutableLiveData<List<Photo>>();
            loadUsers();
        }
        return photos;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void updatePhotos(){
        dbHelper = new DBHelper(context);
        Cursor cursor = dbHelper.readAllData();
        List<Photo> lista =new ArrayList<>();
        if(cursor.getCount() == 0){
        }else{
            while (cursor.moveToNext()){
                Photo photo = new Photo(cursor.getString(0)
                        ,cursor.getString(1)
                        ,cursor.getString(2));
                lista.add(photo);
            }
        }
        toastHomeFragment.showToast("se actualizaron las fotos");
        photos.setValue(lista);
    }

    private void loadUsers() {
        dbHelper = new DBHelper(context);
        Cursor cursor = dbHelper.readAllData();
        List<Photo> lista =new ArrayList<>();
        if(cursor.getCount() == 0){
        }else{
            while (cursor.moveToNext()){
                Photo photo = new Photo(cursor.getString(0)
                        ,cursor.getString(1)
                        ,cursor.getString(2));
                lista.add(photo);
            }
        }
        toastHomeFragment.showToast("se cargaron las fotos");
        photos.postValue(lista);
    }
}