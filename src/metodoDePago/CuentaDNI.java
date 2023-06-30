package metodoDePago;

import interfaces.I_ProcesarPago;

public class CuentaDNI implements I_ProcesarPago {

    private String dni;
    private double monto;
    private String claveApp;

    //constructor
    public CuentaDNI(String dni, double monto, String claveApp){
        this.dni=dni;
        this.monto=monto;
        this.claveApp=claveApp;
    }
    public CuentaDNI(){

    }

    //getters
    public String getDni() {
        return dni;
    }

    public double getMonto() {
        return monto;
    }

    public String getClaveApp() {
        return claveApp;
    }

    //metodo
    @Override
    public String procesarPago(double precioFinal) {
        return "\n"+ "Se acredito el pago de $" + monto + " a la cuenta asociada al DNI: " + dni;
    }

    @Override
    public String cancelarPago() {
        return "Su pago ha sido cancelado";
    }

    @Override
    public String toString() {
        return "-- Cuenta DNI --" + "\n" + "Dni → " + dni + "\n";
    }
}
