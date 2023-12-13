package ar.edu.utn.frbb.tup.Laboratorio3Final.dao;

import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Categoria;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Producto;

import java.util.ArrayList;
import java.util.List;

public interface CategoriaDao {
    Categoria guardar(Categoria c);

    Categoria editar(Categoria c);

    boolean eliminar(Categoria c);

    Categoria getCategoriaPorId(int id);

    List<Producto> getProductosOrdenadosPorPreciosAsc(Categoria c);

    List<Producto> getProductosOrdenadosPorPreciosDesc(Categoria c);

    List<Producto> getProductosPorMarca(Categoria c, String marca);

    List<Producto> getProductosFiltradosPorPrecios(Categoria categoria, double precioMin, double precioMax);

    ArrayList<Categoria> getCategorias();
}
