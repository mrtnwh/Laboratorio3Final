package ar.edu.utn.frbb.tup.Laboratorio3Final.model;

import java.util.List;
import java.util.Map;

public class Producto {
    private String nombre;
    private String categoria;
    private int categoriaId;
    private int id;
    private String marca;
    private String modelo;
    private String descripcion;
    private Map<String, String> especificaciones;
    private double precioLista;
    private double precioContado;
    private final double descuentoContado = 0.15;
    private double precioOnline;
    private boolean productOnline = false;
    private double descuentoOnline;
    private List<Producto> productosRelacionados;
    private boolean personalizable = false;
    private Map<String, List<String>> opcionesPersonalizacion;

    private String tipo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Map<String, String> getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(Map<String, String> especificaciones) {
        this.especificaciones = especificaciones;
    }

    public double getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(double precioLista) {
        this.precioLista = precioLista;
    }

    public double getPrecioContado() {
        precioContado = this.precioLista - (this.precioLista * this.descuentoContado);
        return precioContado;
    }

    public void setPrecioContado(double precioContado) {
        this.precioContado = precioContado;
    }

    public double getDescuentoContado() {
        return descuentoContado;
    }

    public double getPrecioOnline(double precioOnline) {
        if(this.productOnline){
            precioOnline = this.precioOnline - (this.precioOnline * this.descuentoOnline);
            return precioOnline;
        }else{
            return 0;
        }
    }

    public void setPrecioOnline(double precioOnline) {
        this.precioOnline = precioOnline;
    }

    public boolean isProductOnline() {
        return productOnline;
    }

    public void setProductOnline(boolean productOnline) {
        this.productOnline = productOnline;
    }

    public double getDescuentoOnline() {
        return descuentoOnline;
    }

    public void setDescuentoOnline(double descuentoOnline) {
        this.descuentoOnline = descuentoOnline;
    }

    public List<Producto> getProductosRelacionados() {
        return productosRelacionados;
    }

    public void setProductosRelacionados(List<Producto> productosRelacionados) {
        this.productosRelacionados = productosRelacionados;
    }

    public boolean isPersonalizable() {
        return personalizable;
    }

    public void setPersonalizable(boolean personalizable) {
        this.personalizable = personalizable;
    }

    public Map<String, List<String>> getOpcionesPersonalizacion() {
        if(this.isPersonalizable()){
            return opcionesPersonalizacion;
        }else{
            return null;
        }
    }

    public void setOpcionesPersonalizacion(Map<String, List<String>> opcionesPersonalizacion) {
        this.opcionesPersonalizacion = opcionesPersonalizacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Producto() {}

    public Producto (String nombre, int categoriaId, int id, String marca, String modelo, double precioLista){
        this.nombre = nombre;
        this.categoriaId = categoriaId;
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precioLista = precioLista;
    }

    public Producto (int id, String nombre, String descripcion, int categoriaId, String marca, String modelo, double precioLista, String tipo){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaId = categoriaId;
        this.marca = marca;
        this.modelo = modelo;
        this.precioLista = precioLista;
        this.tipo= tipo;
    }
    public Producto (int id, String categoria, String nombre, String descripcion, int categoriaId, String marca, String modelo, double precioLista, String tipo){
        this.id = id;
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaId = categoriaId;
        this.marca = marca;
        this.modelo = modelo;
        this.precioLista = precioLista;
        this.tipo= tipo;
    }

    public Producto(String nombre, String categoria, int categoriaId, int id, String marca, String modelo, String descripcion, Map<String, String> especificaciones, String tipo, double precioLista, double precioContado, double precioOnline, boolean productOnline, double descuentoOnline, List<Producto> productosRelacionados, boolean personalizable, Map<String, List<String>> opcionesPersonalizacion) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.categoriaId = categoriaId;
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.especificaciones = especificaciones;
        this.tipo = tipo;
        this.precioLista = precioLista;
        this.precioContado = precioContado;
        this.precioOnline = precioOnline;
        this.productOnline = productOnline;
        this.descuentoOnline = descuentoOnline;
        this.productosRelacionados = productosRelacionados;
        this.personalizable = personalizable;
        this.opcionesPersonalizacion = opcionesPersonalizacion;
    }


    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", categoriaId=" + categoriaId +
                ", id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", especificaciones=" + especificaciones +
                ", precioLista=" + precioLista +
                ", precioContado=" + precioContado +
                ", descuentoContado=" + descuentoContado +
                ", precioOnline=" + precioOnline +
                ", productOnline=" + productOnline +
                ", descuentoOnline=" + descuentoOnline +
                ", productosRelacionados=" + productosRelacionados +
                ", personalizable=" + personalizable +
                ", opcionesPersonalizacion=" + opcionesPersonalizacion +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
