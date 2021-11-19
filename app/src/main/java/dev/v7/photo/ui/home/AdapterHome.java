package dev.v7.photo.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.v7.photo.R;
import dev.v7.photo.persistence.entidades.Photo;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolderHome> {

    private List<Photo> arregloDeFotos;
    private Context context;
    private  View root;

    public AdapterHome(Context context, List<Photo> arregloDeFotos,  View root){
        this.arregloDeFotos = arregloDeFotos;
        this.context = context;
        this.root = root;
    }

    @NonNull
    @Override
    public ViewHolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tarjetaphoto,parent,false);
        return new ViewHolderHome(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHome holder, int position) {
        holder.textViewArroba.setText(arregloDeFotos.get(position).getArroba());
        holder.textViewNombre.setText(arregloDeFotos.get(position).getNombre());
        holder.mainLayout.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("id", arregloDeFotos.get(position).get_id());
            Navigation.findNavController(root).navigate(R.id.action_navigation_home_to_vistaIndividual,bundle);
        });

    }

    public void setArregloDeFotos(List<Photo> arregloDeFotos) {
        this.arregloDeFotos = arregloDeFotos;
    }

    @Override
    public int getItemCount() {
        return arregloDeFotos.size();
    }

    public class ViewHolderHome extends RecyclerView.ViewHolder{
        public TextView  textViewNombre, textViewArroba;
        public ConstraintLayout mainLayout;
        public ViewHolderHome(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.nombretarjeta);
            textViewArroba = itemView.findViewById(R.id.arrobatarjeta);
            mainLayout = itemView.findViewById(R.id.containerTarjeta);
        }
    }
}
