package ar.edu.utn.frbb.tup.Laboratorio3Final.singletons;

import ar.edu.utn.frbb.tup.Laboratorio3Final.dao.CategoriaDao;

import ar.edu.utn.frbb.tup.Laboratorio3Final.dao.Implementation.CategoriaDaoImplementation;

public class CategoriaDaoSingleton {
    public static CategoriaDao instance;

    private CategoriaDaoSingleton() {
        // Constructor privado para evitar la creación de múltiples instancias
    }

    public static CategoriaDao getInstance() {
        if (instance == null) {
            instance = new CategoriaDaoImplementation();
        }
        return instance;
    }
}
