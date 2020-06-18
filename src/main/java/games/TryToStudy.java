package games;

import entities.Room;
import entities.character.MainCharacter;
import entities.character.NPC;
import entities.command.Command;
import entities.command.CommandType;
import entities.objects.ObjectContainer;
import entities.objects.StdObject;
import events.BrotherEventHandler;
import events.Event;
import events.EventHandler;
import games.GenericGame;
import main.TTSActionHandler;
import parser.PhraseReduction;

import java.util.Arrays;
import java.util.Collections;

public class TryToStudy extends GenericGame {

    private final int POINTGOAL = 100;

    private int actualPoints = 0;

    public TryToStudy() {
        setHandler(new TTSActionHandler(this, System.out));
    }

    public int getActualPoints() {
        return actualPoints;
    }

    public void setActualPoints(int actualPoints) {
        this.actualPoints = actualPoints;
    }

    @Override
    public void init() {
        //Commands
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getCommandList().add(nord);
        Command iventory = new Command(CommandType.INVENTORY, "inventario");
        iventory.setAlias(new String[]{"inv", "i", "I"});
        getCommandList().add(iventory);
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getCommandList().add(sud);
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getCommandList().add(est);
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getCommandList().add(ovest);
        Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit"});
        getCommandList().add(end);
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getCommandList().add(look);
        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi"});
        getCommandList().add(pickup);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{});
        getCommandList().add(open);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi", "attiva"});
        getCommandList().add(push);
        Command talk = new Command(CommandType.TALK_TO, "parla");
        talk.setAlias(new String[]{"parla", "comunica", "dici", "ciao"});
        getCommandList().add(talk);

        //creo le stanze
        Room salotto = new Room(0, "Salotto", "Questo è il salotto/ingresso");
        Room cucina = new Room(1, "Cucina", "Questa è la cucina");
        Room corridoio = new Room(2, "Corridoio", "Questo è il corridoio");
        Room cameretta = new Room(3, "Cameretta", "Questa è la tua stanza");
        Room camera2 = new Room(4, "Cameretta", "Questa è la camera di tua sorella");
        Room bagno = new Room(5, "Bagno", "Questo è il bagno");
        Room cameragenitori = new Room(5, "Camera da letto", "Questa è la camera dei tuoi genitori");

        //mappo
        salotto.setNorth(cucina);
        salotto.setWest(corridoio);
        corridoio.setEast(camera2);
        corridoio.setWest(cameretta);
        corridoio.setSouth(salotto);
        corridoio.setNorth(cameragenitori);
        camera2.setSouth(corridoio);
        cameretta.setSouth(corridoio);
        cameragenitori.setSouth(corridoio);
        cameragenitori.setWest(bagno);
        bagno.setEast(cameragenitori);

        setCurrentRoom(cameretta);
        //look around
        cameretta.setLook("Sempre disordinata");

        //Creo oggetti
        StdObject computer = new StdObject(1, "Computer", "Il tuo pc, mamma mia quante ne ha passate");
        computer.setArticles(new String[]{"il", "un"});
        computer.setTakeable(true);
        cameretta.addObject(computer);
        StdObject chitarra = new StdObject(2, "Chitarra", "La tua chitarra compagna di vita");
        chitarra.setArticles(new String[]{"la"});
        cameretta.addObject(chitarra);
        StdObject Mac = new StdObject(3, "Mac", "Il Mac di papà , se lo tocchi si arrabbia");

        StdObject cuffie = new StdObject(4,"Cuffie","Delle cuffie, potrebbero servire a qualcosa...");
        cuffie.setArticles(new String[]{"le","quelle"});
        salotto.addObject(cuffie);

        StdObject patatine = new StdObject(4, "patatine", "Mh buone");
        patatine.setArticles(new String[]{"le", "delle"});
        StdObject nutella = new StdObject(5, "nutella", "Squisita");
        nutella.setArticles(new String[]{"la", "un po'", "della"});

        ObjectContainer mensa = new ObjectContainer(6, "Mensa", "Qui ci sono molte cose buone");
        mensa.setTakeable(false);
        mensa.setOpen(true);
        mensa.setLocked(false);
        mensa.addObject(patatine);
        mensa.addObject(nutella);

        //Creo NPC
        NPC mamma = new NPC(0, "mamma", new String[]{"ciao enrico"});
        mamma.setPrepositions(new String[]{"a", "con","alla"});
        cameretta.addCharacter(mamma);

        NPC fratello = new NPC(1,"fratello", new String[]{"Mi spiace, ma per giocare a fortnite ho bisogno del volume altissimo","Adesso con le cuffie non ti disturbo più"});
        fratello.setPrepositions(new String[]{"con","al"});
        salotto.addCharacter(fratello);

        MainCharacter me = new MainCharacter("Enrico", 10);
        setMainCharacter(me);

        //creo gli eventi
        Event fratelloFastidioso = new Event(fratello,Arrays.asList(new StdObject[]{cuffie}),"Come al solito c'è tuo fratello che grida mentre gioca alla Switch impedendoti di studiare tranquillamente, potresti provare a colpirlo in testa oppure qualcos'altro...");
        EventHandler fratelloHandler = new BrotherEventHandler(fratelloFastidioso);
        salotto.setEventHandler(fratelloHandler);
    }

    @Override
    public void actionHandle(PhraseReduction action) {

    }
}
