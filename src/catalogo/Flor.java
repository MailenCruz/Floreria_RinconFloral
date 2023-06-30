package catalogo;

import jdk.jfr.FlightRecorderListener;

import java.util.Scanner;

public class Flor {

    //atributos
    private String nombre;
    private String codigo;
    private String color;
    private String origen;
    private double precioPorUnidad;

    //para evitar errores al momento de pasar los datos de JSON a Java, hago uso de variables fijas
    public static final String NOMBRE_FLOR = "nombre";
    public static final String CODIGO_FLOR = "código";
    public static final String COLOR_FLOR = "color";
    public static final String ORIGEN_FLOR = "origen";
    public static final String PRECIO_FLOR = "precioPorUnidad";


    //constructor
    public Flor(String nombre, String codigo, String color, String origen, double precioPorUnidad){
        this.nombre=nombre;
        this.codigo=codigo;
        this.color=color;
        this.origen=origen;
        this.precioPorUnidad=precioPorUnidad;
    }

    public Flor() {

    }

    //getters and setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public double getPrecioPorUnidad() {
        return precioPorUnidad;
    }

    public void setPrecioPorUnidad(double precioPorUnidad) {
        this.precioPorUnidad = precioPorUnidad;
    }

    //metodos

    @Override
    public String toString() {
        return  "Nombre= " + nombre + '\n' +
                "Codigo= " + codigo + '\n' +
                "Color= " + color + '\n' +
                "Origen= " + origen + '\n' +
                "Precio por unidad=" + precioPorUnidad + '\n' +
                "-----------------------------------------------";
    }

    public String muestraEnCarrito() {
        return  "--------------------------------------------"+"\n"+
                "Nombre= " + nombre + '\n' +
                "Codigo= " + codigo + '\n' +
                "Color= " + color + '\n' +
                "Origen= " + origen + '\n' +
                "Precio por unidad= " + precioPorUnidad +"\t";
    }

}
