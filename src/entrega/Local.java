package entrega;

public class Local extends MetodoDeEntrega{
    private String calle;
    private String altura;
    private String diasAbierto;
    private String horario;

    //constructor
    public Local (String calle,String altura,String diasAbierto,String horario){
        this.calle=calle;
        this.altura=altura;
        this.diasAbierto=diasAbierto;
        this.horario=horario;
    }

    //getters and setters (no)

    //metodo propio

    @Override
    public String toString() {
        return  "\n" + "-- Informacion del local --" +"\n"+
                "CALLE= " + calle + "\n" +
                "ALTURA= " + altura + "\n" +
                "DIAS ABIERTO= " + diasAbierto + "\n" +
                "HORARIO= " + horario + "\n"
                ;
    }

    //implementacion del metodo
    @Override
    public double calcularTotalDelEnvio(double precioDeLaCompra) {
        double totalEnvio = 0.0;
        double descuento = (precioDeLaCompra*0.10);

        totalEnvio = precioDeLaCompra - descuento;

        return totalEnvio;
    }

}
