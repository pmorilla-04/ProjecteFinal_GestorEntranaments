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
    private Entranador entrenador;

    public Comentari(int id, String text, LocalDate data, Entranador entrenador) {
        this.id = id;
        this.text = text;
        this.data = data;
        this.entrenador = entrenador;
    }

    public String getText() {
        return text;
    }

    public Entranador getEntrenador() {
        return entrenador;
    }
}