package ar.edu.utn.frbb.tup.Laboratorio3Final.business;

import ar.edu.utn.frbb.tup.Laboratorio3Final.dto.AltaCategoriaDto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Categoria;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Producto;

import java.util.List;

public interface CategoriaBusiness {

    Categoria altaCategoria(AltaCategoriaDto categoriaDto);
    Categoria modificarCategoria(AltaCategoriaDto categoriaDto);
    boolean bajaCategoria(AltaCategoriaDto categoriaDto);
    Categoria consultarCategoriaPorId(int id);
    List<Producto> getProductosPorMarca(AltaCategoriaDto dto, String marca);
    List<Producto> getProductosOrdenadosPorPrecioAsc(AltaCategoriaDto dto);
    List<Producto> getProductosOrdenadosPorPrecioDesc(AltaCategoriaDto dto);
    List<Producto> getProductosFiltradosPorPrecios(AltaCategoriaDto dto, double precioMin, double precioMax);
}
