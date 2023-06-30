package entrega;

import javax.swing.plaf.PanelUI;

public class Domicilio extends MetodoDeEntrega{
    private String calle;
    private String altura;
    private String codigoPostal;

    //constructor
    public Domicilio(String calle,String altura,String codigoPostal){
        this.calle=calle;
        this.altura=altura;
        this.codigoPostal=codigoPostal;
    }

    //getters and setters

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    //metodo propio

    @Override
    public String toString() {
        return "-- Domicilio ingresado --" + "\n" +
                "CALLE= " + calle + "\n" +
                "ALTURA= " + altura + "\n" +
                "CODIGO POSTAL= " + codigoPostal + "\n"
                ;
    }

    //implementacion del metodo
    @Override
    public double calcularTotalDelEnvio(double precioDeLaCompra) {
        double totalEnvio = 0.0;

        // Lógica para calcular el total del envío según el código postal
        if (codigoPostal.equals("7600")) {
            totalEnvio = 200.0; //tarifa de envío MarDelPlata

        } else if (codigoPostal.equals("7000")) {
            totalEnvio = 2000.0; //tarifa de envío Tandil

        } else if (codigoPostal.equals("7167")) {
            totalEnvio = 2000.0; //tarifa de envío Pinamar

        } else if (codigoPostal.compareTo("1000") >= 0 && codigoPostal.compareTo("1499") <= 0) {
            totalEnvio = 1600.0; //tarifa de envío Buenos Aires

        } else {
            totalEnvio = 2500.0; //tarifa de envío otros
        }

        return totalEnvio;
    }

}
