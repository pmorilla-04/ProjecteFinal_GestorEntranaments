package MODEL;

import java.time.LocalDate;

/**
 * Classe que representa un entrenament esportiu.
 * 
 * Guarda informació relacionada amb la data,
 * la dist?ncia, la durada, la intensitat
 * i l'estat de validació de l'entrenament.
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
    private boolean validat;
    private Intensitat intensitat;

    private int usuariId;
    private int tipusEsportId;

    /**
     * Enumeració dels nivells d'intensitat
     * d'un entrenament.
     */
    public enum Intensitat {
        BAIXA, MITJA, ALTA
    }

    /**
     * Constructor de la classe Entrenament.
     * 
     * @param id identificador de l'entrenament
     * @param data data de l'entrenament
     * @param distancia dist?ncia recorreguda
     * @param descripcio descripció de l'entrenament
     * @param completat indica si l'entrenament est? completat
     * @param validat indica si l'entrenament est? validat
     * @param intensitat intensitat de l'entrenament
     * @param usuariId identificador de l'usuari
     * @param tipusEsportId identificador del tipus d'esport
     */
    public Entrenament(int id, LocalDate data, int distancia,
            String descripcio, boolean completat,
            boolean validat, Intensitat intensitat,
            int usuariId, int tipusEsportId) {

        this.id = id;
        this.data = data;
        this.distancia = distancia;
        this.descripcio = descripcio;
        this.completat = completat;
        this.validat = validat;
        this.intensitat = intensitat;
        this.usuariId = usuariId;
        this.tipusEsportId = tipusEsportId;
    }

    /**
     * Retorna si l'entrenament est? validat.
     * 
     * @return true si est? validat
     */
    public boolean isValidat() {
        return validat;
    }

    /**
     * Modifica l'estat de validació.
     * 
     * @param validat nou estat de validació
     */
    public void setValidat(boolean validat) {
        this.validat = validat;
    }

    /**
     * Retorna la dist?ncia recorreguda.
     * 
     * @return dist?ncia de l'entrenament
     */
    public int getDistancia() {
        return distancia;
    }

    /**
     * Modifica la dist?ncia recorreguda.
     * 
     * @param distancia nova dist?ncia
     */
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    /**
     * Retorna l'identificador de l'entrenament.
     * 
     * @return id de l'entrenament
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica l'identificador de l'entrenament.
     * 
     * @param id nou identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna la data de l'entrenament.
     * 
     * @return data de l'entrenament
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Modifica la data de l'entrenament.
     * 
     * @param data nova data
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Retorna la durada de l'entrenament en minuts.
     * 
     * @return durada en minuts
     */
    public int getDuradaMinuts() {
        return duradaMinuts;
    }

    /**
     * Modifica la durada de l'entrenament.
     * 
     * @param duradaMinuts nova durada en minuts
     */
    public void setDuradaMinuts(int duradaMinuts) {
        this.duradaMinuts = duradaMinuts;
    }

    /**
     * Retorna la descripció de l'entrenament.
     * 
     * @return descripció de l'entrenament
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Modifica la descripció de l'entrenament.
     * 
     * @param descripcio nova descripció
     */
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    /**
     * Retorna si l'entrenament est? completat.
     * 
     * @return true si est? completat
     */
    public boolean isCompletat() {
        return completat;
    }

    /**
     * Modifica l'estat de completat.
     * 
     * @param completat nou estat
     */
    public void setCompletat(boolean completat) {
        this.completat = completat;
    }

    /**
     * Retorna l'identificador de l'usuari.
     * 
     * @return id de l'usuari
     */
    public int getUsuariId() {
        return usuariId;
    }

    /**
     * Modifica l'identificador de l'usuari.
     * 
     * @param usuariId nou identificador
     */
    public void setUsuariId(int usuariId) {
        this.usuariId = usuariId;
    }

    /**
     * Retorna l'identificador del tipus d'esport.
     * 
     * @return id del tipus d'esport
     */
    public int getTipusEsportId() {
        return tipusEsportId;
    }

    /**
     * Modifica l'identificador del tipus d'esport.
     * 
     * @param tipusEsportId nou identificador
     */
    public void setTipusEsportId(int tipusEsportId) {
        this.tipusEsportId = tipusEsportId;
    }

    /**
     * Retorna la intensitat de l'entrenament.
     * 
     * @return intensitat de l'entrenament
     */
    public Intensitat getIntensitat() {
        return intensitat;
    }

    /**
     * Modifica la intensitat de l'entrenament.
     * 
     * @param intensitat nova intensitat
     */
    public void setIntensitat(Intensitat intensitat) {
        this.intensitat = intensitat;
    }
}