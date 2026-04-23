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
public class Entranador extends Usuari{
            ArrayList <Esportista> esportistes = new ArrayList <Esportista>();

    public Entranador(int id, String nom, String contassenya, String rol) {
        super(id, nom, contassenya, rol);
    }
    
}
