/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author enrico
 */
public class GameNotFoundException extends Exception{

    @Override
    public String getMessage() {
        return "Il gioco descritto nel file config.txt non esiste";
    }
    
    
}
