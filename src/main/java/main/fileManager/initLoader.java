/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.fileManager;

import exceptions.GameNotFoundException;
import exceptions.LanguageNotFoundException;
import games.GenericGame;
import games.TryToStudy;

import javax.swing.*;
import java.io.*;

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

    //TODO : cancella prima di consegnare
    public static void writeDefaultGame() throws Exception {
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("/home/enrico/IdeaProjects/EnricoPallotta_MAProject/src/main/resources/savings/TryToStudyDefault.dat"));
        GenericGame game = new TryToStudy();
        game.init();
        obj.writeObject(game);
    }

    public static void saveGame(String path, GenericGame game) throws Exception{
        if(!path.endsWith(".dat")) {
            path.concat(".dat");
        }
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(path));
        obj.writeObject(game);
    }

    public static GenericGame loadGame(String path) throws Exception{
        ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(path));
        GenericGame game = (GenericGame) inStream.readObject();
        return game;
    }
}
