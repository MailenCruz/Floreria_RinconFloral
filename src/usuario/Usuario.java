package usuario;


import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable, Comparable {
    private String nombreUsuario;
    private String apellido;
    private String email;
    private String contraseña;

    //constructor vacio
    public Usuario (){

    }
    //constructor completo
    public Usuario(String nombreUsuario, String apellido, String email, String contraseña){
        this.nombreUsuario=nombreUsuario;
        this.apellido=apellido;
        this.email=email;
        this.contraseña=contraseña;
    }
    //constructor para inicio de sesion
    public Usuario(String email,String contraseña){
        this.email=email;
        this.contraseña=contraseña;
    }

    //getters and setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }


    //metodos

    @Override
    public String toString() {
        return "-Nombre= " + nombreUsuario + "\n" +
                "-Apellido= " + apellido + "\n" +
                "-Email= " + email + "\n" +
                "-Contraseña= " + contraseña +
                "\n";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return email == usuario.email && Objects.equals(nombreUsuario, usuario.nombreUsuario);
    }

    @Override
    public int hashCode() {
        return 1;
    }


    public int compareTo(Object o)
    {
        int comparacion = 0;
        if (o!=null)
        {
            if (o instanceof Usuario)
            {
                Usuario otroUsuario = (Usuario)o;
                Integer otroEmail = Integer.parseInt(otroUsuario.getEmail());
                Integer miEmail = Integer.parseInt(getEmail());

                comparacion = getNombreUsuario().compareTo(otroUsuario.getNombreUsuario());
            }
        }
        return comparacion;
    }
}
