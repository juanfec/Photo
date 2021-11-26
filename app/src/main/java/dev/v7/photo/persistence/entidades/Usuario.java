package dev.v7.photo.persistence.entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Usuario {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String nombre;
    @NonNull
    private String correo;
    @NonNull
    private String imageUrl;

    public Usuario() {
    }

    public Usuario(@NonNull String id, @NonNull String nombre, @NonNull String correo,@NonNull String imageUrl) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.imageUrl = imageUrl;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(@NonNull String correo) {
        this.correo = correo;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NonNull String imageUrl) {
        this.imageUrl = imageUrl;
    }
}