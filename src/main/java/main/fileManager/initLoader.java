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
 * Questa classe si occupa della gestione dei file
 * @author enrico
 */
public class initLoader {

    /**
     * Path del file config in cui sono presenti <br>
     *     1) Il nome del gioco che dovrà essere istanziato e caricato
     *     2) La lingua a cui è poi associato un particolare tipo di Parser, nel nostro caso solo Italiano
     */
    public static final String CONFIG = "./src/main/resources/config.txt";

    /**
     * @return Restituisce il nome della classe del gioco che vogliamo giocare
     * @throws Exception
     */
    public static String loadGameClass() throws Exception{
        BufferedReader input = new BufferedReader(new FileReader(CONFIG));

        String current;

        current = input.readLine();
        input.close();
        if(current.toLowerCase().startsWith("game")){
            current = current.replaceAll("game","");
            current = current.replaceAll(" ", "");
            current = current.replaceAll("=", "");
            current = current.replaceAll("\"", "");

            return "games." + current;
        }
        throw new GameNotFoundException();
    }

    /**
     * @return Restituisce il nome della classe Parser specifica per la lingua selezionata
     * @throws Exception
     */
    public static String loadLanguage() throws Exception{
        BufferedReader input = new BufferedReader(new FileReader(CONFIG));
        
        String current;
        
        current = input.readLine();
        current = input.readLine();
        input.close();
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
        TryToStudy game = new TryToStudy();
        game.init();
        obj.writeObject(game);
        obj.close();
    }

    /**
     * Consente di salvare lo stato attuale del gioco
     * @param path Percorso in cui salvare il gioco
     * @param game Gioco da salvare
     * @throws Exception
     */
    public static void saveGame(String path, GenericGame game) throws Exception{
        if(!path.endsWith(".dat")) {
            path.concat(".dat");
        }
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(path));
        obj.writeObject(game);
        obj.close();
    }

    /**
     * Consente di caricare un salvataggio del gioco selezionato da file
     * @param path
     * @return Restituisce un gioco caricato da file
     * @throws Exception
     */
    public static GenericGame loadGame(String path) throws Exception{
        ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(path));
        GenericGame game = (GenericGame) inStream.readObject();
        inStream.close();
        return game;
    }
}
