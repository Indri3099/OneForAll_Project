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
public class LanguageNotFoundException extends Exception{

    @Override
    public String getMessage() {
        return "Il linguaggio selezionato nel file config.txt non esiste";
    }
    
}
