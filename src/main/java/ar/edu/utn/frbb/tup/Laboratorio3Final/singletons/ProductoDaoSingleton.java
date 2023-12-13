package ar.edu.utn.frbb.tup.Laboratorio3Final.singletons;

import ar.edu.utn.frbb.tup.Laboratorio3Final.dao.ProductoDao;
import ar.edu.utn.frbb.tup.Laboratorio3Final.dao.Implementation.ProductoDaoImplementation;

public class ProductoDaoSingleton {
    public static ProductoDao instance;

    private ProductoDaoSingleton() {
        // Constructor privado para evitar la creación de múltiples instancias
    }

    public static ProductoDao getInstance() {
        if (instance == null) {
            instance = new ProductoDaoImplementation();
        }
        return instance;
    }
}
