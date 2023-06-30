package excepciones;

public class CodigoDeSeguridadException extends RuntimeException{
    public CodigoDeSeguridadException(String mensaje){
        super(mensaje);
    }
}
