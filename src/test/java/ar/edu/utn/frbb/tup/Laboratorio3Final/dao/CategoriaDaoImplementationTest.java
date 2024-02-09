package ar.edu.utn.frbb.tup.Laboratorio3Final.dao;


import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Categoria;
import ar.edu.utn.frbb.tup.Laboratorio3Final.model.Producto;
import ar.edu.utn.frbb.tup.Laboratorio3Final.singletons.CategoriaDaoSingleton;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CategoriaDaoImplementationTest {
    private CategoriaDao categoriaDao = CategoriaDaoSingleton.getInstance();
    @Test
    public void test_guardar_categoria(){
        Categoria cat = new Categoria("test nombre categoria", 22, "test descripcion categoria");
        Categoria categoriaGuardada = categoriaDao.guardar(cat);
        System.out.println(categoriaGuardada);
        assertNotNull(categoriaGuardada);
        assertEquals(cat, categoriaGuardada);
    }

    @Test
    public void test_editar_categoria(){
        Categoria cat = new Categoria("test nombre categoria", 22, "test descripcion categoria");
        categoriaDao.guardar(cat);
        System.out.println("cat: "+cat);


        cat.setNombre("test nombre categoria editado");
        cat.setDescripcion("test descripcion categoria editado");
        categoriaDao.editar(cat);
        System.out.println("cat editada: " + cat);

        Categoria categoria = categoriaDao.getCategoriaPorId(cat.getId());
        assertEquals("test nombre categoria editado", categoria.getNombre());
        assertEquals("test descripcion categoria editado", categoria.getDescripcion());
    }

    @Test
    public void test_eliminar_categoria(){
        Categoria cat = new Categoria("test nombre categoria", 22, "test descripcion categoria");

        categoriaDao.guardar(cat);
        boolean catEliminada = categoriaDao.eliminar(cat);

        System.out.println("La categoría fue eliminada? " + catEliminada);
        assertTrue(catEliminada);
    }

    @Test
    public void test_get_categoria_por_id(){
        Categoria cat = new Categoria("test nombre categoria", 22, "test descripcion categoria");
        categoriaDao.guardar(cat);

        Categoria categoriaEncontrada = categoriaDao.getCategoriaPorId(cat.getId());

        assertNotNull(categoriaEncontrada);
        assertEquals(cat, categoriaEncontrada);
    }

    @Test
    public void test_get_productos_por_marca(){
        Categoria cat = new Categoria("test nombre categoria", 22, "test descripcion categoria");
        Producto producto = new Producto("test nombre producto 1", 1, 1, "Xbox", "S", 1250);
        Producto producto2 = new Producto("test nombre producto 2", 2, 2,"Playstation", "PS4", 6000);
        Producto producto3 = new Producto("test nombre producto 3", 3, 3,"Motorola", "XT2014", 5500);
        cat.agregarProducto(producto);
        cat.agregarProducto(producto2);
        cat.agregarProducto(producto3);
        categoriaDao.guardar(cat);
        System.out.println("cat: " + cat);

        List<Producto> lista = categoriaDao.getProductosPorMarca(cat, "Playstation");

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.stream().allMatch(p -> p.getMarca().equalsIgnoreCase("Playstation")));

    }

    @Test
    public void test_precios_categoria(){
        Categoria c = new Categoria("Alfa123", 222, "Equipos de audio y television para el hogar");
        Producto producto = new Producto("test nombre producto 1", 1, 1, "test descripcion producto 1", "XT12345", 1250);
        Producto producto2 = new Producto("test nombre producto 2", 2, 2,"test descripcion producto 1", "XT12345", 6000);
        Producto producto3 = new Producto("test nombre producto 3", 3, 3,"test descripcion producto 1", "XT12345", 5500);

        c.agregarProducto(producto);
        c.agregarProducto(producto2);
        c.agregarProducto(producto3);
        categoriaDao.guardar(c);


        List<Producto> lista = categoriaDao.getProductosFiltradosPorPrecios(c, 5000, 7000);
        System.out.println("lista de productos "+lista);

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.size() > 1);

        assertTrue(lista.stream().allMatch(p -> p.getPrecioLista() >= 5000 && p.getPrecioLista() <= 7000));

        System.out.println(lista);
    }
    @Test
    public void test_precios_ascendentes(){
        Categoria c = new Categoria("Alfa123", 222, "Equipos de audio y television para el hogar");
        Producto producto = new Producto("test nombre producto 1", 1, 1, "test descripcion producto 1", "XT12345", 4000);
        Producto producto2 = new Producto("test nombre producto 2", 2, 2,"test descripcion producto 1", "XT12345", 2000);
        Producto producto3 = new Producto("test nombre producto 3", 3, 3,"test descripcion producto 1", "XT12345", 2500);

        c.agregarProducto(producto);
        c.agregarProducto(producto2);
        c.agregarProducto(producto3);
        categoriaDao.guardar(c);

        List<Producto> lista = categoriaDao.getProductosOrdenadosPorPreciosAsc(c);

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.size() > 1);

        Producto previo = null;
        for (Producto actual : lista) {
            if (previo != null) {
                assertTrue(actual.getPrecioLista() >= previo.getPrecioLista());
            }
            previo = actual;
        }
        System.out.println(lista);
    }

    @Test
    public void test_precios_descendentes(){
        Categoria c = new Categoria("Alfa123", 222, "Equipos de audio y television para el hogar");
        Producto producto = new Producto("test nombre producto 1", 1, 1, "test descripcion producto 1", "XT12345", 4000);
        Producto producto2 = new Producto("test nombre producto 2", 2, 2,"test descripcion producto 1", "XT12345", 2000);
        Producto producto3 = new Producto("test nombre producto 3", 3, 3,"test descripcion producto 1", "XT12345", 2500);

        c.agregarProducto(producto);
        c.agregarProducto(producto2);
        c.agregarProducto(producto3);
        categoriaDao.guardar(c);

        List<Producto> lista = categoriaDao.getProductosOrdenadosPorPreciosDesc(c);

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.size() > 1);

        Producto previo = null;
        for (Producto actual : lista) {
            if (previo != null) {
                assertFalse(actual.getPrecioLista() >= previo.getPrecioLista());
            }
            previo = actual;
        }
        System.out.println(lista);

    }

    @Test
    public void test_guardar_categoria_not_valida(){
        // Arrange: Simulamos una categoría existente con un ID determinado
        Categoria c = new Categoria(); // Supongamos que esta es tu implementación de DAO
        Categoria categoriaExistente = new Categoria("Categoria existente", 1, "asdasdasd");
        categoriaDao.guardar(categoriaExistente);

        // Act y Assert: Intentamos guardar otra categoría con el mismo ID
        Categoria categoriaNueva = new Categoria("Nueva categoría", 1,"asda");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> categoriaDao.guardar(categoriaNueva));

        // Verificamos que se lance la excepción esperada
        String expectedMessage = "Ya existe una categoria con ese id, no se puede crear otra.";
        String actualMessage = exception.getMessage();
        assert(actualMessage.contains(expectedMessage));
    }
}
