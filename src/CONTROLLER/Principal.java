/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CONTROLLER;

import DATA.Conexion;
import MODEL.Comentari;
import MODEL.Entrenament;
import MODEL.Esportista;
import MODEL.Estadistica;
import MODEL.Log;
import MODEL.TipusEsport;
import MODEL.Usuari;
import VIEW.frmValidacio;
import java.util.ArrayList;

/**
 *
 * @author Usuari
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static String rol;
    public static String usuariLoginat;
    public static String rutaIFitxerLogActual;
    public static Integer usuariId;

    public static ArrayList<Log> logs = new ArrayList<>();
    public static ArrayList<Usuari> usuaris = new ArrayList<>();
    public static ArrayList<Esportista> esportistes = new ArrayList<>();
    public static ArrayList<Comentari> comentaris = new ArrayList<>();
    public static ArrayList<Entrenament> entrenaments = new ArrayList<>();
    public static ArrayList<Estadistica> estadistiques = new ArrayList<>();
    public static ArrayList<TipusEsport> tipusesports = new ArrayList<>();

    public static void main(String[] args) {
        // TODO code application logic here
        GestiorFitxersTXT.creacioLog();
        GestiorFitxersTXT.escripturaAFitxerLog("Programa iniciat");

        Conexion ccc = new Conexion();
        ccc.connectar();
        GestiorFitxersTXT.escripturaAFitxerLog("Conexio base de dades");

        frmValidacio f = new frmValidacio();
        f.setVisible(true);
        GestiorFitxersTXT.escripturaAFitxerLog("Obrint formulari Validacio");

    }

}
