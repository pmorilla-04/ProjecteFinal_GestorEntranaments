/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DATA;

import static CONTROLLER.Principal.entrenaments;
import static DATA.Conexion.password;
import static DATA.Conexion.url;
import static DATA.Conexion.user;
import MODEL.Entrenament;

import java.sql.*;

import com.mysql.cj.protocol.Resultset;
import java.time.LocalDate;

/**
 *
 * @author Usuari
 */
public class Querys {

    //MOSTRAR ENTRENAMENTS
    public static void mostrarEntrenaments() {

    String sql = "SELECT id, data, duradaMinuts, descripcio, intensitat, completat FROM entrenament";

    try (
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)
    ) {

        while (rs.next()) {

            int id = rs.getInt("id");
            LocalDate data = rs.getDate("data").toLocalDate();
            int duradaMinuts = rs.getInt("duradaMinuts");
            String descripcio = rs.getString("descripcio");
            String tipus = rs.getString("intensitat");
            boolean completat = rs.getBoolean("completat");

            entrenaments.add(
                new Entrenament(id, data, tipus, duradaMinuts, descripcio, completat)
            );
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    }
    
