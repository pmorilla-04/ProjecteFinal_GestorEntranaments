/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Usuari
 */
public class Log {

    private String hora;
    private String missatge;

    public Log(String hora, String missatge) {
        this.hora = hora;
        this.missatge = missatge;
    }

    public String getHora() {
        return hora;
    }

    public String getMissatge() {
        return missatge;
    }
}
