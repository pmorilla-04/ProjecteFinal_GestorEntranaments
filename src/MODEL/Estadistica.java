package MODEL;

/**
 * Classe que emmagatzema dades estadístiques
 * relacionades amb els entrenaments esportius.
 * 
 * @author Usuari
 */
public class Estadistica {

    private String esport;
    private int totalEntrenaments;
    private int usuarisActius;
    private int minutsTotals;
    private double kmTotals;
    private double mitjanaDurada;
    private int completats;

    /**
     * Constructor de la classe Estadistica.
     * 
     * @param esport nom de l'esport
     * @param totalEntrenaments total d'entrenaments
     * @param usuarisActius total d'usuaris actius
     * @param minutsTotals minuts totals entrenats
     * @param kmTotals quil?metres totals recorreguts
     * @param mitjanaDurada mitjana de durada dels entrenaments
     * @param completats total d'entrenaments completats
     */
    public Estadistica(String esport, int totalEntrenaments,
            int usuarisActius, int minutsTotals,
            double kmTotals, double mitjanaDurada,
            int completats) {

        this.esport = esport;
        this.totalEntrenaments = totalEntrenaments;
        this.usuarisActius = usuarisActius;
        this.minutsTotals = minutsTotals;
        this.kmTotals = kmTotals;
        this.mitjanaDurada = mitjanaDurada;
        this.completats = completats;
    }

    /**
     * Retorna el nom de l'esport.
     * 
     * @return nom de l'esport
     */
    public String getEsport() {
        return esport;
    }

    /**
     * Modifica el nom de l'esport.
     * 
     * @param esport nou nom de l'esport
     */
    public void setEsport(String esport) {
        this.esport = esport;
    }

    /**
     * Retorna el total d'entrenaments.
     * 
     * @return total d'entrenaments
     */
    public int getTotalEntrenaments() {
        return totalEntrenaments;
    }

    /**
     * Modifica el total d'entrenaments.
     * 
     * @param totalEntrenaments nou total
     */
    public void setTotalEntrenaments(int totalEntrenaments) {
        this.totalEntrenaments = totalEntrenaments;
    }

    /**
     * Retorna el total d'usuaris actius.
     * 
     * @return usuaris actius
     */
    public int getUsuarisActius() {
        return usuarisActius;
    }

    /**
     * Modifica el total d'usuaris actius.
     * 
     * @param usuarisActius nou total
     */
    public void setUsuarisActius(int usuarisActius) {
        this.usuarisActius = usuarisActius;
    }

    /**
     * Retorna els minuts totals entrenats.
     * 
     * @return minuts totals
     */
    public int getMinutsTotals() {
        return minutsTotals;
    }

    /**
     * Modifica els minuts totals entrenats.
     * 
     * @param minutsTotals nous minuts totals
     */
    public void setMinutsTotals(int minutsTotals) {
        this.minutsTotals = minutsTotals;
    }

    /**
     * Retorna els quil?metres totals recorreguts.
     * 
     * @return quil?metres totals
     */
    public double getKmTotals() {
        return kmTotals;
    }

    /**
     * Modifica els quil?metres totals.
     * 
     * @param kmTotals nous quil?metres totals
     */
    public void setKmTotals(double kmTotals) {
        this.kmTotals = kmTotals;
    }

    /**
     * Retorna la mitjana de durada dels entrenaments.
     * 
     * @return mitjana de durada
     */
    public double getMitjanaDurada() {
        return mitjanaDurada;
    }

    /**
     * Modifica la mitjana de durada.
     * 
     * @param mitjanaDurada nova mitjana
     */
    public void setMitjanaDurada(double mitjanaDurada) {
        this.mitjanaDurada = mitjanaDurada;
    }

    /**
     * Retorna el total d'entrenaments completats.
     * 
     * @return entrenaments completats
     */
    public int getCompletats() {
        return completats;
    }

    /**
     * Modifica el total d'entrenaments completats.
     * 
     * @param completats nou total
     */
    public void setCompletats(int completats) {
        this.completats = completats;
    }
}