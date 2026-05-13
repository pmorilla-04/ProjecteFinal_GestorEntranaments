/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 * Classe que representa una entrada de log del sistema.
 * 
 * Guarda l'hora i el missatge d'un esdeveniment.
 * 
 * @author Usuari
 */
public class Log {

    private String hora;
    private String missatge;

    /**
     * Constructor de la classe Log.
     * 
     * @param hora hora del registre
     * @param missatge missatge del registre
     */
    public Log(String hora, String missatge) {
        this.hora = hora;
        this.missatge = missatge;
    }

    /**
     * Retorna l'hora del log.
     * 
     * @return hora del log
     */
    public String getHora() {
        return hora;
    }

    /**
     * Retorna el missatge del log.
     * 
     * @return missatge del log
     */
    public String getMissatge() {
        return missatge;
    }
}
