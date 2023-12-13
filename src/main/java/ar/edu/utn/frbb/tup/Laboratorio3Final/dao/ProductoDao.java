package ar.edu.utn.frbb.tup.Laboratorio3Final.dao;

import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Categoria;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Producto;

import java.util.List;

public interface ProductoDao {
    Producto guardar(Producto p, Categoria c);
    Producto editar(Producto p);
    boolean eliminar(Producto p);
    List<Producto> getListaProductos();
    Producto getProductosPorId(int id);
    List<Producto> getProductosPorAtributos(String tipo, String marca, String categoria);
}
