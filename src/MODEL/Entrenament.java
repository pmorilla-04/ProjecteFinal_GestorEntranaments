/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuari
 */
public class Entrenament {

    private int id;
    private LocalDate data;
    private int duradaMinuts;
    private int distancia;
    private String descripcio;
    private boolean completat;

    private Intensitat intensitat;
   
    private int usuariId;
    private int tipusEsportId;

   

    public enum Intensitat {
        BAIXA, MITJA, ALTA
    }

    public Entrenament(int id, LocalDate data, int duradaMinuts, int distancia, String descripcio, boolean completat, Intensitat intensitat,  int usuariId, int tipusEsportId) {
        this.id = id;
        this.data = data;
        this.duradaMinuts = duradaMinuts;
        this.distancia = distancia;
        this.descripcio = descripcio;
        this.completat = completat;
        this.intensitat = intensitat;
       
        this.usuariId = usuariId;
        this.tipusEsportId = tipusEsportId;
    }

  



    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getDuradaMinuts() {
        return duradaMinuts;
    }

    public void setDuradaMinuts(int duradaMinuts) {
        this.duradaMinuts = duradaMinuts;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public boolean isCompletat() {
        return completat;
    }

    public void setCompletat(boolean completat) {
        this.completat = completat;
    }

    public int getUsuariId() {
        return usuariId;
    }

    public void setUsuariId(int usuariId) {
        this.usuariId = usuariId;
    }

    public int getTipusEsportId() {
        return tipusEsportId;
    }

    public void setTipusEsportId(int tipusEsportId) {
        this.tipusEsportId = tipusEsportId;
    }

    public Intensitat getIntensitat() {
        return intensitat;
    }

    public void setIntensitat(Intensitat intensitat) {
        this.intensitat = intensitat;
    }
    
    
    

}
