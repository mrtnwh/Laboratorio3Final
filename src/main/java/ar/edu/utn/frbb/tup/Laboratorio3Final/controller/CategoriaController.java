package ar.edu.utn.frbb.tup.Laboratorio3Final.controller;

import ar.edu.utn.frbb.tup.Laboratorio3Final.business.CategoriaBusiness;
import ar.edu.utn.frbb.tup.Laboratorio3Final.business.ProductoBusiness;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dto.AltaCategoriaDto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dto.AltaProductoDto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Categoria;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Producto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.singletons.CategoriaBusinessImplementationSingleton;
import ar.edu.utn.frbb.tup.Laboratorio3Final.singletons.ProductoBusinessImplementationSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoriaController {
    @Autowired
    CategoriaBusiness categoriaBusiness = CategoriaBusinessImplementationSingleton.getInstance();

    ProductoBusiness productoBusiness = ProductoBusinessImplementationSingleton.getInstance();


    @ManagedOperation(description = "Crea una nueva categoria")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/categoria", method= RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Categoria crearCategoria(@RequestBody AltaCategoriaDto categoriaDto) {
        return categoriaBusiness.altaCategoria(categoriaDto);
    }

    @ManagedOperation(description = "Elimina una categoria")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/categoria/{id}", method= RequestMethod.DELETE)
    public String eliminarCategoria(@PathVariable("id") int id) {
        AltaCategoriaDto categoriaDto = new AltaCategoriaDto(id);
        return categoriaBusiness.bajaCategoria(categoriaDto) ? "la cateogoria fue eliminada, con el id "+ id : "no se encontro ninguna categoria con id "+ id + " o no se puede borrar, porque tiene productos";
    }

    @ManagedOperation(description = "Editar una categoria")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/categoria/{id}", method= RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public String editarCategoria(@PathVariable("id") int id, @RequestBody AltaCategoriaDto categoriaDto) {
        //TODO:cuando hago el edit, se borran los productos asociados a la categoria
        if (categoriaBusiness.consultarCategoriaPorId(id) == null) {
            return "no existe ninguna categoria con el id "+ id;
        }
        return categoriaBusiness.modificarCategoria(categoriaDto).toString();
    }

    @ManagedOperation(description = "Obtener una categoria por id")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/categoria/{id}", method= RequestMethod.GET, produces = "application/json")
    public String obtenerCategoria(@PathVariable("id") int id) {
        if (categoriaBusiness.consultarCategoriaPorId(id) == null) {
            return "no existe ninguna categoria con el id "+ id;
        }
        else
            return categoriaBusiness.consultarCategoriaPorId(id).toString();

    }


    @ManagedOperation(description = "Obtener todos los productos ordenados por parametro opcional")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/categoria", method= RequestMethod.GET)
    public String obtenerProductosOrdenadosPorPrecio(@RequestBody AltaCategoriaDto categoriaDto,
                                                     @RequestParam(value = "order_price", required = false) String order_price,
                                                     @RequestParam(value = "marca", required = false) String marca,
                                                     @RequestParam(value = "maximo", required = false) Integer maximo,
                                                     @RequestParam(value = "minimo", required = false) Integer minimo) {
        if (order_price != null) {
        if (order_price.equals("asc")) {
            return categoriaBusiness.getProductosOrdenadosPorPrecioAsc(categoriaDto).toString();
        }
        else if (order_price.equals("desc")) {
        return categoriaBusiness.getProductosOrdenadosPorPrecioDesc(categoriaDto).toString();
        }
    }
        if (marca !=null) {
            return categoriaBusiness.getProductosPorMarca(categoriaDto, marca).toString();
        }

        if (minimo != 0 && maximo != 0) {
            return categoriaBusiness.getProductosFiltradosPorPrecios(categoriaDto,minimo,maximo).toString();
        }
        else
            return "malos parametros";
    }
}

