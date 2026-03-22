package main;

import controller.*;
import model.Libro;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        APIController api = new APIController();
        BibliotecaController biblioteca = new BibliotecaController();
        FileController file = new FileController();

        int opcion;

        do {
            System.out.println("\n--- BIBLIOTECA STEPHEN KING ---");
            System.out.println("1. Cargar catalogo de libros");
            System.out.println("2. Ver ficha de un libro por ID");
            System.out.println("3. Añadir libro a mi lista de favoritos");
            System.out.println("4. Guardar mi lista de favoritos en el disco");
            System.out.println("5. Cargar mi lista de favoritos guardada");
            System.out.println("0. Salir del programa");
            System.out.print("Elige una opcion: ");

            while (!sc.hasNextInt()) {
                System.out.println("Por favor, introduce un numero valido.");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    List<Libro> libros = api.getLibros();
                    if (libros != null) {
                        biblioteca.setLibros(libros);
                        System.out.println("Catalogo sincronizado. Libros disponibles: " + libros.size());
                    }
                    break;

                case 2:
                    System.out.print("Introduce el ID del libro que quieres consultar: ");
                    int idBusqueda = sc.nextInt();
                    sc.nextLine();

                    Libro libroEncontrado = api.getLibroPorId(idBusqueda);
                    if (libroEncontrado != null) {
                        System.out.println("\n--- Informacion del Libro ---");
                        System.out.println(libroEncontrado);
                    } else {
                        System.out.println("No se encontro el libro en la base de datos.");
                    }
                    break;

                case 3:
                    System.out.print("Introduce el ID del libro que quieres guardar en favoritos: ");
                    int idFav = sc.nextInt();
                    sc.nextLine();
                    biblioteca.addFavorito(idFav);
                    break;

                case 4:
                    if (biblioteca.getFavoritos().isEmpty()) {
                        System.out.println("Tu lista de favoritos esta vacia. Añade algun libro primero.");
                    } else {
                        file.guardarFavoritos(biblioteca.getFavoritos());
                    }
                    break;

                case 5:
                    List<Libro> favs = file.cargarFavoritos();
                    if (favs != null && !favs.isEmpty()) {
                        System.out.println("\n--- Tus Libros Favoritos Guardados ---");
                        for (Libro f : favs) {
                            System.out.println("- " + f);
                        }
                    }
                    break;

                case 0:
                    System.out.println("Cerrando la biblioteca. ¡Hasta la proxima!");
                    break;
                default:
                    System.out.println("Opcion no valida. Intentalo de nuevo.");
            }

        } while (opcion != 0);

        sc.close();
    }
}