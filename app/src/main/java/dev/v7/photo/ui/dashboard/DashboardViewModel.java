package dev.v7.photo.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.v7.photo.persistence.entidades.Usuario;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}