/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CONTROLLER;

import DATA.Conexion;
import VIEW.frmValidacio;

/**
 *
 * @author Usuari
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion ccc = new Conexion();
        ccc.connectar();
        
        frmValidacio f = new frmValidacio();
        f.setVisible(true);
    }
    
}
