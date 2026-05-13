/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.util.ArrayList;

/**
 * Classe que representa un entrenador del sistema.
 * 
 * Un entrenador pot gestionar comentaris
 * sobre els entrenaments dels esportistes.
 * 
 * @author Usuari
 */
public class Entrenador extends Usuari {

    private ArrayList<Comentari> comentaris = new ArrayList<>();

    /**
     * Constructor de la classe Entrenador.
     * 
     * @param id identificador de l'entrenador
     * @param nom nom de l'entrenador
     * @param contrasenya contrasenya de l'entrenador
     */
    public Entrenador(int id, String nom, String contrasenya) {
        super(id, nom, contrasenya, Rol.ENTRENADOR);
    }

    /**
     * Retorna la llista de comentaris de l'entrenador.
     * 
     * @return llista de comentaris
     */
    public ArrayList<Comentari> getComentaris() {
        return comentaris;
    }

    /**
     * Modifica la llista de comentaris.
     * 
     * @param comentaris nova llista de comentaris
     */
    public void setComentaris(ArrayList<Comentari> comentaris) {
        this.comentaris = comentaris;
    }
}