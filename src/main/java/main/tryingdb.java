package main;

import entities.Score;
import main.scoreRest.DBManager;
import main.scoreRest.RestHandling;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class tryingdb {
    public static void main(String[] args) {
        try
        {
            Score score = new Score(new Time(1000),50,100,"ciaoneeee");
            score.setName("enrico2");
            RestHandling.saveScore(score);
            score.setName("enrico3");
            RestHandling.saveScore(score);

            for(Score scores : RestHandling.getScore()){
                System.out.println(scores.getName());
            }
        }
        catch( Exception e )
        {
            System.out.println( e.getMessage() );
        }
    }
}
