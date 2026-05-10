/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
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

    public Estadistica(String esport, int totalEntrenaments, int usuarisActius, int minutsTotals, double kmTotals, double mitjanaDurada, int completats) {
        this.esport = esport;
        this.totalEntrenaments = totalEntrenaments;
        this.usuarisActius = usuarisActius;
        this.minutsTotals = minutsTotals;
        this.kmTotals = kmTotals;
        this.mitjanaDurada = mitjanaDurada;
        this.completats = completats;
    }

    public String getEsport() {
        return esport;
    }

    public void setEsport(String esport) {
        this.esport = esport;
    }

    public int getTotalEntrenaments() {
        return totalEntrenaments;
    }

    public void setTotalEntrenaments(int totalEntrenaments) {
        this.totalEntrenaments = totalEntrenaments;
    }

    public int getUsuarisActius() {
        return usuarisActius;
    }

    public void setUsuarisActius(int usuarisActius) {
        this.usuarisActius = usuarisActius;
    }

    public int getMinutsTotals() {
        return minutsTotals;
    }

    public void setMinutsTotals(int minutsTotals) {
        this.minutsTotals = minutsTotals;
    }

    public double getKmTotals() {
        return kmTotals;
    }

    public void setKmTotals(double kmTotals) {
        this.kmTotals = kmTotals;
    }

    public double getMitjanaDurada() {
        return mitjanaDurada;
    }

    public void setMitjanaDurada(double mitjanaDurada) {
        this.mitjanaDurada = mitjanaDurada;
    }

    public int getCompletats() {
        return completats;
    }

    public void setCompletats(int completats) {
        this.completats = completats;
    }
    
    
}
