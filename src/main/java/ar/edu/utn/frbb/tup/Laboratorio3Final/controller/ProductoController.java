package ar.edu.utn.frbb.tup.Laboratorio3Final.controller;

import ar.edu.utn.frbb.tup.Laboratorio3Final.business.CategoriaBusiness;
import ar.edu.utn.frbb.tup.Laboratorio3Final.business.Implementation.CategoriaBusinessImplementation;
import ar.edu.utn.frbb.tup.Laboratorio3Final.business.ProductoBusiness;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dto.AltaCategoriaDto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dto.AltaProductoDto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Producto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.singletons.CategoriaBusinessImplementationSingleton;
import ar.edu.utn.frbb.tup.Laboratorio3Final.singletons.ProductoBusinessImplementationSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class ProductoController {
    @Autowired
    ProductoBusiness productoBusiness = ProductoBusinessImplementationSingleton.getInstance();
    @Autowired
    CategoriaBusiness categoriabusiness = CategoriaBusinessImplementationSingleton.getInstance();

    @ManagedOperation(description = "Crear Producto")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/producto", method = RequestMethod.POST, consumes = "application/json")
    public String crearProducto(@RequestBody AltaProductoDto productoDto, @RequestParam(value = "idCategoria") int idCategoria) {
        AltaCategoriaDto categoriaDto = new AltaCategoriaDto("test categoria", "test descripcion");
        categoriaDto.setId(idCategoria);
        if (categoriabusiness.consultarCategoriaPorId(categoriaDto.getId()) == null) {
            return "No existe una categoria con el id " + idCategoria;
        }
        return "Creo el producto: "+productoBusiness.altaProducto(productoDto, categoriaDto).toString();
    }

    @ManagedOperation(description = "Eliminar Producto")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/producto/{id}", method = RequestMethod.DELETE)
    public String eliminarProducto(@PathVariable int id) {
        AltaProductoDto productoDto = new AltaProductoDto(id);
        return productoBusiness.bajaProducto(productoDto) ? "El producto fue eliminado, con el id " + id : "No fue encontrada ningun producto con el id " + id;
    }

    @ManagedOperation(description = "Editar Producto")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/producto/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public String editarProducto(@PathVariable int id, @RequestBody AltaProductoDto productoDto) {
        if (productoBusiness.getProductoPorId(id) == null) {
            return "No fue encontrada ningun producto con el id " + id;
        }
        if (categoriabusiness.consultarCategoriaPorId(productoDto.getCategoriaId())!= null) {
            return "no fue encontrada ninguna cateogoria con el id " + productoDto.getCategoriaId();
        }
        return "se pudo editar correctamente el producto: "+productoBusiness.modificarProducto(productoDto).toString();
    }

    @ManagedOperation(description = "Obtener un Producto por id")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/producto/{id}", method = RequestMethod.GET, produces = "application/json")
    public String editarProducto(@PathVariable int id) {
        if (productoBusiness.getProductoPorId(id) == null) {
            return "No fue encontrada ningun producto con el id " + id;
        }
        return productoBusiness.getProductoPorId(id).toString();
    }

    /*
     Consultar un producto por alguno de sus atributos: tipo, marca, categoria
        GET: /producto?tipo_producto=dvd&marca=samsung&cateogoria=ATV
        GET: /producto?tipo_producto=tv&marca=phillips&categoria=ATV
     */
    @ManagedOperation(description = "Consultar un Producto por alguno de sus atributos: tipo, marca, categoria")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/producto", method = RequestMethod.GET)
    public String consultarProducto(@RequestParam(value = "tipo", required = false) String tipo, @RequestParam(value = "marca", required = false) String marca, @RequestParam(value = "categoria", required = false) String categoria){
        if (Objects.equals(tipo, "") && Objects.equals(marca, "") && Objects.equals(categoria, "")) {
            return "No se recibieron parametros para la consulta";
        }
        List<Producto> productos = productoBusiness.getProductosPorAtributos(tipo, marca, categoria);
        return productos.isEmpty() ? "No se encontraron productos con los parametros de busqueda" : productos.toString();
    }


}
