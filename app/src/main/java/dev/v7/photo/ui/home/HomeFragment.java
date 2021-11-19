package dev.v7.photo.ui.home;

import android.database.Cursor;
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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import dev.v7.photo.R;
import dev.v7.photo.databinding.FragmentHomeBinding;
import dev.v7.photo.persistence.DBHelper;
import dev.v7.photo.persistence.entidades.Photo;

public class HomeFragment extends Fragment implements ToastHomeFragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private FirebaseAuth mAuth;
    private String name;
    private List<Photo> arregloDeFotos = new ArrayList<>();
    private RecyclerView recyclerViewHome;
    private DBHelper dbHelper;
    private AdapterHome adapterHome;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.setToastHomeFragment(this);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();

        getUser();
        dbHelper = new DBHelper(getActivity().getApplicationContext());
        storeDataInArrays();
        binding.irAMapas.setOnClickListener(v -> {
            Navigation.findNavController(root).navigate(R.id.action_navigation_home_to_mapsFragment);
        });
        recyclerViewHome = binding.recyclerViewHome;
        adapterHome = new AdapterHome(getContext(),arregloDeFotos,root);
        recyclerViewHome.setAdapter(adapterHome);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewHome.setLayoutManager(linearLayoutManager);

        homeViewModel.getPhotos().observe(getViewLifecycleOwner(), (Observer<List<Photo>>) photos -> {
                    adapterHome.setArregloDeFotos(photos);
                    adapterHome.notifyDataSetChanged();
                });

        //homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
        //    @Override
        //    public void onChanged(@Nullable String s) {
        //        textView.setText(s);
        //    }
        //});
        return root;
    }

    private void getUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getEmail();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
    }

    private void storeDataInArrays(){

    }

    @Override
    public void onResume() {
        super.onResume();
        homeViewModel.updatePhotos();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showToast(String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
        Log.e(mensaje,mensaje);
    }


}