/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.time.LocalDate;

/**
 * Classe que representa un comentari
 * realitzat per un entrenador sobre un entrenament.
 * 
 * @author Usuari
 */
public class Comentari {

    private int id;
    private String text;
    private LocalDate data;
    private Entrenador entrenador;
    private Entrenament entrenament;

    private int entrenamentId;
    private int entrenadorId;

    /**
     * Constructor de la classe Comentari.
     * 
     * @param id identificador del comentari
     * @param text contingut del comentari
     * @param data data del comentari
     * @param entrenamentId identificador de l'entrenament
     * @param entrenadorId identificador de l'entrenador
     */
    public Comentari(int id, String text, LocalDate data,
            int entrenamentId, int entrenadorId) {

        this.id = id;
        this.text = text;
        this.data = data;
        this.entrenamentId = entrenamentId;
        this.entrenadorId = entrenadorId;
    }

    /**
     * Retorna l'identificador del comentari.
     * 
     * @return id del comentari
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica l'identificador del comentari.
     * 
     * @param id nou identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna el text del comentari.
     * 
     * @return text del comentari
     */
    public String getText() {
        return text;
    }

    /**
     * Modifica el text del comentari.
     * 
     * @param text nou text del comentari
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Retorna la data del comentari.
     * 
     * @return data del comentari
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Modifica la data del comentari.
     * 
     * @param data nova data
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Retorna l'identificador de l'entrenament.
     * 
     * @return id de l'entrenament
     */
    public int getEntrenamentId() {
        return entrenamentId;
    }

    /**
     * Modifica l'identificador de l'entrenament.
     * 
     * @param entrenamentId nou id de l'entrenament
     */
    public void setEntrenamentId(int entrenamentId) {
        this.entrenamentId = entrenamentId;
    }

    /**
     * Retorna l'identificador de l'entrenador.
     * 
     * @return id de l'entrenador
     */
    public int getEntrenadorId() {
        return entrenadorId;
    }

    /**
     * Modifica l'identificador de l'entrenador.
     * 
     * @param entrenadorId nou id de l'entrenador
     */
    public void setEntrenadorId(int entrenadorId) {
        this.entrenadorId = entrenadorId;
    }
}
