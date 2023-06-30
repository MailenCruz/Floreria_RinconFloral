package metodoDePago;

import interfaces.I_ProcesarPago;

public class Tarjeta implements I_ProcesarPago {
    private String nombreDelTitular;
    private String apellidoDelTitular;
    private String numero;
    private String cvv;
    private String tipo; //credito o debito

    //constructor
    public Tarjeta (String nombreDelTitular,String apellidoDelTitular,String numero, String cvv, String tipo){
        this.nombreDelTitular=nombreDelTitular;
        this.apellidoDelTitular=apellidoDelTitular;
        this.numero=numero;
        this.cvv=cvv;
        this.tipo=tipo;
    }

    public Tarjeta(){

    }

    //getters and setters
    public String getNombreDelTitular() {
        return nombreDelTitular;
    }

    public void setNombreDelTitular(String nombreDelTitular) {
        this.nombreDelTitular = nombreDelTitular;
    }

    public String getApellidoDelTitular() {
        return apellidoDelTitular;
    }

    public void setApellidoDelTitular(String apellidoDelTitular) {
        this.apellidoDelTitular = apellidoDelTitular;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //metodo
    @Override
    public String toString() {
        return "-- TARJETA --" + "\n" +
                "Titular → " + nombreDelTitular +" "+ apellidoDelTitular + "\n" +
                "Numero → " + numero + "\n" +
                "Codigo de seguridad → " + cvv + "\n" +
                "Tipo → " + tipo + "\n"
                ;
    }

    //implementacion de metodos
    @Override
    public String procesarPago(double precioFinal) {

        return "Se acreditó el pago de $" + precioFinal + " pesos en la cuenta asociada a la tarjeta " + numero + "\n" + "-> email de confirmacion de pago ENVIADO"+"\n";
    }

    @Override
    public String cancelarPago() {
        return "Su pago ha sido cancelado";
    }
}
