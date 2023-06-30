import catalogo.Flor;
import catalogo.JsonUtiles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import usuario.ArchivosUtiles;
import usuario.Usuario;

import java.util.HashMap;
import java.util.HashSet;;

public class Main {
    public static void main(String[] args) {

        //----------------------------------------- Catalogo JSON --------------------------------------------------

        // lectura del archivo catalogo de flores el cual contiene todas las flores disponibles para comprar
        String catalogoDeFloresJSON = JsonUtiles.leer("catalogoDeFlores");

        // pasar datos JSON (que tienen los datos del catalogo) a Java.
        HashMap<String, Flor> catalogoDeFlores = new HashMap<>();

        try {
            JSONArray jsonArrayCatalogo = new JSONArray(catalogoDeFloresJSON);

            for (int i = 0; i < jsonArrayCatalogo.length(); i++) {
                JSONObject jsonObject = jsonArrayCatalogo.getJSONObject(i);
                Flor flor = new Flor();
                flor.setNombre(jsonObject.getString(flor.NOMBRE_FLOR));
                flor.setCodigo(jsonObject.getString(flor.CODIGO_FLOR));
                flor.setColor(jsonObject.getString(flor.COLOR_FLOR));
                flor.setOrigen(jsonObject.getString(flor.ORIGEN_FLOR));
                flor.setPrecioPorUnidad(jsonObject.getDouble(flor.PRECIO_FLOR));
                catalogoDeFlores.put(flor.getCodigo(), flor);
            }

            } catch (JSONException ex) {
                System.out.println(ex.getMessage());
            }

        HashMap<String, Flor> catalogoDeFloresCompleto = catalogoDeFlores;

        //----------------------------------------- Usuarios ARCHIVO --------------------------------------------------

        //Carga de usuarios en un archivo "usuarios.dat"
        HashSet<Usuario> usuarios = new HashSet<>();

        Usuario usuario1 = new Usuario("mailen","cruz","mailen@gmail.com","123456789");
        Usuario usuario2 = new Usuario("fiorella","cruz","fiorella@gmail.com","1234567810");
        Usuario usuario3 = new Usuario("julian","pages","julian@gmail.com","154632899");
        Usuario usuario4 = new Usuario("sandra","cañada","sandra@gmail.com","1256345877");

        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        usuarios.add(usuario4);

        ArchivosUtiles.grabar(usuarios);

        usuarios = ArchivosUtiles.leer();


        //----------------------------------------- Menu Bienvenido ------------------------------------------------


        System.out.println("\n -------- Bienvenido a la floreria virtual <3 --------");

        Menu menu = new Menu(catalogoDeFloresCompleto,usuarios);
        menu.MenuPrincipal();

        //-----------------------------------------------------------------------------------------------------------------
    }
}