/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLLER;

import static CONTROLLER.Principal.rutaIFitxerLogActual;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Classe encarregada de gestionar fitxers TXT i logs.
 * 
 * Permet crear fitxers, escriure logs
 * i llegir registres del sistema.
 * 
 * @author Usuari
 */
public class GestiorFitxersTXT {

    /**
     * Crea un fitxer de text.
     * 
     * @param rutaNomFitxer ruta completa del fitxer
     */
    public static void creacioFitxer(String rutaNomFitxer) {

        try {

            File fitxer = new File(rutaNomFitxer);

            if (fitxer.createNewFile()) {

                System.out.println("Fitxer creat! : " + fitxer.getName());

            } else {

                System.out.println("El fitxer ja existeix! ");
            }

        } catch (IOException e) {

            System.out.println("Hi ha hagut una errada");
            e.printStackTrace();
        }
    }

    /**
     * Crea el fitxer de log del dia actual.
     */
    public static void creacioLog() {

        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyyMMdd");

        String diaActual = LocalDateTime.now().format(formater);

        String directori = "src/Log/";

        String rutaCompleta = directori + diaActual + ".log";

        rutaIFitxerLogActual = rutaCompleta;

        creacioFitxer(rutaCompleta);
    }

    /**
     * Escriu un missatge al fitxer de log.
     * 
     * @param text missatge que es guardar? al log
     */
    public static void escripturaAFitxerLog(String text) {

        try {

            FileWriter fitxer = new FileWriter(rutaIFitxerLogActual, true);

            DateTimeFormatter formater = DateTimeFormatter.ofPattern("HH:mm:ss");

            String instant = LocalDateTime.now().format(formater);

            fitxer.write("\t" + instant + "\t" + text + "\n");

            fitxer.close();

            System.out.println("S'ha escrit correctament al fitxer!");

        } catch (IOException e) {

            System.out.println("Hi ha hagut una errada a l'hora d'escriure");
            e.printStackTrace();
        }
    }

    /**
     * Mostra els logs d'una data concreta.
     * 
     * @param data data del log en format yyyyMMdd
     */
    public static void mostrarLogsPerData(String data) {

        Principal.logs.clear();

        String ruta = "src/Log/" + data + ".log";

        try {

            File fitxer = new File(ruta);

            Scanner lector = new Scanner(fitxer);

            while (lector.hasNextLine()) {

                String linia = lector.nextLine();

                String[] parts = linia.trim().split("\\t");

                if (parts.length >= 2) {

                    String hora = parts[0];

                    String missatge = parts[1];

                    MODEL.Log log = new MODEL.Log(hora, missatge);

                    Principal.logs.add(log);
                }
            }

            lector.close();

        } catch (Exception e) {

            System.out.println("Error llegint log");
            e.printStackTrace();
        }
    }
}
