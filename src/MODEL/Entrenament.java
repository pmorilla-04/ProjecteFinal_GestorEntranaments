/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Usuari
 */
public class Entrenament {

    private int id;
    private LocalDate data;
    private String tipus;
    private int duradaMinuts;
    private String descripcio;
    private boolean completat;
    ArrayList<Comentari> comentaris = new ArrayList<Comentari>();

    public Entrenament(int id, LocalDate data, String tipus, int duradaMinuts, String descripcio, boolean completat) {
        this.id = id;
        this.data = data;
        this.tipus = tipus;
        this.duradaMinuts = duradaMinuts;
        this.descripcio = descripcio;
        this.completat = completat;
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

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
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

    public ArrayList<Comentari> getComentaris() {
        return comentaris;
    }

    public void setComentaris(ArrayList<Comentari> comentaris) {
        this.comentaris = comentaris;
    }
    
    

}
