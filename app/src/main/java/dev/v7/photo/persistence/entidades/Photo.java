package dev.v7.photo.persistence.entidades;

public class Photo {
    private String _id ;
    private String nombre;
    private String arroba;
    private String url;

    public Photo() {
    }

    public Photo(String _id, String nombre, String arroba, String url) {
        this._id = _id;
        this.nombre = nombre;
        this.arroba = arroba;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
