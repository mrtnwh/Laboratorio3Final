package ar.edu.utn.frbb.tup.Laboratorio3Final.singletons;

import ar.edu.utn.frbb.tup.Laboratorio3Final.business.CategoriaBusiness;
import ar.edu.utn.frbb.tup.Laboratorio3Final.business.Implementation.CategoriaBusinessImplementation;

public class CategoriaBusinessImplementationSingleton {
    public static CategoriaBusiness instance;

    private CategoriaBusinessImplementationSingleton() {
        // Constructor privado para evitar la creación de múltiples instancias
    }

    public static CategoriaBusiness getInstance() {
        if (instance == null) {
            instance = new CategoriaBusinessImplementation();
        }
        return instance;
    }
}
