/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Usuari
 */
public class Usuari {
    private int id;
    private String nom;
    private String contassenya;
    private String rol;

    public Usuari(int id, String nom, String contassenya, String rol) {
        this.id = id;
        this.nom = nom;
        this.contassenya = contassenya;
        this.rol = rol;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContassenya() {
        return contassenya;
    }

    public void setContassenya(String contassenya) {
        this.contassenya = contassenya;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
}
