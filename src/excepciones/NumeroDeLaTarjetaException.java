package excepciones;

public class NumeroDeLaTarjetaException extends RuntimeException{
    public NumeroDeLaTarjetaException (String mensaje){
        super(mensaje);
    }
}
