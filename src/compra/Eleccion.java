package compra;

import catalogo.Flor;

public class Eleccion {
    private Flor florSeleccionada;
    private int cantidad;

    public Eleccion (Flor florSeleccionada, int cantidad){
        this.florSeleccionada = florSeleccionada;
        this.cantidad=cantidad;
    }

    //getters and setters
    public Flor getFlorSeleccionada() {
        return florSeleccionada;
    }

    public void setFlorSeleccionada(Flor florSeleccionada) {
        this.florSeleccionada = florSeleccionada;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //metodos

    @Override
    public String toString() {
        return "\n" + florSeleccionada.muestraEnCarrito()+
                "--→ " + "Cantidad elegida= " +cantidad ;
    }
}
