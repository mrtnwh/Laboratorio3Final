package ar.edu.utn.frbb.tup.Laboratorio3Final.dao;



import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Categoria;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Producto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.singletons.CategoriaDaoSingleton;
import ar.edu.utn.frbb.tup.Laboratorio3Final.singletons.ProductoDaoSingleton;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
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
}
