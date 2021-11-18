package dev.v7.photo.persistence.entidades;

public class Photo {
    private String _id ;
    private String nombre;
    private String arroba;

    public Photo() {
    }

    public Photo(String _id, String nombre, String arroba) {
        this._id = _id;
        this.nombre = nombre;
        this.arroba = arroba;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArroba() {
        return arroba;
    }

    public void setArroba(String arroba) {
        this.arroba = arroba;
    }
}
