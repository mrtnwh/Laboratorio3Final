package ar.edu.utn.frbb.tup.Laboratorio3Final.dao;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Categoria;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Producto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.singletons.CategoriaDaoSingleton;
import ar.edu.utn.frbb.tup.Laboratorio3Final.singletons.ProductoDaoSingleton;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ProductoDaoImplementationTest {
    private Map<String, String> especificaciones = new HashMap<>(){{
        put("Bateria", "4500mAh");
        put("Pantalla", "6.7");
        put("Tasa de refresco", "144hz");
        put("Resolucion de Pantalla", "2400x1080 px");
        put("Frecuencia del procesador", "3.2GHz");
    }};

    private CategoriaDao categoriaDao = CategoriaDaoSingleton.getInstance();
    private ProductoDao productoDao = ProductoDaoSingleton.getInstance();

    @Test
    public void test_guardar_categoria_y_productoOK() {
        Categoria cat = new Categoria("test nombre categoria", 22, "test descripcion categoria");
        Producto p = new Producto("Motorola Edge 20 Pro", "Celulares y SmartPhones", cat.getId(), 20, "Motorola", "Edge", "es un celular de gama alta que busca diferenciarse de la competencia por su excelente sistema de cámaras y su altísima tasa de respuesta táctil. Para diferenciarse aún más, cuenta con una versión con cuero vegano.", especificaciones, "Celular",2500, 2000, 2000, false, 0, null, false, null);

        categoriaDao.guardar(cat);
        productoDao.guardar(p,cat);
        assertNotNull(productoDao.getProductosPorId(p.getId()));
        assertFalse(cat.getListaProductos().isEmpty());
        assertTrue(cat.getListaProductos().contains(p));

        assertFalse(productoDao.getListaProductos().isEmpty());
        assertTrue(productoDao.getListaProductos().contains(p));
    }

    @Test
    public void test_editar_productoOK() {
        Categoria cat = new Categoria("test nombre categoria", 22, "test descripcion categoria");
        Producto p = new Producto("Motorola Edge 40", cat.getId(),20, "Motorola", "Edge", 125000);
        categoriaDao.guardar(cat);
        productoDao.guardar(p,cat);

        System.out.println("producto antes de editar: " + p);

        p.setNombre("Microfono HyperX SoloCast");
        p.setMarca("HyperX");
        p.setCategoria("Microfonos");
        p.setModelo("QXTS23");
        p.setPrecioLista(70000);
        productoDao.editar(p);
        System.out.println("producto despues de editar: " + p);

        Producto prodEncontrado = productoDao.getProductosPorId(p.getId());

        assertNotNull(prodEncontrado);
        assertEquals(p, prodEncontrado);
    }

    @Test
    public void test_eliminar_productoOK() {
        Categoria cat = new Categoria("test nombre categoria", 22, "test descripcion categoria");
        Producto p = new Producto("Motorola Edge 40", cat.getId(),20, "Motorola", "Edge", 125000);
        categoriaDao.guardar(cat);
        productoDao.guardar(p,cat);

        assertTrue(productoDao.eliminar(p));
        assertNull(productoDao.getProductosPorId(p.getId()));

    }

    @Test
    public void test_get_producto_por_idOK() {
        Categoria cat = new Categoria("test nombre categoria", 22, "test descripcion categoria");
        Producto p = new Producto("Motorola Edge 40", cat.getId(),20, "Motorola", "Edge", 125000);
        categoriaDao.guardar(cat);
        productoDao.guardar(p,cat);

        Producto productoEncontrado = productoDao.getProductosPorId(p.getId());
        assertNotNull(productoDao.getProductosPorId(p.getId()));
        assertEquals(productoEncontrado, p);

    }

    @Test
    public void test_get_producto_por_id_notValido() {
        Categoria cat = new Categoria("test nombre categoria", 1, "test descripcion categoria");
        Producto p = new Producto("Motorola Edge 40", cat.getId(),1, "Motorola", "Edge", 125000);
        categoriaDao.guardar(cat);
        productoDao.guardar(p,cat);

        Producto produdctoNovalido = productoDao.getProductosPorId(3);

        assertNull(produdctoNovalido);
    }

    @Test
    public void test_get_producto_por_atributosOK() {
        Categoria cat = new Categoria("test nombre categoria", 22, "test descripcion categoria");
        Producto p = new Producto(2,"Celulares", "Motorola Edge 40","descripcion", cat.getId(), "Motorola", "edge",125000,"Smartphone");
        categoriaDao.guardar(cat);
        productoDao.guardar(p,cat);

        List<Producto> productoPorAtributos = productoDao.getProductosPorAtributos(p.getTipo(), p.getMarca(), p.getCategoria());

        assertNotNull(productoPorAtributos);
        assertTrue(productoPorAtributos.contains(p));
        assertEquals(1, productoPorAtributos.size());
        //Afirmar si las categorias son iguales
        assertEquals(productoPorAtributos.get(0).getTipo(), p.getTipo());
        assertEquals(productoPorAtributos.get(0).getMarca(), p.getMarca());
        assertEquals(productoPorAtributos.get(0).getCategoria(), p.getCategoria());
    }

}
