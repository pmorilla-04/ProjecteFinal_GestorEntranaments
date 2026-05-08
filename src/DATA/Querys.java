/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DATA;

import static CONTROLLER.Principal.comentarisEntrenaments;
import static CONTROLLER.Principal.entrenaments;
import static CONTROLLER.Principal.rol;
import static CONTROLLER.Principal.usuaris;
import static DATA.Conexion.password;
import static DATA.Conexion.url;
import static DATA.Conexion.user;
import MODEL.Entrenament;
import MODEL.Entrenament.Intensitat;
import MODEL.TipusEsport;
import MODEL.Usuari;
import MODEL.Usuari.Rol;
import MODEL.Comentarientrenament;
import java.sql.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Usuari
 */
public class Querys {

    //1. ENTRENAMENTS
    //MOSTRAR ENTRENAMENTS
    public static void mostrarEntrenaments() {

        entrenaments.clear();
        String sql = "SELECT * FROM entrenament";

        try (
                Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                int id = rs.getInt("id"); //ID
                LocalDate data = rs.getDate("data").toLocalDate(); //DATA (LOCAALDATE)
                int duradaMinuts = rs.getInt("duradaMinuts"); //DURADA
                int distancia = rs.getInt("distancia"); //DISTANCIA
                String descripcio = rs.getString("descripcio"); //DESCRIPCIO

                //  ENUM INTENSITAT conversió 
                Entrenament.Intensitat intensitat
                        = Entrenament.Intensitat.valueOf(
                                rs.getString("intensitat")
                        );

                boolean completat = rs.getBoolean("completat"); //COMPLETAT
                int usuariId = rs.getInt("usuari_id"); //ID USUARI
                int tipusEsportId = rs.getInt("tipus_esport_id"); //ID TIPUS ESPORT

                entrenaments.add(
                        new Entrenament(
                                id,
                                data,
                                duradaMinuts,
                                distancia,
                                descripcio,
                                completat,
                                intensitat,
                                usuariId,
                                tipusEsportId
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void filtrarEntrenament(Integer id, LocalDate data, Integer duradaMinuts,
            Integer distancia,
            Entrenament.Intensitat intensitat,
            Boolean completat,
            Integer tipusEsportId) {

        StringBuilder sql = new StringBuilder("SELECT * FROM entrenament WHERE 1=1");
        ArrayList<Object> params = new ArrayList<>();
        //TODO: En Principal.java mai posem un valor per usuariId aixi que sempre es null i seria intressant despress filtrar que cada usuari veigi lo seu PENSAR HO DESPRES
        if (CONTROLLER.Principal.usuariId != null) {
            sql.append(" AND usuari_id = ?");
            params.add(CONTROLLER.Principal.usuariId);
        }

        if (id != null) {
            sql.append(" AND id = ?");
            params.add(id);
        }

        if (data != null) {
            sql.append(" AND data = ?");
            params.add(java.sql.Date.valueOf(data));
        }

        if (duradaMinuts != null) {
            sql.append(" AND duradaMinuts >= ?");
            params.add(duradaMinuts);
        }

        if (distancia != null) {
            sql.append(" AND distancia >= ?");
            params.add(distancia);
        }

        if (intensitat != null) {
            sql.append(" AND LOWER(intensitat) = ?");
            params.add(intensitat.name().toLowerCase());
        }

        if (completat != null) {
            sql.append(" AND completat = ?");
            params.add(completat);
        }

        if (tipusEsportId != null) {
            sql.append(" AND tipus_esport_id = ?");
            params.add(tipusEsportId);
        }

        try (
                Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            entrenaments.clear();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Entrenament.Intensitat intens
                        = Entrenament.Intensitat.valueOf(
                                rs.getString("intensitat").toUpperCase()
                        );

                entrenaments.add(
                        new Entrenament(
                                rs.getInt("id"),
                                rs.getDate("data").toLocalDate(),
                                rs.getInt("duradaMinuts"),
                                rs.getInt("distancia"),
                                rs.getString("descripcio"),
                                rs.getBoolean("completat"),
                                intens,
                                rs.getInt("usuari_id"),
                                rs.getInt("tipus_esport_id")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //AFEGIR ENTRENAMENT
    public static void afegirEntrenament(LocalDate data, int duradaMinuts, int distancia, String descripcio, Intensitat intensitat, boolean completat, int usuari_id, int tipus_esport_id) {

        String sql = " INSERT INTO entrenament \n"
                + "        (data, duradaMinuts, distancia, descripcio, intensitat, completat, usuari_id, tipus_esport_id)\n"
                + "        VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(data));
            ps.setInt(2, duradaMinuts);
            ps.setInt(3, distancia);
            ps.setString(4, descripcio);
            ps.setString(5, intensitat.name());
            ps.setBoolean(6, completat);
            ps.setInt(7, usuari_id);
            ps.setInt(8, tipus_esport_id);

            int files = ps.executeUpdate();
            System.out.println("Files inserides: " + files);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // MODIFICAR ENTRENAMENT
    public static void actualitzarEntrenament(
            Integer id,
            LocalDate data,
            Integer duradaMinuts,
            Integer distancia,
            String descripcio,
            Intensitat intensitat,
            Boolean completat,
            Integer tipusEsportId,
            Integer userId) {

        StringBuilder sql = new StringBuilder("UPDATE entrenament SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (data != null) {
            sql.append("data = ?, ");
            params.add(java.sql.Date.valueOf(data));
        }

        if (duradaMinuts != null) {
            sql.append("duradaMinuts = ?, ");
            params.add(duradaMinuts);
        }

        if (distancia != null) {
            sql.append("distancia = ?, ");
            params.add(distancia);
        }

        if (descripcio != null) {
            sql.append("descripcio = ?, ");
            params.add(descripcio);
        }

        if (intensitat != null) {
            sql.append("intensitat = ?, ");
            params.add(intensitat.name());
        }

        if (completat != null) {
            sql.append("completat = ?, ");
            params.add(completat);
        }

        if (tipusEsportId != null) {
            sql.append("tipus_esport_id = ?, ");
            params.add(tipusEsportId);
        }

        if (userId != null) {
            sql.append("usuari_id = ?, ");
            params.add(userId);
        }

       
        sql.setLength(sql.length() - 2);

        sql.append(" WHERE id = ?");
        params.add(id);

        try (
                Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            
            int files = ps.executeUpdate();
            System.out.println("Files actualitzades: " + files);

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static ResultSet getIntensitats() throws SQLException {
        Connection conn = Conexion.connectar();

        String sql = "SHOW COLUMNS FROM entrenament LIKE 'intensitat'";

        PreparedStatement ps = conn.prepareStatement(sql);
        return ps.executeQuery();
    }

    public static ResultSet getTipusEsport() throws SQLException {
        Connection conn = Conexion.connectar();

        String sql = "SELECT id, nom FROM tipus_esport";

        PreparedStatement ps = conn.prepareStatement(sql);
        return ps.executeQuery();
    }

    //2. COMENTARIS
    //MOTRAR COMENTARIS AMB ENTRENAMENTS
    public static void mostrarComentarisAmbEntrenament() {
        comentarisEntrenaments.clear();

        String sql
                = "SELECT c.id AS id_comentari, c.text AS comentari, c.data AS data_comentari, "
                + "ent.id AS id_entrenament, ent.data AS data_entrenament, ent.duradaMinuts, ent.distancia, "
                + "ent.descripcio, ent.intensitat, ent.completat, "
                + "esp.nom AS nom_esportista, entrena.nom AS nom_entrenador, te.nom AS tipus_esport "
                + "FROM comentari c "
                + "JOIN usuari entrena ON c.entranador_id = entrena.id "
                + "JOIN entrenament ent ON c.entranament_id = ent.id "
                + "JOIN usuari esp ON ent.usuari_id = esp.id "
                + "JOIN tipus_esport te ON ent.tipus_esport_id = te.id "
                + "ORDER BY c.data DESC";

        try (Connection conn = DriverManager.getConnection(
                url, user, password); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Comentarientrenament c = new Comentarientrenament(
                        rs.getInt("id_comentari"),
                        rs.getString("comentari"),
                        rs.getDate("data_comentari").toLocalDate(),
                        rs.getInt("id_entrenament"),
                        rs.getDate("data_entrenament").toLocalDate(),
                        rs.getInt("duradaMinuts"),
                        rs.getInt("distancia"),
                        rs.getString("intensitat"),
                        rs.getBoolean("completat"),
                        rs.getString("descripcio"),
                        rs.getString("nom_esportista"),
                        rs.getString("nom_entrenador"),
                        rs.getString("tipus_esport")
                );

                comentarisEntrenaments.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //AFEGIR COMENTARIS
    public static void afegirComentari(int id, String text) {
        String sql = "INSERT INTO comentari (text) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id); //ID USUARI
            ps.setString(2, text); //TEXT

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //3. USUARIS
    //MOSTRAR USUARIS
    public static void mostrarUsuaris() {
        usuaris.clear();
        String sql = "SELECT * FROM usuari";

        try (
                Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String contrassenya = rs.getString("contrassenya");
                Rol rol = Rol.valueOf(rs.getString("rol").toUpperCase());

                usuaris.add(
                        new Usuari(id, nom, contrassenya, rol) {
                }
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //AFEGIR USUARI
    public static void afegirUsuari(String nom, String contrassenya, Rol rol) {
        String sql = "INSERT INTO usuari (nom, contrassenya, rol) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nom); //NOM
            ps.setString(2, contrassenya); //CONTRASSENYA
            ps.setString(3, rol.name()); //ROL

            int files = ps.executeUpdate();
            System.out.println("Files inserides: " + files);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //MODIFICAR USUARI
    public static void modificarUsuari(int id, String nom, String contrassenya, Rol rol) {
        String sql = "UPDATE usuari SET nom = ?, contrassenya = ?, rol = ? WHERE id =?";

        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nom); //NOM
            ps.setString(2, contrassenya); //CONTRASSENYA
            ps.setString(3, rol.name()); //ROL
            ps.setInt(4, id); //ID
            int files = ps.executeUpdate();
            System.out.println("Files modificades: " + files);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ELIMINAR USUARI
    public static void eliminarUsuari(int id) {
        String sql = "DELETE FROM usuari WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); //ID

            int files = ps.executeUpdate();
            System.out.println("Files eliminades:  " + files);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //4. ESTADISTIQUES
    public static void mostrarEstadistiques() {
        String sql = "";

        try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
