/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 * Classe que representa un tipus d'esport.
 * 
 * @author Usuari
 */
public class TipusEsport {

    private int id;
    private String nom;

    /**
     * Constructor de la classe TipusEsport.
     * 
     * @param id identificador del tipus d'esport
     * @param nom nom del tipus d'esport
     */
    public TipusEsport(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    /**
     * Retorna l'identificador del tipus d'esport.
     * 
     * @return id del tipus d'esport
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica l'identificador del tipus d'esport.
     * 
     * @param id nou identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna el nom del tipus d'esport.
     * 
     * @return nom del tipus d'esport
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifica el nom del tipus d'esport.
     * 
     * @param nom nou nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retorna el nom del tipus d'esport.
     * 
     * @return nom del tipus d'esport
     */
    @Override
    public String toString() {
        return nom;
    }
}
