package main;

import exceptions.GameNotFoundException;
import exceptions.LanguageNotFoundException;
import games.GenericGame;
import java.io.*;

/**
 * Questa classe si occupa della gestione dei file (salvataggio e caricamento)
 * @author enrico
 */
public class FileManager {

    /**
     * Path del file config in cui sono presenti <br>
     *     1) Il path del salvataggio di un gioco da caricare come gioco di default
     *     2) La lingua a cui è poi associato un particolare tipo di Parser, nel nostro caso solo Italiano
     */
    public static final String CONFIG = "./src/main/resources/config.txt";

    /**
     * @return Restituisce il path del salvataggio di default
     * @throws Exception
     */
    public static String loadDefaultPath() throws Exception{
        BufferedReader input = new BufferedReader(new FileReader(CONFIG));

        String current;

        current = input.readLine();
        input.close();
        if(current.toLowerCase().startsWith("game")){
            current = current.substring(current.indexOf("\"") +1 ,current.lastIndexOf("\""));
            return current;
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

    /**
     * Consente di salvare lo stato attuale del gioco
     * @param path Percorso in cui salvare il gioco
     * @param game Gioco da salvare
     * @throws Exception
     */
    public static void saveGame(String path, GenericGame game) throws Exception{
        if(!path.endsWith(".dat")) {
            path = path.concat(".dat");
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
