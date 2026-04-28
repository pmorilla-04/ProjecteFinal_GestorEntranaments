/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.util.ArrayList;

/**
 *
 * @author Usuari
 */
public abstract class Usuari {
    private int id;
    private String nom;
    private String contrassenya;
    
    public enum Rol {
    ADMIN, ESPORTISTA, ENTRENADOR
}

    public Usuari(int id, String nom, String contrassenya) {
        this.id = id;
        this.nom = nom;
        this.contrassenya = contrassenya;
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
        return contrassenya;
    }

    public void setContassenya(String contassenya) {
        this.contrassenya = contassenya;
    }

    
    
    
}
