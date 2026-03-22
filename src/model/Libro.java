package model;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class Libro implements Serializable {
    private int id;

    @SerializedName("Title")
    private String title;

    private String author; // Se queda null porque la API no lo trae en la raíz, pero no rompe nada

    @SerializedName("Year")
    private int year;

    public Libro(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // --- GETTERS Y SETTERS ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return id + " - " + title + " (" + year + ")";
    }
}