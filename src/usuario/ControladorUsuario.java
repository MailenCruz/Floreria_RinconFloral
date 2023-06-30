package usuario;

import java.util.*;

public class ControladorUsuario {

    //muestra el set ya cargado con los usuarios
    public void muestraDeUsuarios(HashSet<Usuario> usuarios) {

        Iterator<Usuario> it = usuarios.iterator();
        System.out.println("\nUSUARIOS ACTIVOS:");

        while (it.hasNext()) {
            Usuario usuario = it.next();
            System.out.println(usuario);
        }

        System.out.println("Cantidad total de usuarios activos: " + usuarios.size());
    }

    //Agrega un nuevo usuario al sistem
    public HashSet<Usuario> alta(HashSet<Usuario> usuarios, Usuario usuarioNuevo) {

        usuarios.add(usuarioNuevo);
        System.out.println("El usuario fue agregado al sistema");

        return usuarios;
    }

    //Elimina un usuario buscado por nombre, este se solicita que lo ingrese el usuario
    public boolean baja(HashSet<Usuario> usuarios, String nombreDelUsuario) {

        boolean rta = true;
        Usuario usuarioAEliminar = null;

        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreDelUsuario)) {
                usuarioAEliminar = usuario;
            }
        }

        if (usuarioAEliminar != null) {
            rta = usuarios.remove(usuarioAEliminar);
            System.out.println("Usuario ELIMINADO: " + nombreDelUsuario);
        } else {
            rta = false;
        }

        return rta;
    }

    //Pide al usuario el nombre del usuario que desea modificar, corrobora que el mismo sea correcto y da una serie de opciones de caracteristicas a modificar
    //Tiene como retorno un boolean el cual si es true, termina con la modificacion y lanza un mini menu para salir del programa o ir al menu.
    public boolean modificacion(HashSet<Usuario> usuarios, String nombreDelUsuario) {

        Scanner teclado = new Scanner(System.in);
        String seguir = "s";
        Boolean lectura;

        boolean encontrado = false;
        Usuario usuarioAModificar = null;

        for (Usuario usuario : usuarios) {

            if (usuario.getNombreUsuario().equals(nombreDelUsuario)) {
                usuarioAModificar = usuario;
                encontrado = true;
            }
        }

        if (encontrado) {

            System.out.println("El usuario que va a modificar es el siguiente: ");
            MostrarUsuarioEnModificacion(usuarioAModificar);

            System.out.println("Es correcto? s/n");
            String seguir2 = teclado.next();

            if (seguir2.equalsIgnoreCase("s")) {

                do {
                    do {
                        lectura = true;

                        try {
                            System.out.println("Ingrese la opcion que corresponda al dato que quiere modificar");
                            System.out.println("1- Nombre");
                            System.out.println("2- Apellido");
                            System.out.println("3- Email");
                            System.out.println("4- Contraseña");

                            int opUS = teclado.nextInt();

                            switch (opUS) {

                                case 1:
                                    System.out.println("Ingrese el nuevo nombre: ");
                                    String nombreNuevo = teclado.next();
                                    usuarioAModificar.setNombreUsuario(nombreNuevo);

                                    System.out.println("\nUSUARIO MODIFICADO");
                                    MostrarUsuarioEnModificacion(usuarioAModificar);
                                    break;

                                case 2:
                                    System.out.println("Ingrese el nuevo apellido: ");
                                    String apellidoNuevo = teclado.next();
                                    usuarioAModificar.setApellido(apellidoNuevo);

                                    System.out.println("\nUSUARIO MODIFICADO");
                                    MostrarUsuarioEnModificacion(usuarioAModificar);

                                    break;

                                case 3:
                                    System.out.println("Ingrese el nuevo Email: ");
                                    String EmailNuevo = teclado.next();
                                    usuarioAModificar.setEmail(EmailNuevo);

                                    System.out.println("\nUSUARIO MODIFICADO");
                                    MostrarUsuarioEnModificacion(usuarioAModificar);

                                    break;

                                case 4:
                                    System.out.println("Ingrese la nueva contraseña: ");
                                    String contraseñaNueva = teclado.next();
                                    usuarioAModificar.setContraseña(contraseñaNueva);

                                    System.out.println("\nUSUARIO MODIFICADO");
                                    MostrarUsuarioEnModificacion(usuarioAModificar);
                                    break;

                                default:
                                    teclado.nextLine();
                                    System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                                    teclado.nextLine();
                                    break;
                            }

                        } catch (InputMismatchException ex) {
                            lectura = false;
                            teclado.nextLine();
                            System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                            teclado.nextLine();
                        }

                    } while (!lectura);

                    System.out.println("Desea modificar otro dato? s/n");
                    seguir = teclado.next();

                } while (seguir.equalsIgnoreCase("s"));

            } else {
                encontrado = false;
            }
        }
        return encontrado;
    }

    //Implementacion del mismo ya que el codigo de muestra se repetia en varias ocaciones.
    public void MostrarUsuarioEnModificacion (Usuario usuarioAModificar){

        System.out.println("Nombre:" + usuarioAModificar.getNombreUsuario());
        System.out.println("Apellido:" + usuarioAModificar.getApellido());
        System.out.println("Email:" + usuarioAModificar.getEmail());
        System.out.println("Contraseña:" + usuarioAModificar.getContraseña());
        System.out.println("\n");
    }
}
