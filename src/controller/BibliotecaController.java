package controller;

import model.Libro; // <--- IMPORTANTE
import java.util.ArrayList;
import java.util.List;

public class BibliotecaController {

    private List<Libro> libros;
    private List<Libro> favoritos;

    public BibliotecaController() {
        this.libros = new ArrayList<>();
        this.favoritos = new ArrayList<>();
    }

    public void setLibros(List<Libro> libros) {
        if (libros != null) {
            this.libros = libros;
        }
    }

    public void addFavorito(int id) {
        if (this.libros == null || this.libros.isEmpty()) {
            System.out.println("No hay libros cargados en la biblioteca. Importalos primero con la opcion 1.");
            return;
        }

        boolean encontrado = false;

        for (Libro l : this.libros) {
            if (l.getId() == id) {
                this.favoritos.add(l);
                System.out.println("Libro añadido a favoritos: " + l.getTitle());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontro ningun libro con el ID: " + id);
        }
    }

    public List<Libro> getFavoritos() {
        return favoritos;
    }
}