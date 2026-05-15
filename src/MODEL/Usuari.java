/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 * Classe abstracta que representa un usuari del sistema.
 * Conté les dades comunes dels diferents tipus d'usuaris.
 * 
 * Els usuaris poden tenir diferents rols:
 * administrador, esportista o entrenador.
 * 
 * @author Usuari
 */
public abstract class Usuari {

    private int id;
    private String nom;
    private String contrassenya;
    private Rol rol;

    /**
     * Enumeració dels diferents rols disponibles al sistema.
     */
    public static enum Rol {
        ADMIN, ESPORTISTA, ENTRENADOR
    }

    /**
     * Constructor de la classe Usuari.
     * 
     * @param id identificador de l'usuari
     * @param nom nom de l'usuari
     * @param contrassenya contrasenya de l'usuari
     * @param rol rol assignat a l'usuari
     */
    public Usuari(int id, String nom, String contrassenya, Rol rol) {
        this.id = id;
        this.nom = nom;
        this.contrassenya = contrassenya;
        this.rol = rol;
    }

    /**
     * Retorna l'identificador de l'usuari.
     * 
     * @return id de l'usuari
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica l'identificador de l'usuari.
     * 
     * @param id nou identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna el nom de l'usuari.
     * 
     * @return nom de l'usuari
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifica el nom de l'usuari.
     * 
     * @param nom nou nom de l'usuari
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retorna la contrasenya de l'usuari.
     * 
     * @return contasenya de l'usuari
     */
    public String getContassenya() {
        return contrassenya;
    }

    /**
     * Modifica la contrasenya de l'usuari.
     * 
     * @param contassenya nova contrasenya
     */
    public void setContassenya(String contassenya) {
        this.contrassenya = contassenya;
    }

    /**
     * Retorna el rol de l'usuari.
     * 
     * @return rol de l'usuari
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Modifica el rol de l'usuari.
     * 
     * @param rol nou rol de l'usuari
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
