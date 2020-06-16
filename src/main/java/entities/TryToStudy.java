package entities;

import entities.command.Command;
import entities.command.CommandType;
import entities.objects.ObjectContainer;
import entities.objects.StdObject;

public class TryToStudy extends GenericGame{

    private final int POINTGOAL = 100;

    private int points = 0;

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
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati","exit"});
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
        push.setAlias(new String[]{"spingi","attiva"});
        getCommandList().add(push);

        //creo le stanze
        Room salotto = new Room(0,"Salotto","Questo è il salotto/ingresso");
        Room cucina = new Room(1,"Cucina","Questa è la cucina");
        Room corridoio = new Room(2,"Corridoio","Questo è il corridoio");
        Room cameretta = new Room(3,"Cameretta","Questa è la tua stanza");
        Room camera2 = new Room(4,"Cameretta","Questa è la camera di tua sorella");
        Room bagno = new Room(5,"Bagno","Questo è il bagno");
        Room cameragenitori = new Room(5,"Camera da letto","Questa è la camera dei tuoi genitori");
        
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
        
        //look around
        cameretta.setLook("Sempre disordinata");

        //Creo oggetti
        StdObject Computer = new StdObject(1,"Computer","Il tuo pc, mamma mia quante ne ha passate");
        StdObject Chitarra = new StdObject(2,"Chitarra","La tua chitarra compagna di vita");
        StdObject Mac = new StdObject(3,"Mac","Il Mac di papà , se lo tocchi si arrabbia");

        StdObject patatine = new StdObject(4,"patatine","Mh buone");
        StdObject nutella = new StdObject(5,"nutella","Squisita");

        ObjectContainer mensa = new ObjectContainer(6,"Mensa","Qui ci sono molte cose buone");
        mensa.setTakeable(false);
        mensa.setOpen(true);
        mensa.setLocked(false);
        mensa.addObject(patatine);
        mensa.addObject(nutella);

    }
}
