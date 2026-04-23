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
    Entranador entrenador;
    Entrenament entrenament;
    private int id;
    private String text;
    private LocalDate data;

    public Comentari(Entranador entrenador, Entrenament entrenament, int id, String text, LocalDate data) {
        this.entrenador = entrenador;
        this.entrenament = entrenament;
        this.id = id;
        this.text = text;
        this.data = data;
    }

    public Entranador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entranador entrenador) {
        this.entrenador = entrenador;
    }

    public Entrenament getEntrenament() {
        return entrenament;
    }

    public void setEntrenament(Entrenament entrenament) {
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
    
    
}
