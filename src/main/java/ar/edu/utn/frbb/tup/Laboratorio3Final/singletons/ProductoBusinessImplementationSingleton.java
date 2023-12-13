package ar.edu.utn.frbb.tup.Laboratorio3Final.singletons;


import ar.edu.utn.frbb.tup.Laboratorio3Final.business.ProductoBusiness;
import ar.edu.utn.frbb.tup.Laboratorio3Final.business.Implementation.ProductoBusinessImplementation;

public class ProductoBusinessImplementationSingleton {
    public static ProductoBusiness instance;

    private ProductoBusinessImplementationSingleton() {
        // Constructor privado para evitar la creación de múltiples instancias
    }

    public static ProductoBusiness getInstance() {
        if (instance == null) {
            instance = new ProductoBusinessImplementation();
        }
        return instance;
    }
}
