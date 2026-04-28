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

 
public class Esportista extends Usuari {

    private ArrayList<Entrenament> entrenaments = new ArrayList<>();

    public Esportista(int id, String nom, String contrasenya) {
        super(id, nom, contrasenya);
    }

    public ArrayList<Entrenament> getEntrenaments() {
        return entrenaments;
    }

    public void setEntrenaments(ArrayList<Entrenament> entrenaments) {
        this.entrenaments = entrenaments;
    }

    
}

