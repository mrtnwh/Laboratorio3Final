package ar.edu.utn.frbb.tup.Laboratorio3Final.model;

import java.util.ArrayList;

public class Categoria {
    private String nombre;
    private int id;
    private String descripcion;
    private ArrayList<Producto> listaProductos = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        listaProductos.remove(producto);
    }

    public Categoria(String nombre, int id, String descripcion) {
        this.nombre = nombre;
        this.id = id;
        this.descripcion = descripcion;
    }

    public Categoria(String nombre, int id, String descripcion, ArrayList<Producto> listaProductos) {
        this.nombre = nombre;
        this.id = id;
        this.descripcion = descripcion;
        this.listaProductos = listaProductos;
    }

    public Categoria(){}

    @Override
    public String toString() {
        return "Categoria{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", listaProductos=" + listaProductos +
                '}';
    }
}
