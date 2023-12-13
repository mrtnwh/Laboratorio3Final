package ar.edu.utn.frbb.tup.Laboratorio3Final.dto;

import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class AltaCategoriaDto {
    private int id;
    private String nombre;
    private String descripcion;
    private List<Producto> listaProductos;

    public AltaCategoriaDto(int id, String nombre, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public AltaCategoriaDto() {}
    public AltaCategoriaDto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public AltaCategoriaDto(int id, String nombre, String descripcion, List<Producto> listaProductos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.listaProductos = listaProductos;
    }

}
