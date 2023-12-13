package ar.edu.utn.frbb.tup.Laboratorio3Final.business.Implementation;

import ar.edu.utn.frbb.tup.Laboratorio3Final.business.CategoriaBusiness;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dao.CategoriaDao;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dto.AltaCategoriaDto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Categoria;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Producto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.singletons.CategoriaDaoSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoriaBusinessImplementation implements CategoriaBusiness {
    Categoria c;
    @Autowired
    CategoriaDao categoriaDao = CategoriaDaoSingleton.getInstance();

    public Categoria altaCategoria(AltaCategoriaDto dto) {
        c = new Categoria();
        c.setNombre(dto.getNombre());
        c.setDescripcion(dto.getDescripcion());
        categoriaDao.guardar(c);
        return c;
    }

    public Categoria modificarCategoria(AltaCategoriaDto dto) {
        try{
            c = categoriaDao.getCategoriaPorId(dto.getId());
            c.setNombre(dto.getNombre());
            c.setDescripcion(dto.getDescripcion());
        } catch (NoSuchElementException e) {
            System.out.println(""+ e.getMessage());
            return null;
        }
        return categoriaDao.editar(c);
    }

    public boolean bajaCategoria(AltaCategoriaDto dto) {
        try{
            c = categoriaDao.getCategoriaPorId(dto.getId());
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            return false;
        }
        return categoriaDao.eliminar(c);
    }

    public Categoria consultarCategoriaPorId(int id) {
        try{
            return categoriaDao.getCategoriaPorId(id);

        } catch (NoSuchElementException e) {
            System.out.println("El id de categoria ingresado no existe, error: "+ e.getMessage());
            return null;
        }
    }

    public List<Producto> getProductosPorMarca(AltaCategoriaDto dto, String marca) {
        try{
            return categoriaDao.getProductosPorMarca(c, marca);
        } catch (NoSuchElementException e) {
            System.out.println("la marca ingresada no existe, error: "+ e.getMessage());
            return null;
        }
    }

    @Override
    public List<Producto> getProductosOrdenadosPorPrecioAsc(AltaCategoriaDto dto, String marca) {
        Categoria c = categoriaDao.getCategoriaPorId(dto.getId());
        return categoriaDao.getProductosPorPrecioAsc(c);
    }

    @Override
    public List<Producto> getProductosOrdenadosPorPrecioDesc(AltaCategoriaDto dto) {
        Categoria c = categoriaDao.getCategoriaPorId(dto.getId());
        return categoriaDao.getProductosPorPrecioDesc(c);
    }

    @Override
    public List<Producto> getProductosFiltradosPorPrecios(AltaCategoriaDto dto, double precioMin, double precioMax) {
        Categoria c = categoriaDao.getCategoriaPorId(dto.getId());
        return categoriaDao.getProductosFiltradosPorPrecios(c, precioMin, precioMax);
    }
}
