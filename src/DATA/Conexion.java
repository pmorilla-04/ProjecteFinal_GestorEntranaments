/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DATA;

import CONTROLLER.GestiorFitxersTXT;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuari
 */
public class Conexion {

    public static String url = "jdbc:mysql://localhost:3306/Entranaments";
    public static String user = "root";
    public static String password = "PauMO223*";
    //public static String sql = "SELECT id, nom FROM persones";

    public static Connection connectar() {

        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connexió OK");
            //GestiorFitxersTXT.escripturaAFitxerLog("Conexio a la base de dades correcte");
        } catch (SQLException e) {
            System.out.println("No s'ha pogut establir la connexió");
            e.printStackTrace();
           // GestiorFitxersTXT.escripturaAFitxerLog("Algo ha fallat al conectar amb la base de dades");
        }

        return con;
    }
    
    
}
