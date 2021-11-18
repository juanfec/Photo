package dev.v7.photo.ui;

import androidx.lifecycle.ViewModelProvider;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import dev.v7.photo.R;
import dev.v7.photo.databinding.VistaPhotoIndividualConBindingFragmentBinding;
import dev.v7.photo.persistence.DBHelper;
import dev.v7.photo.persistence.entidades.Photo;

public class VistaPhotoIndividualConBinding extends Fragment {

    private VistaPhotoIndividualConBindingViewModel mViewModel;
    private Photo photo;
    private EditText nombre,arroba;
    private VistaPhotoIndividualConBindingFragmentBinding binding;
    private DBHelper dbHelper;

    public static VistaPhotoIndividualConBinding newInstance() {
        return new VistaPhotoIndividualConBinding();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = VistaPhotoIndividualConBindingFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.arrobaVistaIndividual.setText(photo.getArroba());
        binding.nombreVistaIndividual.setText(photo.getNombre());
        binding.guardarDatos.setOnClickListener(v -> {
            photo.setNombre(binding.nombreVistaIndividual.getText().toString());
            photo.setArroba(binding.arrobaVistaIndividual.getText().toString());
            dbHelper.actualizarPhoto(photo);
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VistaPhotoIndividualConBindingViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(getContext());
        if (getArguments() != null) {
            Cursor cursor = dbHelper.leerDatoPorId(getArguments().getString("id"));
            if(cursor.getCount() == 0){

            }else{
                while (cursor.moveToNext()){
                    photo = new Photo(cursor.getString(0)
                            ,cursor.getString(1)
                            ,cursor.getString(2));
                }
            }
        }
    }
}