/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package MODEL;

import java.util.ArrayList;

/**
 * Classe que representa un esportista.
 *
 * Un esportista pot tenir diversos entrenaments associats al seu perfil.
 *
 * @author Usuari
 */
public class Esportista extends Usuari {

    private ArrayList<Entrenament> entrenaments = new ArrayList<>();

    /**
     * Constructor de la classe Esportista.
     *
     * @param id identificador de l'esportista
     * @param nom nom de l'esportista
     * @param contrasenya contrasenya de l'esportista
     */
    public Esportista(int id, String nom, String contrasenya) {
        super(id, nom, contrasenya, Rol.ESPORTISTA);
    }

    /**
     * Retorna la llista d'entrenaments.
     *
     * @return llista d'entrenaments
     */
    public ArrayList<Entrenament> getEntrenaments() {
        return entrenaments;
    }

    /**
     * Modifica la llista d'entrenaments.
     *
     * @param entrenaments nova llista d'entrenaments
     */
    public void setEntrenaments(ArrayList<Entrenament> entrenaments) {
        this.entrenaments = entrenaments;
    }
}
