package ar.edu.utn.frbb.tup.Laboratorio3Final.dao.Implementation;

import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Categoria;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Producto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dao.CategoriaDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CategoriaDaoImplementation implements CategoriaDao {

    public List<Categoria> categorias = new ArrayList<>();

    public Categoria guardar(Categoria c) {
        categorias.add(c);
        System.out.println("Se guardó la categoria en memoria correctamente.");
        return c;
    }

    public Categoria editar(Categoria c) {
        //TODO: CRUD EDIT Dao
        return null;
    }

    public boolean eliminar(Categoria c) {
        if (c != null){
            categorias.remove(c);
            System.out.println("Se elimino la categoria indicada correctamente.");
            return true;
        }
        return false;
    }

    public Categoria getCategoriaPorId(int id) {
        Categoria categoriaEncontrada = null;
        if (!categorias.isEmpty()){
            for (Categoria categoria : categorias){
                if (categoria.getId() == (id)){
                    categoriaEncontrada = categoria;
                    break;
                }
            }
        }
        if (categoriaEncontrada == null) {
            System.out.println("La categoría no existe");
        }
        return categoriaEncontrada;
    }

    public ArrayList<Categoria> getCategorias() {
        if (categorias.size() == 0){throw new RuntimeException("No hay categorias a mostrar.");}
        ArrayList<Categoria> listaCategorias = new ArrayList<>();
        for (Categoria categoria : categorias) {
            listaCategorias.add(categoria);
        }
        return listaCategorias;
    }

    public List<Producto> getProductosOrdenadosPorPreciosAsc(Categoria c) {
        return c.getListaProductos()
                .stream()
                .sorted(Comparator.comparingDouble(Producto::getPrecioLista))
                .collect(Collectors.toList());
    }

    public List<Producto> getProductosOrdenadosPorPreciosDesc(Categoria c) {
        return c.getListaProductos()
                .stream()
                .sorted(Comparator.comparingDouble(Producto::getPrecioLista).reversed())
                .collect(Collectors.toList());

    }

    public List<Producto> getProductosPorMarca(Categoria c, String marca) {
        return c.getListaProductos()
                .stream()
                .filter(producto -> producto.getMarca().equals(marca))
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> getProductosFiltradosPorPrecios(Categoria categoria, double precioMin, double precioMax) {
        return categoria.getListaProductos()
                .stream()
                .filter(producto -> producto.getPrecioLista() >= precioMin && producto.getPrecioLista() <= precioMax)
                .collect(Collectors.toList());
    }
}
