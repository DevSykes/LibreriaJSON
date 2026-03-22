package controller;

import model.Libro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileController {

    private static final String FILE_NAME = "favoritos.dat";

    // --- GUARDAR FAVORITOS ---
    public void guardarFavoritos(List<Libro> favoritos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(favoritos);

            System.out.println("Lista de favoritos guardada con exito.");

        } catch (IOException e) {
            System.out.println("Hubo un problema al guardar la lista de favoritos: " + e.getMessage());
        }
    }

    // --- CARGAR FAVORITOS ---
    @SuppressWarnings("unchecked")
    public List<Libro> cargarFavoritos() {
        File fichero = new File(FILE_NAME);

        if (!fichero.exists()) {
            System.out.println("No hay ninguna lista de favoritos guardada.");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {

            return (List<Libro>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Hubo un problema al leer la lista: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}