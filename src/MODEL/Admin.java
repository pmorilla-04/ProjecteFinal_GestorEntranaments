/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 * Classe que representa un administrador del sistema.
 * 
 * L'administrador té permisos de gestió avançada.
 * 
 * @author Usuari
 */
public class Admin extends Usuari {

    /**
     * Constructor de la classe Admin.
     * 
     * @param id identificador de l'administrador
     * @param nom nom de l'administrador
     * @param contrasenya contrasenya de l'administrador
     */
    public Admin(int id, String nom, String contrasenya) {
        super(id, nom, contrasenya, Rol.ADMIN);
    }
}
