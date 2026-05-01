/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Usuari
 */
public class Admin extends Usuari {

    public Admin(int id, String nom, String contrasenya) {
        super(id, nom, contrasenya, Rol.ADMIN);
    }
}
