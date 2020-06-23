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
import main.TTSActionHandler;
import parser.PhraseReduction;

import java.sql.Time;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TryToStudy extends GenericGame {

    public TryToStudy() {
        setName("Try To Study");
    }

    @Override
    public void init() {
        POINTGOAL = 100;

        setTime(new Time(1000*60*15));
        getTime().setHours(0);

        setIntro("Buongiorno bello! Sai che dovresti proprio studiare? Tra " + getTime().toString().substring(3) + " hai l'esame!");
        setEnding("Bene, sembra che abbiamo finito. Speriamo in un bel 30L");
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
        end.setAlias(new String[]{"end", "fine", "esci", "exit", "stop"});
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
        Command give = new Command(CommandType.GIVE, "dare");
        give.setAlias(new String[]{"dai","passa"});
        getCommandList().add(give);

        //creo le stanze
        Room salotto = new Room(0, "Salotto", "Questo è il salotto");
        Room cucina = new Room(1, "Cucina", "Il tempio sacro della mamma dove... avvengono magie!");
        Room corridoio = new Room(2, "Corridoio", "Classico, serve solo come collegamento alle varie stanze");
        Room cameretta = new Room(3, "Cameretta", "Ah la tua stanzetta... probabilmente hai passato un buon 60% della tua vita qui");
        Room camera2 = new Room(4, "Cameretta", "La camera di tua sorella");
        Room bagno = new Room(5, "Bagno", "Il bagno... proprio ora ti viene di farla?");
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
        cucina.setSouth(salotto);

        setCurrentRoom(cameretta);
        //look around
        cameretta.setLook("Sempre disordinata , qui ci sono i tuoi passatempi : il computer, la chitarra e la playstation");

        //Creo oggetti
        StdObject computer = new StdObject(1, "Computer", "Il tuo pc, mamma mia quante ne ha passate");
        computer.setTakeable(false);
        cameretta.addObject(computer);

        StdObject chitarra = new StdObject(2, "Chitarra", "Perché non ci fai sentire qualcosa?");
        cameretta.addObject(chitarra);

        StdObject mac = new StdObject(3, "Mac", "Il Mac di papà , se lo tocchi si arrabbia");
        mac.setTakeable(false);
        camera2.addObject(mac);

        //cuffie nello zaino da dare a fratello
        ObjectContainer zaino = new ObjectContainer(4,"Zaino", "Il tuo zaino per l'univerisità, c'è un paio di cuffie all'interno e una penna");
        zaino.setOpen(true);
        corridoio.addObject(zaino);

        StdObject cuffie = new StdObject(4,"Cuffie","Delle cuffie, potrebbero servire a qualcosa...");
        zaino.addObject(cuffie);
        //----------------------------------------------------


        StdObject patatine = new StdObject(4, "patatine", "Mh buone");
        StdObject nutella = new StdObject(5, "nutella", "Squisita");

        ObjectContainer mensa = new ObjectContainer(6, "Mensa", "Qui ci sono molte cose buone");
        mensa.setTakeable(false);
        mensa.setOpen(true);
        mensa.setLocked(false);
        mensa.addObject(patatine);
        mensa.addObject(nutella);

        //Creo NPC
        NPC mamma = new NPC(0, "mamma", Arrays.asList(new String[]{"ciao enrico"}));
        cameretta.addCharacter(mamma);

        NPC fratello = new NPC(1,"fratello", Arrays.asList(new String[]{"Mi spiace, ma per giocare a fortnite ho bisogno del volume altissimo","Adesso con le cuffie non ti disturbo più"}));
        fratello.setAccepted(Arrays.asList(new StdObject[]{cuffie}));
        salotto.addCharacter(fratello);

        MainCharacter me = new MainCharacter("Enrico", 10);
        setMainCharacter(me);

        //creo gli eventi
        Event fratelloFastidioso = new Event(fratello,Arrays.asList(new StdObject[]{cuffie}),"Come al solito c'è tuo fratello che grida mentre gioca alla Switch impedendoti di studiare tranquillamente, potresti provare a colpirlo in testa oppure qualcos'altro...",20);
        EventHandler fratelloHandler = new BrotherEventHandler(fratelloFastidioso);
        salotto.setEventHandler(fratelloHandler);
    }

    @Override
    public void actionHandle(PhraseReduction action) {

    }
}
