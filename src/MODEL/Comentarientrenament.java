/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Usuari
 */
import java.time.LocalDate;

public class Comentarientrenament {

    private int idComentari;
    private String comentari;
    private LocalDate dataComentari;

    private int idEntrenament;
    private LocalDate dataEntrenament;
    private int duradaMinuts;
    private int distancia;
    private String intensitat;
    private boolean completat;
    private String descripcio;

    private String nomEsportista;
    private String nomEntrenador;
    private String tipusEsport;

    public Comentarientrenament(int idComentari, String comentari, LocalDate dataComentari,
            int idEntrenament, LocalDate dataEntrenament,
            int duradaMinuts, int distancia,
            String intensitat, boolean completat,
            String descripcio,
            String nomEsportista,
            String nomEntrenador,
            String tipusEsport) {

        this.idComentari = idComentari;
        this.comentari = comentari;
        this.dataComentari = dataComentari;
        this.idEntrenament = idEntrenament;
        this.dataEntrenament = dataEntrenament;
        this.duradaMinuts = duradaMinuts;
        this.distancia = distancia;
        this.intensitat = intensitat;
        this.completat = completat;
        this.descripcio = descripcio;
        this.nomEsportista = nomEsportista;
        this.nomEntrenador = nomEntrenador;
        this.tipusEsport = tipusEsport;
    }


    public int getIdComentari() {
        return idComentari;
    }

    public String getComentari() {
        return comentari;
    }

    public LocalDate getDataComentari() {
        return dataComentari;
    }

    public int getIdEntrenament() {
        return idEntrenament;
    }

    public LocalDate getDataEntrenament() {
        return dataEntrenament;
    }

    public int getDuradaMinuts() {
        return duradaMinuts;
    }

    public int getDistancia() {
        return distancia;
    }

    public String getIntensitat() {
        return intensitat;
    }

    public boolean isCompletat() {
        return completat;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public String getNomEsportista() {
        return nomEsportista;
    }

    public String getNomEntrenador() {
        return nomEntrenador;
    }

    public String getTipusEsport() {
        return tipusEsport;
    }

    public void setIdComentari(int idComentari) {
        this.idComentari = idComentari;
    }

    public void setComentari(String comentari) {
        this.comentari = comentari;
    }

    public void setDataComentari(LocalDate dataComentari) {
        this.dataComentari = dataComentari;
    }

    public void setIdEntrenament(int idEntrenament) {
        this.idEntrenament = idEntrenament;
    }

    public void setDataEntrenament(LocalDate dataEntrenament) {
        this.dataEntrenament = dataEntrenament;
    }

    public void setDuradaMinuts(int duradaMinuts) {
        this.duradaMinuts = duradaMinuts;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public void setIntensitat(String intensitat) {
        this.intensitat = intensitat;
    }

    public void setCompletat(boolean completat) {
        this.completat = completat;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void setNomEsportista(String nomEsportista) {
        this.nomEsportista = nomEsportista;
    }

    public void setNomEntrenador(String nomEntrenador) {
        this.nomEntrenador = nomEntrenador;
    }

    public void setTipusEsport(String tipusEsport) {
        this.tipusEsport = tipusEsport;
    }
    
    
}
