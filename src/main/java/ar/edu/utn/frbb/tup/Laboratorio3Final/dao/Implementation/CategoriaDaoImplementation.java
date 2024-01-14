package ar.edu.utn.frbb.tup.Laboratorio3Final.dao.Implementation;

import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Categoria;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Producto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dao.CategoriaDao;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CategoriaDaoImplementation implements CategoriaDao {

    public List<Categoria> categorias = new ArrayList<>();

    public Categoria guardar(Categoria c) {
        if (getCategoriaPorId(c.getId()) != null)
        {
            System.out.println("Ya existe una categoria con ese id, no se puede crear otra.");
            return null;
        }
        else
        categorias.add(c);
        System.out.println("Se guardó la categoria en memoria correctamente.");
        return c;
    }

    public Categoria editar(Categoria categoriaActualizado) {
        try{
            for (Categoria categoria : categorias) {
                if (categoria.getId() == categoriaActualizado.getId()) {
                    categoria.setNombre(categoriaActualizado.getNombre());
                    categoria.setDescripcion(categoriaActualizado.getDescripcion());
                }
                System.out.println("Se editó la categoria en memoria correctamente.");
                return categoria;

            }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            return null;
        }
        return categoriaActualizado;
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
        System.out.println("Se encontraron los productos ordenados por precio ascendente.");
        return c.getListaProductos()
                .stream()
                .sorted(Comparator.comparingDouble(Producto::getPrecioLista))
                .collect(Collectors.toList());
    }

    public List<Producto> getProductosOrdenadosPorPreciosDesc(Categoria c) {
        System.out.println("Se encontraron los productos ordenados por precio descendente.");
        return c.getListaProductos()
                .stream()
                .sorted(Comparator.comparingDouble(Producto::getPrecioLista).reversed())
                .collect(Collectors.toList());

    }

    public List<Producto> getProductosPorMarca(Categoria c, String marca) {
        System.out.println("Se encontraron los productos filtrados por marca.");
        return c.getListaProductos()
                .stream()
                .filter(producto -> producto.getMarca().equals(marca))
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> getProductosFiltradosPorPrecios(Categoria categoria, double precioMin, double precioMax) {
        System.out.println("Se encontraron los productos filtrados por precio.");
        return categoria.getListaProductos()
                .stream()
                .filter(producto -> producto.getPrecioLista() >= precioMin && producto.getPrecioLista() <= precioMax)
                .collect(Collectors.toList());
    }
}
