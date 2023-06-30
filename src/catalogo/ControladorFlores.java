package catalogo;

import java.util.*;

//Clase que se encarga de realizar las funciones de ABM del catalogo de flores
public class ControladorFlores {

    //muestra el mapa ya cargado con los datos del JSON
    public void muestraDeCatalogo(HashMap<String, Flor> catalogoDeFlores) {

        // muestra de las flores cargadas en el mapa (catalogoDeFlores)
        Iterator<Map.Entry<String, Flor>> iterator = catalogoDeFlores.entrySet().iterator();
        System.out.println("------------------------------ CATÁLOGO DE FLORES ----------------------------------");
        System.out.println("------------------------------------------------------------------------------------");
        while (iterator.hasNext()) {
            Map.Entry<String, Flor> entry = iterator.next();
            String codigo = entry.getKey();
            Flor flor = entry.getValue();
            System.out.println(flor.toString());
        }
    }

    //Agrega una nueva flor al catalogo
    public HashMap<String, Flor> alta(HashMap<String, Flor> catalogoDeFlores, Flor florNueva) {

        catalogoDeFlores.put(florNueva.getCodigo(), florNueva);
        System.out.println("Su flor fue agregada al catalogo");

        return catalogoDeFlores;
    }

    //Elimina una flor buscada por codigo, este se solicita que lo ingrese el usuario
    public boolean baja (HashMap<String, Flor> catalogoDeFlores, String codFlorParaBorrar) {

        boolean rta = catalogoDeFlores.containsKey(codFlorParaBorrar);

        if (rta) {

            System.out.println("--------- FLOR SELECCIONADA --------- ");
            System.out.println(catalogoDeFlores.get(codFlorParaBorrar));

            catalogoDeFlores.remove(codFlorParaBorrar);
            System.out.println("\n> La flor fue eliminada del catalogo\n");
        }

        return rta;
    }

    //Pide al usuario el codigo de la flor que desea modificar, corrobora que la misma sea correcta y da una serie de opciones de caracteristicas a modificar
    //Tiene como retorno un boolean el cual si es falso, termina con la modificacion y lanza un mini menu para salir del programa o ir al menu.
    public boolean modificacion (HashMap<String, Flor> catalogoDeFlores, String codigoDeFlor) {

        Scanner teclado = new Scanner(System.in);
        String seguir = "s";
        boolean lectura;
        boolean rta = catalogoDeFlores.containsKey(codigoDeFlor);

        if (rta) {

            System.out.println("La flor que va a modificar es la siguiente");
            System.out.println(catalogoDeFlores.get(codigoDeFlor));
            System.out.println("Es correcto? s/n");
            String seguir2 = teclado.next();

            if (seguir2.equalsIgnoreCase("s")) {

                do {
                    do {
                        lectura = true;
                        try {

                            System.out.println("Ingrese la opcion que corresponda al dato que quiere modificar");
                            System.out.println("1- Nombre");
                            System.out.println("2- Codigo");
                            System.out.println("3- Color");
                            System.out.println("4- Origen");
                            System.out.println("5- Precio");
                            int op = teclado.nextInt();

                            switch (op) {

                                case 1:
                                    System.out.println("Ingrese el nuevo nombre: ");
                                    String nombreNuevo = teclado.next();
                                    catalogoDeFlores.get(codigoDeFlor).setNombre(nombreNuevo);

                                    System.out.println("FLOR MODIFICADA");
                                    System.out.println(catalogoDeFlores.get(codigoDeFlor));
                                    break;

                                case 2:
                                    System.out.println("Ingrese el nuevo codigo: ");
                                    String codigoNuevo = teclado.next();
                                    catalogoDeFlores.get(codigoDeFlor).setCodigo(codigoNuevo);

                                    System.out.println("FLOR MODIFICADA");
                                    System.out.println(catalogoDeFlores.get(codigoDeFlor));
                                    break;

                                case 3:
                                    System.out.println("Ingrese el nuevo color: ");
                                    String colorNuevo = teclado.next();
                                    catalogoDeFlores.get(codigoDeFlor).setColor(colorNuevo);

                                    System.out.println("FLOR MODIFICADA");
                                    System.out.println(catalogoDeFlores.get(codigoDeFlor));
                                    break;

                                case 4:
                                    System.out.println("Ingrese el nuevo origen: ");
                                    String origenNuevo = teclado.next();
                                    catalogoDeFlores.get(codigoDeFlor).setOrigen(origenNuevo);

                                    System.out.println("FLOR MODIFICADA");
                                    System.out.println(catalogoDeFlores.get(codigoDeFlor));
                                    break;

                                case 5:
                                    System.out.println("Ingrese el nuevo precio: ");
                                    double precioNuevo = teclado.nextDouble();
                                    catalogoDeFlores.get(codigoDeFlor).setPrecioPorUnidad(precioNuevo);

                                    System.out.println("--------- FLOR MODIFICADA --------- ");
                                    System.out.println(catalogoDeFlores.get(codigoDeFlor));
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
            }
        }

        return rta;
     }

}

