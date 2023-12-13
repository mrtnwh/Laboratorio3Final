package ar.edu.utn.frbb.tup.Laboratorio3Final.business.Implementation;

import ar.edu.utn.frbb.tup.Laboratorio3Final.business.ProductoBusiness;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dao.CategoriaDao;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dao.ProductoDao;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dto.AltaCategoriaDto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dto.AltaProductoDto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Categoria;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Producto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.singletons.CategoriaDaoSingleton;
import ar.edu.utn.frbb.tup.Laboratorio3Final.singletons.ProductoDaoSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class ProductoBusinessImplementation implements ProductoBusiness {
    public Producto p;
    @Autowired
    ProductoDao productoDao = ProductoDaoSingleton.getInstance();

    @Autowired
    CategoriaDao catDao = CategoriaDaoSingleton.getInstance();

    public Producto altaProducto(AltaProductoDto prodDto, AltaCategoriaDto catDto) {
        Producto p = new Producto();
        p.setNombre(prodDto.getNombre());
        p.setCategoria(catDto.getNombre());
        p.setId(prodDto.getId());
        p.setCategoriaId(catDto.getId());
        p.setMarca(prodDto.getMarca());
        p.setModelo(prodDto.getModelo());
        Categoria c = catDao.getCategoriaPorId(catDto.getId());

        productoDao.guardar(p, c);
        return p;
    }

    public Producto modificarProducto(AltaProductoDto dto) {
        try{
            p = productoDao.getProductosPorId(dto.getId());
            p.setNombre(dto.getNombre());
            p.setDescripcion(dto.getDescripcion());
            p.setCategoriaId(dto.getCategoriaId());
            p.setMarca(dto.getMarca());
            p.setModelo(dto.getModelo());
            p.setPrecioLista(dto.getPrecioLista());
            p.setTipo(dto.getTipo());
        } catch (NoSuchElementException e) {
            System.out.println(""+e.getMessage());
            return null;
        }
        return productoDao.editar(p);

    }

    public boolean bajaProducto(AltaProductoDto dto) {
        try{
            p = productoDao.getProductosPorId(dto.getId());
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            return false;
        }
        return productoDao.eliminar(p);
    }

    public Producto getProductoPorId(int id) {
        try{
            return productoDao.getProductosPorId(id);
        } catch (NoSuchElementException e) {
            System.out.println("El id de producto ingresado no existe "+e.getMessage());;
            return null;
        }
    }

    public List<Producto> getProductosPorAtributos(String tipo, String marca, String categoria) {
        return productoDao.getProductosPorAtributos(tipo, marca, categoria);
    }

}
