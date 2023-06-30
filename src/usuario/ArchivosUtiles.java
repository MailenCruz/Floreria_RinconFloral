package usuario;

import java.io.*;
import java.util.HashSet;

public class ArchivosUtiles {

    public static void grabar(HashSet<Usuario> usuarios)
    {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try
        {
            fileOutputStream = new FileOutputStream("usuarios.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (Usuario usuario : usuarios)
            {
                objectOutputStream.writeObject(usuario);
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();

                if (objectOutputStream != null)
                    objectOutputStream.close();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    public static HashSet<Usuario> leer()
    {
        HashSet<Usuario> usuarioHashSet = new HashSet<>();

        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try
        {
            fileInputStream = new FileInputStream("usuarios.dat");
            objectInputStream = new ObjectInputStream(fileInputStream);

            while (true)
            {
                Usuario aux = (Usuario) objectInputStream.readObject();
                usuarioHashSet.add(aux);
            }
        }
        catch (EOFException ex)
        {
            //System.out.println("FIN de ARCHIVO");
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            try
            {
                if (fileInputStream!=null)
                    fileInputStream.close();

                if (objectInputStream!=null)
                    objectInputStream.close();
            }
            catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

        }
        return usuarioHashSet;
    }


}
