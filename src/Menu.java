import catalogo.ControladorFlores;
import catalogo.Flor;
import compra.Carrito;
import compra.Eleccion;
import entrega.Domicilio;
import entrega.Local;
import entrega.MetodoDeEntrega;
import excepciones.ClaveCuentaDNIException;
import excepciones.CodigoDeSeguridadException;
import excepciones.ContraseñaException;
import excepciones.NumeroDeLaTarjetaException;
import metodoDePago.CuentaDNI;
import metodoDePago.Efectivo;
import metodoDePago.Tarjeta;
import ticket.Ticket;
import usuario.ControladorUsuario;
import usuario.Usuario;

import java.text.DecimalFormat;
import java.util.*;

public class Menu {

    private HashMap<String,Flor> catalogo;
    private HashSet<Usuario> usuarios;
    private Scanner teclado;

    public Menu (HashMap<String ,Flor> catalogo, HashSet<Usuario> usuarios){
        this.catalogo=catalogo;
        this.usuarios=usuarios;
    }

    //----------------------------------------------------
    public void MenuPrincipal (){
        boolean lectura;
        do{
            lectura=true;
            try {
                teclado=new Scanner(System.in);
                System.out.println("\nMENU PRINCIPAL");
                System.out.println("1- Ingresar a la app");
                System.out.println("2- Configuracion del sistema");
                System.out.println("3- Salir");

                int op;
                op= teclado.nextInt(); //lanza una exepcion si ingresa otro tipo de dato

                switch (op){
                    case 1: //Menu de ingreso a la app
                        MenuIngresoALaApp();
                        break;

                    case 2: //Menu configuracion del sistema

                        System.out.println("\nIngrese la contraseña de acceso:");
                        String contraseñaDeAcceso = teclado.next();

                        if (contraseñaDeAcceso.equalsIgnoreCase("123")) {
                            MenuConfiguracionDelSistema();
                        } else {
                            System.out.println("\nLa contraseña es INCORRECTA");
                            MenuPrincipal();
                        }

                        break;

                    case 3: //Salida del programa
                        System.out.println("\nSaliendo...");
                        break;

                    default:
                        teclado.nextLine();
                        System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                        teclado.nextLine();
                        MenuPrincipal();
                        break;
                }
            }
            catch(InputMismatchException e){
                lectura = false;
                teclado.nextLine();
                System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                teclado.nextLine();
            }
        } while (!lectura);
    }

    //----------------------------------------------------
    public void MenuConfiguracionDelSistema (){
        boolean lectura;

        do {
            lectura=true;
            try {

                System.out.println("\nCONFIGURACION DEL SISTEMA");
                System.out.println("1- Configurar catalogo");
                System.out.println("2- Configurar usuario");
                System.out.println("3- Volver al menu principal");
                int op2 = teclado.nextInt();

                switch (op2) {
                    case 1:
                        MenuCatalogo();
                        break;
                    case 2:
                        MenuUsuario();
                        break;
                    case 3:
                        MenuPrincipal();
                        break;

                    default:
                        teclado.nextLine();
                        System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                        teclado.nextLine();
                        MenuConfiguracionDelSistema();
                        break;
                }
            }
            catch (InputMismatchException e) {
                lectura = false;
                teclado.nextLine();
                System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                teclado.nextLine();
            }
        }while (!lectura);

    }

    public void MenuUsuario (){
        boolean lectura;
        ControladorUsuario controladorUsuario = new ControladorUsuario();

        do{
            lectura=true;
            try {
                teclado = new Scanner(System.in);
                System.out.println("\nMENU DE USUARIO");
                System.out.println("1- Mostrar usuarios");
                //ABM
                System.out.println("2- Crear usuario"); //dar de alta un nuevo usuario
                System.out.println("3- Eliminar usuario"); //dar de baja un usuario existente
                System.out.println("4- Modificar usuario"); //modificacion de un usuario
                System.out.println("5- Volver al menu principal");

                int op = teclado.nextInt();

                switch (op){
                    case 1:
                        controladorUsuario.muestraDeUsuarios(usuarios);
                        MenuUsuario();

                        break;
                    case 2:
                        Usuario usuarioNuevo = CrearUsuario_EnMenuUsuario();
                        usuarios = controladorUsuario.alta(usuarios,usuarioNuevo);
                        MenuUsuario();
                        break;

                    case 3:
                        int flag=1;
                        controladorUsuario.muestraDeUsuarios(usuarios);

                        do {

                            System.out.println("Ingrese el nombre del usuario que desea dar de baja: ");
                            String nombreUsuarioParaBorrar = teclado.next();

                            boolean rtaBajaUsusario = controladorUsuario.baja(usuarios, nombreUsuarioParaBorrar);

                            if (rtaBajaUsusario) {
                                flag=0;

                                System.out.println("Desea ver el listado de usuarios actual? s/n");
                                String seguir = teclado.next();

                                if (seguir.equalsIgnoreCase("s")) {
                                    controladorUsuario.muestraDeUsuarios(usuarios);
                                } else {
                                    MenuUsuario();
                                }

                            } else {
                                System.out.println("--> El nombre ingresado no pertenece a una usuario");
                            }

                        }while (flag == 1);

                        MenuUsuario();
                        break;

                    case 4:

                        int flag4 = 1;
                        controladorUsuario.muestraDeUsuarios(usuarios);

                        do {

                            System.out.println("Ingrese el nombre del usuario que desea modificar:");
                            String nombreUsuarioParaModificar = teclado.next();

                            boolean rta = controladorUsuario.modificacion(usuarios, nombreUsuarioParaModificar);

                            try{

                                if (rta) {
                                    flag4 = 0;

                                    System.out.println("1- Cerrar programa");
                                    System.out.println("2- Volver al menu de usuario");
                                    int opMod = teclado.nextInt();

                                    switch (opMod) {
                                        case 1:
                                            System.out.println("Saliendo...");
                                            break;

                                        case 2:
                                            MenuUsuario();
                                            break;

                                        default:
                                            teclado.nextLine();
                                            System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                                            teclado.nextLine();
                                            MenuUsuario();
                                            break;
                                    }
                                }
                                else {
                                    System.out.println("--> El codigo ingresado no pertenece a un usuario");
                                }

                            } catch(InputMismatchException e){
                                    lectura = false;
                                    teclado.nextLine();
                                    System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                                    teclado.nextLine();
                            }

                        }while (flag4==1);

                        break;

                    case 5:
                        MenuPrincipal();
                        break;

                    default:
                        teclado.nextLine();
                        System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                        teclado.nextLine();
                        MenuUsuario();
                        break;
                }
            }
            catch (InputMismatchException ex){
                lectura = false;
                teclado.nextLine();
                System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                teclado.nextLine();
            }
        }while (!lectura);

    }

    public Usuario CrearUsuario_EnMenuUsuario (){

        Usuario usuarioNuevo=null;

        System.out.println("\nIngrese los datos del nuevo usuario");

        System.out.println("Nombre del usuario:");
        String nombreUsuario = teclado.next();
        System.out.println("Apellido del usuario:");
        String apellidoUsuario = teclado.next();
        System.out.println("Email del usuario: ");
        String email = teclado.next();

        //El metodo ingreso de contraseña lanza una excepcion si la misma es menor a 8 digitos
        String contraseña="";
        try {
            contraseña = IngresoDeContraseña();
        }catch (ContraseñaException e){
            System.out.println("\n"+e.getMessage() + "- Crea el usuario nuevamente");
            MenuUsuario();
        }

        usuarioNuevo = new Usuario(nombreUsuario,apellidoUsuario,email,contraseña);

        for (Usuario usuario:usuarios) {

            if(usuario.getEmail().equals(usuarioNuevo.getEmail()))
            {
                System.out.println("\n- El mail ingresado ya posee registro de un usuario - ");
                MenuUsuario();
            }
        }

        return usuarioNuevo;
    }

    public String IngresoDeContraseña () {

        while (true) {
            System.out.println("Contraseña:");
            String contraseña = teclado.next();

            if (contraseña.length() <= 8) {
                throw new ContraseñaException("\nERROR: La contraseña debe tener 8 digitos");
            } else {

                return contraseña;
            }
        }
    }

    public void MenuCatalogo (){
        boolean lectura;

        //clase que se encarga de realizar todas las funciones posibles para hacer con las flores.
        ControladorFlores controladorFlores = new ControladorFlores();

        do{
            lectura=true;
            try {
                teclado = new Scanner(System.in);

                System.out.println("\nCONFIGURACION DEL CATALOGO");
                System.out.println("1- Ver catalogo");
                //ABM
                System.out.println("2- Ingresar una flor nueva al catalogo"); //dar de alta
                System.out.println("3- Eliminar una flor del catalogo"); //dar de baja
                System.out.println("4- Modificar una flor del catalogo"); //modificacion

                System.out.println("5- Volver al menu principal");

                int op = teclado.nextInt();

                switch (op){
                    case 1:
                        controladorFlores.muestraDeCatalogo(catalogo);
                        MenuCatalogo();
                        break;

                    case 2:
                        System.out.println("\nIngrese los datos de la flor");

                        System.out.println("Nombre de la flor: ");
                        String nombre = teclado.next();
                        System.out.println("Codigo de la flor: ");
                        String codigo = teclado.next();
                        System.out.println("Color de la flor: ");
                        String color = teclado.next();
                        System.out.println("Origen de la flor: ");
                        String origen = teclado.next();
                        System.out.println("Precio de la flor: ");
                        double precio = teclado.nextDouble();

                        Flor florNueva = new Flor(nombre,codigo,color,origen,precio);

                        catalogo = controladorFlores.alta(catalogo,florNueva);

                        System.out.println();
                        MenuCatalogo();

                        break;

                    case 3:

                        int flag=1;
                        controladorFlores.muestraDeCatalogo(catalogo);

                        do {
                            System.out.println("Ingrese el codigo de la flor que desea dar de baja: ");
                            String codigoParaBorrar = teclado.next();

                            boolean rtaBaja = controladorFlores.baja(catalogo, codigoParaBorrar);

                            if (rtaBaja) {
                                flag=0;
                                System.out.println("Desea ver el catalogo actual? s/n");
                                String seguir = teclado.next();

                                if (seguir.equalsIgnoreCase("s")) {
                                    controladorFlores.muestraDeCatalogo(catalogo);
                                } else {
                                    MenuCatalogo();
                                }

                            } else {
                                System.out.println("\n--> El codigo ingresado no pertenece a una flor");
                            }

                        }while (flag == 1);

                        MenuCatalogo();

                        break;

                    case 4:

                        int flag4 = 1;
                        controladorFlores.muestraDeCatalogo(catalogo);

                        do {
                            System.out.println("Ingrese el codigo de la flor que desea modificar:");
                            String codigoParaModificar = teclado.next();

                            boolean rta = controladorFlores.modificacion(catalogo, codigoParaModificar);

                            if (rta) {
                                flag4=0;
                                System.out.println("1- Cerrar programa");
                                System.out.println("2- Volver al menu de flores");
                                int opMod = teclado.nextInt();

                                switch (opMod) {
                                    case 1:
                                        System.out.println("Saliendo...");
                                        break;
                                    case 2:
                                        MenuCatalogo();
                                        break;
                                }
                            }
                            else
                            {
                                System.out.println("\n--> El codigo ingresado no pertenece a una flor");
                            }
                        }while (flag4==1);

                        break;

                    case 5:
                        MenuConfiguracionDelSistema();
                        break;

                    default:
                        teclado.nextLine();
                        System.out.println("\nIngrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                        teclado.nextLine();
                        MenuCatalogo();
                        break;
                }
            }
            catch (InputMismatchException ex){
                lectura = false;
                teclado.nextLine();
                System.out.println("\nIngrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                teclado.nextLine();
            }
        }while (!lectura);

    }

    //------------------------------------------------------

    public void MenuIngresoALaApp (){
        boolean lectura;
        ControladorUsuario controladorUsuario = new ControladorUsuario();

        do{
            lectura=true;
            try{
                System.out.println("\nINGRESO A LA APP");
                System.out.println("1- Iniciar Sesion");
                System.out.println("2- Registrarte");
                System.out.println("3- Volver al menu principal");
                int op = teclado.nextInt();

                switch (op){
                    case 1:
                        boolean rta = InciarSesion();

                        if(rta){
                            //entra al menu de compra
                            MenuCompra();
                        }
                        else {
                            System.out.println("El usuario ingresado no existe en el sistema - ingrese la opcion 2 para registrarse");
                            MenuIngresoALaApp();
                        }
                        break;

                    case 2:
                        Usuario nuevoUsuario = CrearUsuario_EnMenuIngresarALaApp();
                        controladorUsuario.alta(usuarios,nuevoUsuario);

                        //entra el menu de compra
                        MenuCompra();

                        break;

                    case 3:
                        MenuPrincipal();
                        break;

                    default:
                        teclado.nextLine();
                        System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                        teclado.nextLine();
                        MenuIngresoALaApp();
                        break;
                }
            }
            catch (InputMismatchException e) {
                lectura = false;
                teclado.nextLine();
                System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                teclado.nextLine();
            }
        }while (!lectura);


    }

    //Devuelve true si el usuario esta en el sistema, entonces entra al sistema de compra
    public boolean InciarSesion (){

        boolean rta=false;

        System.out.println("Email del usuario: ");
        String email = teclado.next();

        //El metodo ingreso de contraseña lanza una excepcion si la misma es menor a 8 digitos
        String contraseña="";
        try {
            contraseña = IngresoDeContraseña();

        }catch (ContraseñaException e){
            System.out.println(e.getMessage() + "- Crea el usuario nuevamente");
            MenuIngresoALaApp();
        }

        Usuario usuarioIngresado = new Usuario(email, contraseña);

        for(Usuario usuario:usuarios){

            if(usuario.getEmail().equals(usuarioIngresado.getEmail()) && usuario.getContraseña().equals(usuarioIngresado.getContraseña())){
                rta = true;
            }
        }

        return rta;
    }

    public Usuario CrearUsuario_EnMenuIngresarALaApp (){

        Usuario usuarioNuevo=null;

        System.out.println("\nIngrese los datos del nuevo usuario");

        System.out.println("Nombre del usuario:");
        String nombreUsuario = teclado.next();
        System.out.println("Apellido del usuario:");
        String apellidoUsuario = teclado.next();
        System.out.println("Email del usuario: ");
        String email = teclado.next();

        //El metodo ingreso de contraseña lanza una excepcion si la misma es menor a 8 digitos
        String contraseña="";
        try {
            contraseña = IngresoDeContraseña();
        }catch (ContraseñaException e){
            System.out.println(e.getMessage() + "- Crea el usuario nuevamente");
            MenuIngresoALaApp();
        }

        usuarioNuevo = new Usuario(nombreUsuario,apellidoUsuario,email,contraseña);

        for (Usuario usuario:usuarios) {

            if(usuario.getEmail().equals(usuarioNuevo.getEmail()))
            {
                System.out.println("- El mail ingresado ya posee registro de un usuario - inicie session con el mismo");
                MenuIngresoALaApp();
            }
        }

        return usuarioNuevo;
    }

    //----------------------------------------------------------

    public void MenuCompra (){
        boolean lectura;
        ControladorFlores controladorFlores = new ControladorFlores();
        do{
            lectura=true;
            try {
                System.out.println("\nINICIO DE LA COMPRA:");
                System.out.println("1- Ver catalogo");
                System.out.println("2- Iniciar compra");
                int op = teclado.nextInt();

                switch (op){
                    case 1:
                        controladorFlores.muestraDeCatalogo(catalogo);
                        MenuCompra();
                        break;

                    case 2:
                        controladorFlores.muestraDeCatalogo(catalogo);

                        //inicio de la compra
                        Carrito nuevaCompra = new Carrito();
                        cargaCarrito(nuevaCompra);

                        //muestra el carro
                        System.out.println("\nFLORES SELECCIONADAS:");
                        System.out.println(nuevaCompra.mostrarCarrito());

                        //modificacion del carro

                        System.out.println("\nQuiere modificar algo del carrito? s/n");
                        String cambiarCarro = teclado.next();

                        if(cambiarCarro.equalsIgnoreCase("s")){
                            modificarCarro(nuevaCompra);
                        }

                        //ENTREGA
                        MenuEntrega(nuevaCompra);

                        //--
                        MenuCompra();

                        break;

                    default:
                        teclado.nextLine();
                        System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                        teclado.nextLine();
                        MenuCompra();
                        break;
                }
            }catch (InputMismatchException e) {
                lectura = false;
                teclado.nextLine();
                System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                teclado.nextLine();
            }

        }while (!lectura);
        teclado.close();
    }

    public void cargaCarrito(Carrito nuevaCompra){

        //inicio de la compra
        String continuarCarga="s";

        do {
            System.out.println("Ingrese el codigo de la flor que quiere agregar al carrito:");
            String codigo = teclado.next();

            if(catalogo.containsKey(codigo)){
                System.out.println("Ingrese la cantidad de la misma que quiere comprar:");
                int cantidad = teclado.nextInt();

                // Verificar si la flor ya está en el carrito
                Flor flor = catalogo.get(codigo);

                boolean encontrada = false;

                // Agregar la flor al carrito si ya hay de la elegida
                for (Eleccion eleccion : nuevaCompra.getCarrito()) {
                    if (eleccion.getFlorSeleccionada().equals(flor)) {
                        eleccion.setCantidad(eleccion.getCantidad() + cantidad);
                        double totalPrevio = nuevaCompra.getTotal();
                        totalPrevio += nuevaCompra.calcularTotal(flor.getPrecioPorUnidad(),cantidad);
                        encontrada = true;
                        break;
                    }
                }

                // Si la flor no está en el carrito, se agrega como nueva elección
                if (!encontrada) {
                    nuevaCompra.agregarAlCarrito(flor,cantidad);
                }

                System.out.println("\nQuiere agregar otra flor al carrito? s/n ");
                continuarCarga = teclado.next();
            }
            else {
                System.out.println("\n> El codigo ingresado no pertenece a ninguna flor - intentelo nuevamente\n");
            }

        }while (continuarCarga.equalsIgnoreCase("s"));

    }

    public void modificarCarro(Carrito carritoCargado){

        boolean lectura2;

        do {
            lectura2 = true;

            System.out.println("1- Agregar");
            System.out.println("2- Eliminar");
            System.out.println("3- Continuar");
            int op = teclado.nextInt();

            try {
                switch (op) {
                    case 1:
                        cargaCarrito(carritoCargado);
                        System.out.println(carritoCargado.mostrarCarrito());

                        //vuelve al menu
                        System.out.println("- volviendo al menu...\n");
                        modificarCarro(carritoCargado);

                        break;
                    case 2:

                        System.out.println("Ingrese el nombre de la flor que desea eliminar");
                        String nombreAborrar = teclado.next();
                        System.out.println("Ingrese el color de la flor que desea eliminar");
                        String color = teclado.next();
                        System.out.println("Ingrese la cantidad de la misma que desea borrar");
                        int cantidad = teclado.nextInt();

                        boolean rta = carritoCargado.eliminarDelCarrito(nombreAborrar,color,cantidad);

                        if (rta) {
                            System.out.println("Su flor se elimino");
                            System.out.println("\nCARRITO ACTUALIZADO");
                            System.out.println(carritoCargado.mostrarCarrito());
                            System.out.println("\n");

                            //vuelve al menu
                            System.out.println("- volviendo al menu...\n");
                            modificarCarro(carritoCargado);

                        } else {
                            System.out.println("No existe el nombre de la flor en su carrito");
                            System.out.println(carritoCargado.mostrarCarrito());

                            //vuelve al menu
                            modificarCarro(carritoCargado);
                        }
                        break;

                    case 3:
                        MenuEntrega(carritoCargado);
                        break;
                    default:
                        teclado.nextLine();
                        System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                        teclado.nextLine();
                        MenuCompra();
                        break;
                }
            } catch (InputMismatchException e) {
                lectura2 = false;
                teclado.nextLine();
                System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                teclado.nextLine();
            }

        }while (!lectura2);

    }

    //-----------------------------------------------------------
    public void MenuEntrega (Carrito compraFinal){

        MetodoDeEntrega eleccion=null;

        boolean lectura;
        do{
            lectura=true;
            try {
                System.out.println("\nENTREGA DEL PEDIDO");
                System.out.println("1- Retiro por el local");
                System.out.println("2- Envio a domicilio");
                int op=teclado.nextInt();

                switch (op){
                    case 1:
                        //Muestra de datos del local con totalFinal a pagar
                        eleccion = new Local("Irala","4690","Lunes a Viernes","9:00hs a 18:00hss");
                        System.out.println(eleccion.toString());

                        System.out.println("Por retiro en el local se le aplica un 10% de descuento a su compra!");
                        double precioDeLaCompra = compraFinal.getTotal();
                        double precioFinal = eleccion.calcularTotalDelEnvio(precioDeLaCompra);

                        compraFinal.setTotal(precioFinal);

                        System.out.println("\n> Su precio final es de: "+precioFinal);

                        //---------------------------------------------------------------------------------
                        System.out.println("\nQuiere elegir otra forma de entrega? s/n");
                        String decision = teclado.next();
                        if(decision.equalsIgnoreCase("n")){
                            //MENU DE PAGO
                            MenuMetodoDePago(compraFinal);
                        }
                        else{
                            MenuEntrega(compraFinal);
                        }
                        //----------------------------------------------------------------------------------

                        break;
                    case 2:
                        //Muestra de datos del domicilio ingresado con nuevo totalFinal debido al envio
                        System.out.println("\nIngrese los que le pediremos a continuacion:");
                        System.out.println("↓ CALLE");
                        String calle = teclado.next();
                        System.out.println("↓ ALTURA");
                        String altura = teclado.next();
                        System.out.println("↓ CODIGO POSTAL");
                        String codigoPostal = teclado.next();

                        eleccion = new Domicilio(calle,altura,codigoPostal);

                        double costoDelEnvio = eleccion.calcularTotalDelEnvio(compraFinal.getTotal());
                        System.out.println("\n>> Costo del envio: "+costoDelEnvio);

                        double precioFinal2 = compraFinal.getTotal() + costoDelEnvio;
                        System.out.println("\n→ Su precio final es de: "+precioFinal2);

                        compraFinal.setTotal(precioFinal2);

                        //---------------------------------------------------------------------------------
                        System.out.println("\nQuiere elegir otra forma de entrega? s/n");
                        String decision2 = teclado.next();

                        if(decision2.equalsIgnoreCase("n")){
                            //MENU DE PAGO
                            MenuMetodoDePago(compraFinal);
                        }
                        else{
                            MenuEntrega(compraFinal);
                        }
                        //----------------------------------------------------------------------------------
                        break;

                    default:
                        teclado.nextLine();
                        System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                        teclado.nextLine();
                        MenuEntrega(compraFinal);
                        break;
                }
            } catch (InputMismatchException e) {
                lectura = false;
                teclado.nextLine();
                System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                teclado.nextLine();
            }
        }while (!lectura);

    }

    //-----------------------------------------------------------
    public void MenuMetodoDePago (Carrito compraFinal){

        boolean lectura;
        Ticket<ArrayList<Eleccion>,String,Double> ticket = new Ticket<>();

        ArrayList<Eleccion> compra = compraFinal.getCarrito();
        String pago ="";
        Double totalAPagar = compraFinal.getTotal();

        do{
            lectura=true;
            try {
                System.out.println("\nMETODO DE PAGO");
                System.out.println("1- Efectivo");
                System.out.println("2- Tarjeta");
                System.out.println("3- CuentaDNI");
                int op = teclado.nextInt();

                switch (op){
                    case 1:
                        Efectivo eleccionEfectivo = new Efectivo();

                        System.out.println("Desea comenzar a efectuar el pago? s/n  ->De lo contrario la compra se dara de baja ");
                        String cancelar = teclado.next();

                        if(cancelar.equalsIgnoreCase("n")){
                            System.out.println(eleccionEfectivo.cancelarPago());
                            MenuPrincipal();
                        }
                        else{
                            System.out.println(eleccionEfectivo.procesarPago(compraFinal.getTotal()));
                            System.out.println("\n Gracias por su compra!");

                            //TICKETTT -------------------------------------------------------------------
                            System.out.println("\nGENERANDO TICKET..\n");

                            pago = eleccionEfectivo.toString();

                            ticket=generadorDeTicket(compra,pago,totalAPagar);
                            System.out.println(ticket.toString());
                            //--------------------------------------------------------------------------
                        }
                        MenuPrincipal();
                        break;

                    case 2:

                        Tarjeta tarjetaUsuario = new Tarjeta();
                        tarjetaUsuario = cargaTarjeta();
                        double totalDeLaCompra = compraFinal.getTotal();

                        if(tarjetaUsuario.getTipo().equalsIgnoreCase("debito")){

                            System.out.println("Desea comenzar a efectuar el pago? s/n  ->De lo contrario la compra se dara de baja ");
                            String cancelar2 = teclado.next();

                            if(cancelar2.equalsIgnoreCase("n")){
                                System.out.println(tarjetaUsuario.cancelarPago());
                            }
                            else{
                                //procesamos el pago una vez que la tajeta fue cargada y el usuario confirmo
                                System.out.println(tarjetaUsuario.procesarPago(totalDeLaCompra));
                                System.out.println("\nGracias por su compra!");

                                //TICKETTT -------------------------------------------------------------------
                                System.out.println("\nGENERANDO TICKET..\n");

                                pago = tarjetaUsuario.toString();

                                ticket=generadorDeTicket(compra,pago,totalAPagar);
                                System.out.println(ticket.toString());
                                //--------------------------------------------------------------------------

                            }
                            MenuPrincipal();
                        }

                        //cuotas
                        if(tarjetaUsuario.getTipo().equalsIgnoreCase("credito"))
                        {
                            System.out.println("Desea comenzar a efectuar el pago? s/n  ->De lo contrario la compra se dara de baja ");
                            String cancelar3 = teclado.next();

                            if(cancelar3.equalsIgnoreCase("n")){
                                System.out.println(tarjetaUsuario.cancelarPago());
                            }
                            else{
                                double total = eleccionDeCuotas(compraFinal);
                                //procesamos el pago una vez que la tajeta fue cargada y el usuario confirmo
                                System.out.println(tarjetaUsuario.procesarPago(total));
                                System.out.println("\nGracias por su compra!");

                                //TICKETTT -------------------------------------------------------------------
                                System.out.println("\nGENERANDO TICKET..\n");

                                pago = tarjetaUsuario.toString();

                                ticket=generadorDeTicket(compra,pago,total);
                                System.out.println(ticket.toString());
                                //--------------------------------------------------------------------------

                            }
                            MenuPrincipal();
                        }

                        break;

                    case 3:
                        CuentaDNI cuentaDNI = new CuentaDNI();
                        cuentaDNI = cargaCuentaDNI(compraFinal);
                        double totalDeLaCompra2 = compraFinal.getTotal();

                        System.out.println("Desea finalizar la compra? s/n");
                        String cancelar2 = teclado.next();

                        if(cancelar2.equalsIgnoreCase("n")){
                            System.out.println(cuentaDNI.cancelarPago());
                        }
                        else{
                            System.out.println(cuentaDNI.procesarPago(totalDeLaCompra2));
                            System.out.println("\nGracias por su compra!");

                            //TICKETTT -------------------------------------------------------------------
                            System.out.println("\nGENERANDO TICKET..\n");

                            pago = cuentaDNI.toString();

                            ticket=generadorDeTicket(compra,pago,totalAPagar);
                            System.out.println(ticket.toString());
                            //--------------------------------------------------------------------------
                        }

                        MenuPrincipal();

                        break;

                    default:
                        teclado.nextLine();
                        System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                        teclado.nextLine();
                        break;
                }
            } catch (InputMismatchException e) {
                lectura = false;
                teclado.nextLine();
                System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                teclado.nextLine();
                MenuMetodoDePago(compraFinal);
            }
        }while (!lectura);

    }

    //-----------------------------------------------------------
    //TARJETA
    public Tarjeta cargaTarjeta(){

        String seguir="";
        Tarjeta tarjetaUsuario = new Tarjeta();

        do {
            System.out.println("> Carga de datos de la tajeta\n");

            System.out.println("Ingrese solo el nombre del titular de la tarjeta");
            String nombreTitular = teclado.next();
            System.out.println("Ingrese solo el apellido del titular de la tarjeta");
            String apellidoTitular = teclado.next();

            //Excepcion lanzada si la tarjeta no tiene la cantidad de numeros correspondiente
            String numero="";
            try {
                numero=cargaNumeroDeLaTarjeta();

            } catch (NumeroDeLaTarjetaException e) {
                System.out.println(e.getMessage());
                numero=cargaNumeroDeLaTarjeta();
            }

            //Excepcion lanzada si el codigo ingresado no es de 3 digitos
            String cvv="";
            try {
                cvv = cargaCodigoDeSeguridad();
            }catch (CodigoDeSeguridadException e){
                System.out.println(e.getMessage());
                cvv=cargaCodigoDeSeguridad();
            }

            //Si es debito debe mostrarle las cuotas
            System.out.println("Ingrese el tipo (debito o credito)");
            String tipo = teclado.next();

            //cargamos la tarjeta del usuario
            tarjetaUsuario = new Tarjeta(nombreTitular, apellidoTitular, numero, cvv, tipo);

            System.out.println("\n"+tarjetaUsuario.toString());
            System.out.println("Los datos ingresados son correctos? s/n");
            seguir = teclado.next();

        }while (seguir.equalsIgnoreCase("n"));


        return tarjetaUsuario;
    }

    //cargar numero de la tarjeta //no teclado
    public String cargaNumeroDeLaTarjeta (){
        String numero="";

        while(true){
            System.out.println("Ingrese el numero de la tarjeta");
            numero = teclado.next();

            if(numero.length() != 16 && numero.length() != 15){
                throw new NumeroDeLaTarjetaException("ERROR: Los numero de la tarjeta son 16 o 15");
            }
            else {

                return numero;
            }
        }
    }

    //cargar codigo de seguridad
    public String cargaCodigoDeSeguridad () {
        String cvv="";

        while (true) {
            System.out.println("Ingrese el codigo de seguridad");
            cvv = teclado.next();

            if(cvv.length() != 3 ){
                throw new CodigoDeSeguridadException("ERROR: El numero de seguridad es de 3 digitos");
            }
            else {

                return cvv;
            }
        }
    }

    //cuotas para la tarjeta
    public double eleccionDeCuotas(Carrito compraFinal) {
        boolean lectura;
        double totalFinal=0.0;

        do {
            lectura = true;
            try {
                System.out.println("\nSeleccione la opcion correspondiente a la cantidad de cuotas deseada:");
                System.out.println("1- > 3 CUOTAS < ");
                System.out.println("2- > 6 CUOTAS <");
                System.out.println("3- > 12 CUOTAS <");
                int op = teclado.nextInt();

                double totalCompra = compraFinal.getTotal();
                double montoPorCuotaSinInteres=0.0;

                double interesPorCuota=0.0;
                double montoPorCuotaConInteres=0.0;
                double tasaAnual = 0.7275;


                switch (op) {
                    case 1:
                        int cantidadDeCuotas3 = 3;

                        montoPorCuotaSinInteres=totalCompra/cantidadDeCuotas3;

                        interesPorCuota=calcularIntereses(totalCompra,tasaAnual);
                        montoPorCuotaConInteres=montoPorCuotaSinInteres+interesPorCuota;

                        totalFinal=montoPorCuotaConInteres*cantidadDeCuotas3;

                        System.out.println("\nTOTAL DE LA COMPRA INICIAL: $"+formatDecimal(totalCompra));
                        System.out.println("--------------------------------------------------");
                        System.out.println("INTERES POR CUOTA: $"+formatDecimal(interesPorCuota));
                        System.out.println("VALOR POR CUOTA: $"+formatDecimal(montoPorCuotaConInteres));
                        System.out.println("--------------------------------------------------");
                        System.out.println("Su total ahora es de: $"+formatDecimal(totalFinal));

                        System.out.println("\n> Desea pagar con esta opcion de cuotas? s/n");
                        String eleccion1 = teclado.next();

                        if(eleccion1.equalsIgnoreCase("n")){
                            totalFinal=eleccionDeCuotas(compraFinal);
                        }

                        break;
                    case 2:
                        int cantidadDeCuotas6 = 6;

                        montoPorCuotaSinInteres=totalCompra/cantidadDeCuotas6;

                        interesPorCuota=calcularIntereses(totalCompra,tasaAnual);
                        montoPorCuotaConInteres=montoPorCuotaSinInteres+interesPorCuota;

                        totalFinal=montoPorCuotaConInteres*cantidadDeCuotas6;

                        System.out.println("\nTOTAL DE LA COMPRA INICIAL: $"+formatDecimal(totalCompra));
                        System.out.println("--------------------------------------------------");
                        System.out.println("INTERES POR CUOTA: $"+formatDecimal(interesPorCuota));
                        System.out.println("VALOR POR CUOTA: $"+formatDecimal(montoPorCuotaConInteres));
                        System.out.println("--------------------------------------------------");
                        System.out.println("Su total ahora es de: $"+formatDecimal(totalFinal));

                        System.out.println("\n> Desea pagar con esta opcion de cuotas? s/n");
                        String eleccion2 = teclado.next();

                        if(eleccion2.equalsIgnoreCase("n")){
                            totalFinal=eleccionDeCuotas(compraFinal);
                        }

                        break;

                    case 3:

                        int cantidadDeCuotas12 = 12;

                        montoPorCuotaSinInteres=totalCompra/cantidadDeCuotas12;

                        interesPorCuota=calcularIntereses(totalCompra,tasaAnual);
                        montoPorCuotaConInteres=montoPorCuotaSinInteres+interesPorCuota;

                        totalFinal=montoPorCuotaConInteres*cantidadDeCuotas12;

                        System.out.println("\nTOTAL DE LA COMPRA INICIAL: $"+formatDecimal(totalCompra));
                        System.out.println("--------------------------------------------------");
                        System.out.println("INTERES POR CUOTA: $"+formatDecimal(interesPorCuota));
                        System.out.println("VALOR POR CUOTA: $"+formatDecimal(montoPorCuotaConInteres));
                        System.out.println("--------------------------------------------------");
                        System.out.println("Su total ahora es de: $"+formatDecimal(totalFinal));

                        System.out.println("\n> Desea pagar con esta opcion de cuotas? s/n");
                        String eleccion3 = teclado.next();

                        if(eleccion3.equalsIgnoreCase("n")){
                            totalFinal=eleccionDeCuotas(compraFinal);
                        }
                        break;

                    default:
                        teclado.nextLine();
                        System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                        teclado.nextLine();
                        totalFinal=eleccionDeCuotas(compraFinal);
                        break;
                }
            } catch (InputMismatchException e) {
                lectura = false;
                teclado.nextLine();
                System.out.println("Ingrese un numero que corresponda al menu mostrado - Presione Enter para ver el menu nuevamente");
                teclado.nextLine();
            }

        }while (!lectura);

        return totalFinal;
    }

    public static double calcularIntereses(double monto, double tasaAnual) {
        double tasaMensual = tasaAnual / 12; // Tasa de interés mensual

        double intereses = monto * tasaMensual;

        return intereses;
    }

    public static String formatDecimal(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }

    //-------------------------------------------------------------
    //CUENTA DNI
    public CuentaDNI cargaCuentaDNI (Carrito compraFinal){

        String continuar="";
        String dni="";
        CuentaDNI cuentaDNI = new CuentaDNI();

        do {
            System.out.println("Ingrese su DNI para asi poder dar inicio al pago por cuenta DNI");
            dni = teclado.next();
            System.out.println("Su DNI es correcto? s/n");
            continuar = teclado.next();

        }while (continuar.equalsIgnoreCase("n"));

        System.out.println("En su aplicacion dirijase a la opcion -Pagar por clave DNI-");

        System.out.println("\nIngrese el monto total de la compra: $"+compraFinal.getTotal());

        //Excepcion lanzada en caso de que la clave generada sea distinta de 6 digitos.
        String claveDNI = "";
        try {
            claveDNI=ingresoDeClaveDNI();
        }catch (ClaveCuentaDNIException e){
            System.out.println(e.getMessage());
            claveDNI=ingresoDeClaveDNI();
        }

        cuentaDNI = new CuentaDNI(dni,compraFinal.getTotal(),claveDNI);


        return cuentaDNI;
    }

    //carga y verificacion de la clave
    public String ingresoDeClaveDNI(){

        String claveDni="";

        while(true){
            System.out.println("\n>Presione generar clave y escribala a continuacion: ");
            claveDni=teclado.next();

            if(claveDni.length() != 6){
                throw new ClaveCuentaDNIException("ERROR: La clave debe ser de 6 digitos");
            }
            else {

                return claveDni;
            }
        }

    }

    //-------------------------------------------------------------
    public Ticket generadorDeTicket (ArrayList<Eleccion> compraFinal, String pago, double totalDeLaCompra){

        Ticket<ArrayList<Eleccion>,String,Double> ticket;
        ticket= new Ticket<>(compraFinal,pago,totalDeLaCompra);

        return ticket;
    }

}


