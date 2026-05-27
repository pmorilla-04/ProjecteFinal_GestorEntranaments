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
import com.formdev.flatlaf.FlatLightLaf;
import java.util.ArrayList;

/**
 * Classe principal de l'aplicació.
 * 
 * S'encarrega d'inicialitzar el sistema,
 * connectar amb la base de dades i
 * obrir el formulari de validació.
 * 
 * @author Usuari
 */
public class Principal {

    /**
     * Rol de l'usuari loguejat.
     */
    public static String rol;

    /**
     * Nom de l'usuari loguejat.
     */
    public static String usuariLoginat;

    /**
     * Ruta del fitxer de log actual.
     */
    public static String rutaIFitxerLogActual;

    /**
     * Identificador de l'usuari loguejat.
     */
    public static Integer usuariId;

    /**
     * Llista de logs del sistema.
     */
    public static ArrayList<Log> logs = new ArrayList<>();

    /**
     * Llista d'usuaris.
     */
    public static ArrayList<Usuari> usuaris = new ArrayList<>();

    /**
     * Llista d'esportistes.
     */
    public static ArrayList<Esportista> esportistes = new ArrayList<>();

    /**
     * Llista de comentaris.
     */
    public static ArrayList<Comentari> comentaris = new ArrayList<>();

    /**
     * Llista d'entrenaments.
     */
    public static ArrayList<Entrenament> entrenaments = new ArrayList<>();

    /**
     * Llista d'estadístiques.
     */
    public static ArrayList<Estadistica> estadistiques = new ArrayList<>();

    /**
     * Llista de tipus d'esports.
     */
    public static ArrayList<TipusEsport> tipusesports = new ArrayList<>();

    /**
     * Metode principal que inicia l'aplicació.
     * 
     * @param args arguments de línia de comandes
     */
    public static void main(String[] args) {
        FlatLightLaf.setup();
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