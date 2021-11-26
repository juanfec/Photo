package dev.v7.photo.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import dev.v7.photo.R;
import dev.v7.photo.databinding.FragmentNotificationsBinding;
import dev.v7.photo.persistence.DBHelper;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        binding.enviarDatosSqlite.setOnClickListener(v -> {
            if(binding.nombreEditarSqlite.getText().toString().isEmpty()||binding.arrobaEditarSqlite.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
            }else{
                DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
                dbHelper.addPhoto(binding.nombreEditarSqlite.getText().toString(),binding.arrobaEditarSqlite.getText().toString(),binding.urleditarPhoto.getText().toString());
                Log.e("dbsave","se guardo correctamente");
            }
        });
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}