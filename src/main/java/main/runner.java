package main;

import games.GenericGame;
import games.TryToStudy;
import entities.command.CommandType;
import parser.Parser;
import parser.PhraseReduction;

import java.util.Scanner;

public class runner {
    public static void main(String[] args) {
        GenericGame game = new TryToStudy();

        game.init();

        Parser parser = new Parser();

        System.out.println(game.getCurrentRoom().getName());
        System.out.println("================================================");
        System.out.println(game.getCurrentRoom().getDescription());
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            PhraseReduction p = parser.analyze(command, game.getCurrentRoom(), game.getCommandList(), game.getMainCharacter());
            if (p.getCommand() != null && p.getCommand().getType() == CommandType.END) {
                System.out.println("Addio!");
                break;
            } else if(p.getCommand() != null){
                try{
                    game.getHandler().handle(p);
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
                System.out.println(p);
                System.out.println("================================================");
            }
        }

    }
}
