/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.time.LocalDate;

/**
 *
 * @author Usuari
 */
public class Comentari {

    private int id;
    private String text;
    private LocalDate data;
    private Entrenador entrenador;
    private Entrenament entrenament;

    public Comentari(int id, String text, LocalDate data, Entrenador entrenador, Entrenament entrenament) {
        this.id = id;
        this.text = text;
        this.data = data;
        this.entrenador = entrenador;
        this.entrenament = entrenament;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Entrenament getEntrenament() {
        return entrenament;
    }

    public void setEntrenament(Entrenament entrenament) {
        this.entrenament = entrenament;
    }

    

    
}
