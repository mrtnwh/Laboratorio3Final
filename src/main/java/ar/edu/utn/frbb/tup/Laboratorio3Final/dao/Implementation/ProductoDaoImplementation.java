package ar.edu.utn.frbb.tup.Laboratorio3Final.dao.Implementation;

import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Categoria;
import ar.edu.utn.frbb.tup.Laboratorio3Final.singletons.CategoriaDaoSingleton;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Producto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dao.CategoriaDao;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dao.ProductoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class ProductoDaoImplementation implements ProductoDao {
    private final List<Producto> productos = new ArrayList<>();

    @Autowired
    CategoriaDao categoriaDao = CategoriaDaoSingleton.getInstance();

    public Producto guardar(Producto p, Categoria c) {
        c = categoriaDao.getCategoriaPorId(p.getCategoriaId());
        if (c != null) {
            c.agregarProducto(p);
            productos.add(p);
            System.out.println("Se guardó el producto en memoria correctamente.");
            return p;
        } else {
            System.out.println("No se pudo encontrar la categoría con id: " + p.getCategoriaId());
            return null;
        }
    }

    public Producto editar(Producto productoActualizado) {
        try{
            for (Producto producto : productos) {
                if (producto.getId() == productoActualizado.getId()) {
                    producto.setNombre(productoActualizado.getNombre());
                    producto.setCategoriaId(productoActualizado.getCategoriaId());
                    producto.setMarca(productoActualizado.getMarca());
                    producto.setModelo(productoActualizado.getModelo());
                    producto.setPrecioLista(productoActualizado.getPrecioLista());
                }
                System.out.println("Se editó el producto en memoria correctamente.");
                return producto;

        }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            return null;
        }
        return productoActualizado;
        }


    public boolean eliminar(Producto p) {
        try{
            Categoria c = categoriaDao.getCategoriaPorId(p.getCategoriaId());
            c.eliminarProducto(p);
            productos.remove(p);
            System.out.println("Se elimino el producto indicado correctamente.");
            return true;
        }catch (NoSuchElementException e){
            System.out.println("No se pudo eliminar el producto, "+e.getMessage());
        }
        return false;
    }

    public List<Producto> getListaProductos() {
        return productos;
    }

    public Producto getProductosPorId(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                System.out.println("Se encontro el producto indicado por id "+ id + ", el producto es "+ producto);
                return producto;
            }
        }
        System.out.println("No se encontro el producto :(");
        return null;
    }

    public List<Producto> getProductosPorAtributos(String tipo, String marca, String categoria) {
        System.out.println("Se encontro el producto por atributos");
        return getListaProductos().stream()
                .filter(producto -> producto.getTipo().equalsIgnoreCase(tipo))
                .filter(producto -> producto.getMarca().equalsIgnoreCase(marca))
                .filter(producto -> producto.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());

    }
}
