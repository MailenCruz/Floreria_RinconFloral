package ticket;

public class Ticket <C,P,D> {
    private C compra;
    private P pago;
    private D totalAPagar;

    //constructor
    public Ticket(C compra, P pago, D totalAPagar){
        this.compra=compra;
        this.pago=pago;
        this.totalAPagar=totalAPagar;
    }

    public Ticket(){

    }

    //getters
    public C getCompra() {
        return compra;
    }

    public P getPago() {
        return pago;
    }

    public D getTotalAPagar() {
        return totalAPagar;
    }

    //metodo


    @Override
    public String toString() {
        return "\n" +
                "------------------ TICKET ------------------" +
                "\n" + compra.toString() +"\n"+
                "--------------------------------------------" + "\n"+
                "METODO DE PAGO= " + pago + "\n"+
                "--------------------------------------------" + "\n"+
                "TOTAL PAGADO= $" + totalAPagar + "\n"+
                "--------------------------------------------";
    }
}
