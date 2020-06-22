/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.fileManager;

import exceptions.GameNotFoundException;
import exceptions.LanguageNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author enrico
 */
public class initLoader {
    
    public static final String CONFIG = "./src/main/resources/config.txt";
    
    public static String loadGameClass() throws Exception{
        BufferedReader input = new BufferedReader(new FileReader(CONFIG));
        
        String current;
        
        current = input.readLine();
        
        if(current.toLowerCase().startsWith("game")){
            current = current.replaceAll("game","");
            current = current.replaceAll(" ", "");
            current = current.replaceAll("=", "");
            current = current.replaceAll("\"", "");

            return "games." + current;
        }
        throw new GameNotFoundException();
    }
    
    public static String loadLanguage() throws Exception{
        BufferedReader input = new BufferedReader(new FileReader(CONFIG));
        
        String current;
        
        current = input.readLine();
        current = input.readLine();
        
        if(current.contains("language")){
            current = current.replaceAll("language", "");
            current = current.replaceAll("=", "");
            current = current.replaceAll(" ", "");
            current = current.replaceAll("\"", "");
            
            return "parser." + current + "Parser";
        }
        throw new LanguageNotFoundException();
    }
}
