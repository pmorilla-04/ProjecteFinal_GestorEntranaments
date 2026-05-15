/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DATA;

import static CONTROLLER.Principal.comentaris;
import static CONTROLLER.Principal.entrenaments;
import static CONTROLLER.Principal.estadistiques;
import static CONTROLLER.Principal.rol;
import static CONTROLLER.Principal.usuaris;
import static DATA.Conexion.password;
import static DATA.Conexion.url;
import static DATA.Conexion.user;
import MODEL.Comentari;
import MODEL.Entrenament;
import MODEL.Entrenament.Intensitat;
import MODEL.TipusEsport;
import MODEL.Usuari;
import MODEL.Usuari.Rol;
import MODEL.Estadistica;
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

                int id = rs.getInt("id");
                LocalDate data = rs.getDate("data").toLocalDate();
                int duradaMinuts = rs.getInt("duradaMinuts");
                int distancia = rs.getInt("distancia");
                String descripcio = rs.getString("descripcio");

                Entrenament.Intensitat intensitat
                        = Entrenament.Intensitat.valueOf(rs.getString("intensitat"));

                boolean completat = rs.getBoolean("completat");
                boolean validat = rs.getBoolean("validat"); // ? NOU

                int usuariId = rs.getInt("usuari_id");
                int tipusEsportId = rs.getInt("tipus_esport_id");

                entrenaments.add(
                        new Entrenament(
                                id,
                                data,
                                duradaMinuts,
                                distancia,
                                descripcio,
                                completat,
                                validat,
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
            Boolean completat, Boolean validat,
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

        if (validat != null) {
            sql.append(" AND validat = ?");
            params.add(validat);
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
                                rs.getBoolean("validat"),
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

    public static Entrenament getEntrenament(int id) {
        String sql = "SELECT * FROM entrenament WHERE id = ?";

        try (
                Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    int entrenamentId = rs.getInt("id");
                    LocalDate data = rs.getDate("data").toLocalDate();
                    int duradaMinuts = rs.getInt("duradaMinuts");
                    int distancia = rs.getInt("distancia");
                    String descripcio = rs.getString("descripcio");

                    // Conversió ENUM intensitat
                    Entrenament.Intensitat intensitat
                            = Entrenament.Intensitat.valueOf(
                                    rs.getString("intensitat")
                            );

                    boolean completat = rs.getBoolean("completat");
                    boolean validat = rs.getBoolean("validat");
                    int usuariId = rs.getInt("usuari_id");
                    int tipusEsportId = rs.getInt("tipus_esport_id");

                    return new Entrenament(
                            entrenamentId,
                            data,
                            duradaMinuts,
                            distancia,
                            descripcio,
                            completat,
                            validat,
                            intensitat,
                            usuariId,
                            tipusEsportId
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static void validarEntrenament(int id, boolean validat) {

    String sql = "UPDATE entrenament SET validat = ? WHERE id = ?";

    try (
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        ps.setBoolean(1, validat);
        ps.setInt(2, id);

        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// 2. COMENTARIS
    public static void mostrarComentari(int idEntrenament) {

        comentaris.clear();

        String sql = "SELECT * FROM comentari WHERE entranament_id = ?";

        try (
                Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEntrenament);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int entrenamentId = rs.getInt("entranament_id");
                    int entrenadorId = rs.getInt("entranador_id");

                    comentaris.add(
                            new Comentari(
                                    rs.getInt("id"),
                                    rs.getString("text"),
                                    rs.getDate("data").toLocalDate(),
                                    entrenamentId,
                                    entrenadorId
                            )
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //AFEGIR COMENTARIS
    public static void afegirComentari(String text, LocalDate data, int entranador_id, int entranament_id) {
        String sql = " INSERT INTO comentari (text, `data`, entranador_id, entranament_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, text); //TEXT
            ps.setDate(2, java.sql.Date.valueOf(data)); //DATA
            ps.setInt(3, entranador_id); //ID ENTRANADOR
            ps.setInt(4, entranament_id); //ID ENTRENAMENT

            int files = ps.executeUpdate();
            System.out.println("Files inserides: " + files);
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
        String sql = "SELECT \n"
                + "    te.nom AS esport,\n"
                + "    COUNT(e.id) AS total_entrenaments,\n"
                + "    COUNT(DISTINCT e.usuari_id) AS usuaris_actius,\n"
                + "    SUM(e.duradaMinuts) AS minuts_totals,\n"
                + "    SUM(e.distancia) AS km_totals,\n"
                + "    AVG(e.duradaMinuts) AS mitjana_durada,\n"
                + "    SUM(CASE WHEN e.completat = 1 THEN 1 ELSE 0 END) AS completats\n"
                + "FROM tipus_esport te\n"
                + "LEFT JOIN entrenament e ON e.tipus_esport_id = te.id\n"
                + "GROUP BY te.nom\n"
                + "ORDER BY total_entrenaments DESC;";

        try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String esport = rs.getString("esport");
                int totalEntrenaments = rs.getInt("total_entrenaments");
                int usuarisActius = rs.getInt("usuaris_actius");
                int minutsTotals = rs.getInt("minuts_totals");
                double kmTotals = rs.getDouble("km_totals");
                double mitjanaDurada = rs.getDouble("mitjana_durada");
                int completats = rs.getInt("completats");

                estadistiques.add(new Estadistica(esport, totalEntrenaments, usuarisActius, minutsTotals, kmTotals, mitjanaDurada, completats));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
