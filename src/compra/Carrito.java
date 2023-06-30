package compra;

import catalogo.Flor;

import java.util.ArrayList;

public class Carrito {
    private ArrayList<Eleccion> carrito;
    private double total;

    public Carrito (){
        this.carrito=new ArrayList<>();
    }

    //getters and setters
    public ArrayList<Eleccion> getCarrito() {
        return carrito;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    //metodos
    public ArrayList<Eleccion> agregarAlCarrito (Flor flor, int cantidad){

        Eleccion eleccion = new Eleccion(flor,cantidad);
        carrito.add(eleccion);

        double precio = eleccion.getFlorSeleccionada().getPrecioPorUnidad();
        total = calcularTotal(precio, cantidad);

        return carrito;
    }

    public boolean eliminarDelCarrito (String nombre,String color, int cantidad){

        boolean rta=false;

        for (int i = 0; i < carrito.size(); i++) {

            Eleccion eleccion = carrito.get(i);

            if ((eleccion.getFlorSeleccionada().getNombre()).equals(nombre) && eleccion.getFlorSeleccionada().getColor().equals(color)) { //si el nombre esta en el catalogo
                if (eleccion.getCantidad() > cantidad) { //y la cantidad de la misma es mayor a lo que quiere borrar
                    eleccion.setCantidad(eleccion.getCantidad() - cantidad); //se lo restamos
                    rta = true;
                } else if (eleccion.getCantidad() == cantidad) { //en caso de que sea igual la eliminamos por completo
                    carrito.remove(i);
                    rta = true;
                }

                total=total-(eleccion.getFlorSeleccionada().getPrecioPorUnidad()*cantidad);

                break; // Se encontró y eliminó la flor, se puede salir del bucle
            }
        }

        return rta;

    }

    public double calcularTotal (double precio, int cantidad){

        total = total + (precio*cantidad);

        return total;
    }

    public String mostrarCarrito (){
        StringBuilder elecciones = new StringBuilder();

        for(Eleccion eleccion:carrito){

            elecciones.append(eleccion.toString()).append("\n");
        }

        elecciones.append("\n>>>El total de su compra es de: ").append(total);

        return elecciones.toString();
    }
}
