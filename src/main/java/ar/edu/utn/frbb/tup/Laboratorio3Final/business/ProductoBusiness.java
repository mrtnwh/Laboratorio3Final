package ar.edu.utn.frbb.tup.Laboratorio3Final.business;

import ar.edu.utn.frbb.tup.Laboratorio3Final.dto.AltaCategoriaDto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dto.AltaProductoDto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Producto;

import java.util.List;
import java.util.Map;

public interface ProductoBusiness {
    Producto altaProducto(AltaProductoDto productoDto, AltaCategoriaDto catDto);
    Producto modificarProducto(AltaProductoDto productoDto);
    boolean bajaProducto(AltaProductoDto productoDto);
    Producto getProductoPorId(int id);
    List<Producto> getProductosPorAtributos(String tipo, String marca, String categoria);

}
