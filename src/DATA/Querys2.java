/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DATA;

import static CONTROLLER.Principal.comentaris;
import static CONTROLLER.Principal.entrenaments;
import static DATA.Conexion.password;
import static DATA.Conexion.url;
import static DATA.Conexion.user;
import MODEL.Comentari;
import MODEL.Entrenament;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 *
 * @author Usuari
 */
public class Querys2 {

    public static int tornarIdTipusEsportAmbNom(String nom) {
        String sql = "SELECT id FROM tipus_esport WHERE nom = ? LIMIT 1;";
          int id = 1;
         try (
                Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nom);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return id;
        }
    }
}
