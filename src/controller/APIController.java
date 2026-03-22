package controller;

import com.google.gson.Gson;
import model.Libro;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class APIController {

    private static final String URL_API = "https://stephen-king-api.onrender.com/api/books";
    private static final String URL_SINGLE_BOOK = "https://stephen-king-api.onrender.com/api/book/";

    // --- CLASE AUXILIAR PARA MAPEAR EL JSON DE LA API ---
    // La API envuelve la lista en un objeto que contiene una variable llamada "data"
    private class RespuestaAPI {
        private List<Libro> data;

        public List<Libro> getData() {
            return data;
        }
    }

    // 1. Obtener todos los libros
    public List<Libro> getLibros() {
        try {
            URL url = new URL(URL_API);
            InputStreamReader reader = new InputStreamReader(url.openStream());

            Gson gson = new Gson();

            // Leemos el objeto raíz (RespuestaAPI) en lugar de la lista directamente
            RespuestaAPI respuesta = gson.fromJson(reader, RespuestaAPI.class);

            if (respuesta != null && respuesta.getData() != null) {
                return respuesta.getData(); // Aquí ya devolvemos la lista limpia
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Error al obtener la lista de libros: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // 2. Obtener un solo libro por ID
    public Libro getLibroPorId(int id) {
        try {
            URL url = new URL(URL_SINGLE_BOOK + id);
            InputStreamReader reader = new InputStreamReader(url.openStream());

            Gson gson = new Gson();

            // La API para un solo libro devuelve un objeto directo que tiene dentro una propiedad "data"
            // Vamos a usar la misma lógica de lectura:
            RespuestaSingleAPI respuestaSingle = gson.fromJson(reader, RespuestaSingleAPI.class);

            if (respuestaSingle != null && respuestaSingle.getData() != null) {
                return respuestaSingle.getData();
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Error al buscar el libro con ID " + id + ": " + e.getMessage());
            return null;
        }
    }

    // Clase auxiliar para un solo libro
    private class RespuestaSingleAPI {
        private Libro data;

        public Libro getData() {
            return data;
        }
    }
}