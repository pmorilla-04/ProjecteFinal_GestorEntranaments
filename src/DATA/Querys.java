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
         entrenaments.clear();
        String sql = "SELECT * FROM entrenament";

        try (
                Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

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

    //FILTRAR ENTRENAMENTS
    /*public static void filtrarEntrenament(String cadena) {
        String sql = "";

        try (
                Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + cadena + "%");
            entrenaments.clear();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                entrenaments.add(new Entrenament(
                        rs.getInt("Id"),
                        rs.getDate("data").toLocalDate(),
                        rs.getInt("duradaMinuts"),
                        rs.getString("descripcio"),
                        rs.getString("intensitat"),
                        rs.getBoolean("completat")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    
    //AFEGIR ENTRENAMENT
    public static void afegirEntrenament(LocalDate data, int duradaMinuts, String descripcio, String intensitat, boolean completat, int usuari_id, int tipus_esport_id){
    String sql="INSERT INTO entrenament (data, duradaMinuts, descripcio, intensitat, completat, usuari_id, tipus_esport_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
       try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {
           
            ps.setDate(1, java.sql.Date.valueOf(data));
            ps.setInt(2, duradaMinuts);
            ps.setString(2, descripcio);
            ps.setString(3, intensitat);
            ps.setBoolean(4, completat);
            ps.setInt(5, usuari_id);
            ps.setInt(6, tipus_esport_id);
            int files = ps.executeUpdate();
            System.out.println("Files inserides: " + files);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //MODIFICAR ENTRENAMENT
    public static void modificarEntrenament(int id, LocalDate data, int duradaMinuts, String descripcio, String intensitat, boolean completat, int usuari_id, int tipus_esport_id){
        String sql = "UPADTE entrenament SET id = ?, data = ?";
    }
    
    //ELIMINAR ENTRENAMENT
     public static void eliminarEntrenament(int id) {
        String sql = "DELETE FROM entrenament WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int files = ps.executeUpdate();
            System.out.println("Files eliminades: " + files);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
