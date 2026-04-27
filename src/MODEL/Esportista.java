/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuari
 */
public class Esportista extends Usuari {

    private List<Entrenament> entrenaments;

    public Esportista(int id, String nom, String contassenya, String rol) {
        super(id, nom, contassenya, rol);

    }

    public List<Entrenament> getEntrenaments() {
        return entrenaments;
    }
}
