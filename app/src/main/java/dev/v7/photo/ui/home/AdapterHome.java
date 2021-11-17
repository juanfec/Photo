package dev.v7.photo.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dev.v7.photo.R;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolderHome> {

    private String [] arrayNombres;
    private String [] arrayArrobas;
    private Context context;

    public AdapterHome(Context context, String [] arrayNombres, String [] arrayArrobas){
        this.arrayNombres = arrayNombres;
        this.arrayArrobas = arrayArrobas;
        this.context = context;
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
        holder.textViewArroba.setText(arrayArrobas[position]);
        holder.textViewNombre.setText(arrayNombres[position]);
    }

    @Override
    public int getItemCount() {
        return arrayNombres.length;
    }

    public class ViewHolderHome extends RecyclerView.ViewHolder{
        public TextView  textViewNombre, textViewArroba;
        public ViewHolderHome(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.nombretarjeta);
            textViewArroba = itemView.findViewById(R.id.arrobatarjeta);
        }
    }
}
