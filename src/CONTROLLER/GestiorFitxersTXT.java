/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLLER;

import static CONTROLLER.Principal.rutaIFitxerLogActual;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Usuari
 */
public class GestiorFitxersTXT {

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
    
    //Creacio LOG
    public static void creacioLog(){
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyyMMdd");
        String diaActual = LocalDateTime.now().format(formater);
        String directori = "src/Log/";
        String rutaCompleta = directori + diaActual + ".log";
        rutaIFitxerLogActual= rutaCompleta;
        creacioFitxer(rutaCompleta);
    }
    
        //ESCRIPTURA DE FITXER LOG
    public static void escripturaAFitxerLog( String text) {
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
}
