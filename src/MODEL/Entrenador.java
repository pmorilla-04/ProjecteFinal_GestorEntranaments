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
public class Entrenador extends Usuari {

    private ArrayList<Comentari> comentaris = new ArrayList<>();

    public Entrenador(int id, String nom, String contrasenya) {
        super(id, nom, contrasenya);
    }

    public ArrayList<Comentari> getComentaris() {
        return comentaris;
    }

    public void setComentaris(ArrayList<Comentari> comentaris) {
        this.comentaris = comentaris;
    }
}
