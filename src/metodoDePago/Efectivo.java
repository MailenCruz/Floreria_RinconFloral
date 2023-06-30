package metodoDePago;

import interfaces.I_ProcesarPago;

public class Efectivo implements I_ProcesarPago {
    @Override
    public String procesarPago(double precioFinal) {
        return "Una vez entregado el producto debera abonar "+ precioFinal+" pesos, correspondiente al total de la compra, de lo contrario el mismo NO se entregara";
    }
    @Override
    public String cancelarPago() {
        return "El pago ha sido cancelado";
    }

    @Override
    public String toString() {
        return "-- Efectivo --  ";
    }
}
