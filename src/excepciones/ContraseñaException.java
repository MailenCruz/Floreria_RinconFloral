package excepciones;
public class ContraseñaException extends RuntimeException{
    public ContraseñaException (String mensaje){
        super(mensaje);
    }
}
