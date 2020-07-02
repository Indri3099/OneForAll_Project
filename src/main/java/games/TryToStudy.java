package games;

import entities.Room;
import entities.character.Character;
import entities.character.NPC;
import entities.command.Command;
import entities.command.CommandType;
import entities.objects.ObjectContainer;
import entities.objects.StdObject;
import events.*;
import exceptions.*;
import parser.PhraseReduction;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.sql.Time;
import java.util.Arrays;

public class TryToStudy extends GenericGame {

    private class NameConstants {
        public static final String
                COMPUTER = "computer",
                CHITARRA = "chitarra",
                ZAINO = "zaino",
                CUFFIE = "cuffie",
                PENNA = "penna",
                DISPENSA = "dispensa",
                PASTA = "pasta",
                POMODORO = "pomodoro",
                NUTELLA = "nutella",
                CHIAVE = "chiave",
                MAC = "mac",
                APPUNTI = "appunti",
                CAMERETTA = "cameretta",
                CUCINA = "cucina",
                STANZETTA = "stanzetta",
                CAMERALETTO = "camera da letto",
                BAGNO = "bagno",
                SALONE = "salone",
                CORRIDOIO = "corridoio",
                MAMMA = "mamma",
                FRATELLO = "fratello",
                ME = "Enrico",
                BUTTON = "pulsante";
    }

    public TryToStudy() {
        setName("Try To Study");
        setDefaultPath("./src/main/resources/savings/TryToStudyDefault.dat");
    }

    @Override
    public void init() {
        setPointGoal(100);

        setTotalTime(new Time(15 * 60 * 1000));
        getTotalTime().setHours(0);
        setActualTime(new Time(getTotalTime().getTime()));
        getActualTime().setHours(0);

        setIntro("Buongiorno bello! Sai che dovresti proprio studiare? Tra " + getActualTime().toString().substring(3) + " hai l'esame!");
        setWin("Bene, sembra che abbiamo finito. Speriamo in un bel 30L");
        setLose("Peccato, non hai fatto in tempo a raggiungere il tuo obbiettivo :(");
        setDescription("Salve! Andiamo subito al sodo.\nIl gioco è ambientato nella casa di un normale studente che,\ndeve cercare di studiare tra il casino della famiglia e le varie distrazioni.\n" +
                "Qui entri in gioco tu! Sì dico proprio a te.\nLo scopo del gioco è arrivare a 100 punti entro 5 minuti.\n" +
                "Se ci riuscirai passerai questo dannato esame, altrimenti...beh altrimenti muoviti a studiare fallito!\n" +
                "Al termine del gioco potrai salvare il tuo punteggio,\nsentiti libero inoltre di salvare quando vuoi in modo da riprendere quando non hai nulla da fare\n" +
                "Beh che aspetti!? Corri a studiare , il tempo scorre!");
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
        pickup.setAlias(new String[]{"prendi","solleva"});
        getCommandList().add(pickup);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{});
        getCommandList().add(open);
        Command close = new Command(CommandType.CLOSE, "chiudi");
        close.setAlias(new String[]{});
        getCommandList().add(close);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi", "schiaccia", "pigia"});
        getCommandList().add(push);
        Command talk = new Command(CommandType.TALK_TO, "parla");
        talk.setAlias(new String[]{"parla", "comunica", "dici", "ciao"});
        getCommandList().add(talk);
        Command give = new Command(CommandType.GIVE, "dare");
        give.setAlias(new String[]{"dai", "passa"});
        getCommandList().add(give);
        Command drop = new Command(CommandType.DROP, "lascia");
        drop.setAlias(new String[]{"lascia", "sbarazzati", "abbandona", "butta", "getta"});
        getCommandList().add(drop);
        Command suona = new Command(CommandType.PLAY, "suona");
        getCommandList().add(suona);

        //creo le stanze
        Room salotto = new Room(0, NameConstants.SALONE, "Questo è il salotto");
        Room cucina = new Room(1, NameConstants.CUCINA, "La cucina, mamma è sempre qui nei dintorni");
        Room corridoio = new Room(2, NameConstants.CORRIDOIO, "Classico, serve solo come collegamento alle varie stanze");
        Room cameretta = new Room(3, NameConstants.CAMERETTA, "Ah la tua stanzetta... probabilmente hai passato un buon 60% della tua vita qui");
        Room camera2 = new Room(4, NameConstants.STANZETTA, "La camera di tua sorella");
        Room bagno = new Room(5, NameConstants.BAGNO, "Il bagno... proprio ora ti viene di farla?");
        Room cameragenitori = new Room(5, NameConstants.CAMERALETTO, "Questa è la camera dei tuoi genitori");

        //look around
        cameretta.setLook("Sempre disordinata , qui ci sono i tuoi passatempi : il computer e la chitarra");
        salotto.setLook("Non c'è nulla di che, a parte il divano e la tv monopolizzati da tuo fratello");
        cucina.setLook("Vedo mamma sempre indaffarata, lì c'è una dispensa con roba da mangiare");
        camera2.setLook("Qui c'è il pannello con 4 pulsanti , chissà che succede se li premi\nSembra anche esserci una chiave e il mac di tuo padre");
        cameragenitori.setLook("Nulla di particolare, sulla scrivania sembra esserci un quaderno di appunti");
        corridoio.setLook("C'è uno zaino aperto, vedi un po' cosa c'è dentro");
        //mappo
        salotto.setNorth(cucina);
        salotto.setWest(corridoio);
        salotto.setSouth(bagno);
        corridoio.setEast(camera2);
        corridoio.setWest(cameretta);
        corridoio.setSouth(salotto);
        corridoio.setNorth(cameragenitori);
        camera2.setSouth(corridoio);
        cameretta.setSouth(corridoio);
        cameragenitori.setSouth(corridoio);
        bagno.setNorth(salotto);
        cucina.setSouth(salotto);

        setCurrentRoom(cameretta);
        cameragenitori.setLocked(true);


        //Creo oggetti
        StdObject computer = new StdObject(1, NameConstants.COMPUTER, "Il tuo pc, mamma mia quante ne ha passate");
        computer.setTakeable(false);
        cameretta.addObject(computer);

        StdObject chitarra = new StdObject(2, NameConstants.CHITARRA, "Perché non ci fai sentire qualcosa?");
        chitarra.setSound(new File("./resources/audio/chitarra.wav"));
        cameretta.addObject(chitarra);

        StdObject mac = new StdObject(3, NameConstants.MAC, "Il Mac di papà , se lo tocchi si arrabbia");
        mac.setTakeable(false);
        camera2.addObject(mac);

        //cuffie nello zaino da dare a fratello
        ObjectContainer zaino = new ObjectContainer(4, NameConstants.ZAINO, "Il tuo zaino per l'univerisità, c'è un paio di cuffie all'interno e una penna");
        zaino.setAlias(new String[]{"zainetto"});
        zaino.setOpen(true);
        zaino.setLocked(false);
        corridoio.addObject(zaino);

        StdObject cuffie = new StdObject(5, NameConstants.CUFFIE, "Delle cuffie, potrebbero servire a qualcosa...");
        cuffie.setAlias(new String[]{"cuffiette", "auricolari"});
        zaino.addObject(cuffie);
        //----------------------------------------------------
        //pasta e pomodoro da dare a mamma
        StdObject pasta = new StdObject(6, NameConstants.PASTA, "Siamo italiani, senza pasta non sappiamo sopravvivere");
        StdObject pomodoro = new StdObject(7, NameConstants.POMODORO, "Certo non puoi mangiare solo pasta in bianco");
        pomodoro.setAlias(new String[]{"pomodori"});
        StdObject nutella = new StdObject(8, NameConstants.NUTELLA, "Buona, ma forse adesso ci vuole qualcos'altro");

        ObjectContainer dispensa = new ObjectContainer(9, NameConstants.DISPENSA, "Qui ci sono molte cose buone, aprilo, forse trovi quel che cerchi");
        dispensa.setAlias(new String[]{"mensa", "cassetto"});
        dispensa.setTakeable(false);
        dispensa.setOpen(false);
        dispensa.setLocked(false);
        dispensa.addObject(pasta);
        dispensa.addObject(pomodoro);
        dispensa.addObject(nutella);
        cucina.addObject(dispensa);

        StdObject key = new StdObject(10, NameConstants.CHIAVE, "Una chiave, potrebbe servire ad aprire qualche porta...");
        key.setAlias(new String[]{"chiavi"});
        camera2.addObject(key);

        //Creo NPC
        NPC mamma = new NPC(1, NameConstants.MAMMA, "Se vuoi passare l'esame devi mangiare qualcosa, portami della pasta e del pomodoro che te li cucino!");
        mamma.setAccepted(Arrays.asList(new StdObject[]{pasta, pomodoro}));
        cucina.addCharacter(mamma);

        NPC fratello = new NPC(2, NameConstants.FRATELLO, "Mi spiace, ma per giocare a fortnite ho bisogno del volume altissimo");
        fratello.setAccepted(Arrays.asList(new StdObject[]{cuffie}));
        salotto.addCharacter(fratello);

        Character me = new Character(0,NameConstants.ME);
        setMainCharacter(me);

        //creo gli eventi
        Event fratelloFastidioso = new Event(fratello, Arrays.asList(new StdObject[]{cuffie}), "Come al solito c'è tuo fratello che grida mentre gioca alla Switch impedendoti di studiare tranquillamente, potresti provare a colpirlo in testa oppure qualcos'altro...", 20);
        fratelloFastidioso.setEndPhrase("E va bene metto le cuffie e non do più fastidio");
        EventHandler fratelloHandler = new BrotherEventHandler(fratelloFastidioso);
        salotto.setEventHandler(fratelloHandler);

        Event ciboMamma = new Event(mamma, Arrays.asList(new StdObject[]{pasta, pomodoro}), "C'è la mamma, sembra voglia dirti qualcosa", 20);
        ciboMamma.setEndPhrase("Perfetto! Vedrai come sarai più energico dopo un piatto di pasta!");
        EventHandler mammaHandler = new MomEventHandler(ciboMamma);
        cucina.setEventHandler(mammaHandler);

        Event portaBloccata = new Event(null, Arrays.asList(new StdObject[]{key}), "La porta della camera dei tuoi genitori è chiusa a chiave, forse la chiave è nei dintorni", 0);
        portaBloccata.setEndPhrase("Con la chiave raccolta adesso la stanza è accessibile");
        EventHandler doorEvent = new DoorLockedHandler(portaBloccata);
        corridoio.setEventHandler(doorEvent);


        StdObject button1 = new StdObject(11, NameConstants.BUTTON + "1", "4 pulsanti, c'è una etichetta su ognuno : Pulsante1, Pulsante2, Pulsante3, Pulsante4\n Chi ha fatto queste etichette aveva molta fantasia");
        button1.setAlias(new String[]{"pulsante", "pulsanti", "bottoni"});
        button1.setTakeable(false);
        button1.setPushable(true);
        StdObject button2 = new StdObject(12, NameConstants.BUTTON + "2", null);
        button2.setTakeable(false);
        button2.setPushable(true);
        StdObject button3 = new StdObject(13, NameConstants.BUTTON + "3", null);
        button3.setTakeable(false);
        button3.setPushable(true);
        StdObject button4 = new StdObject(14, NameConstants.BUTTON + "4", null);
        button4.setTakeable(false);
        button4.setPushable(true);
        camera2.addObject(button1);
        camera2.addObject(button2);
        camera2.addObject(button3);
        camera2.addObject(button4);
        Event luciSpente = new Event(null, Arrays.asList(new StdObject[]{button4, button2}), "Sembra che sia saltata la luce in tutte le stanze e così non puoi studiare per il tuo esame!\nIn camera di tua sorella troverai dei pulsanti per riattivarla", 40);
        luciSpente.setEndPhrase("Wow , sei stato formidabile a spingere i pulsanti giusti...\n...in realtà avresti anche potuto premerli tutti a caso" + "\nBene sembra che adesso le luci si siano accese tutte!\nStudiare con la luce spenta non sarebbe stato semplice...\n");
        EventHandler lucievent = new BlackOutHandler(luciSpente);
        camera2.setEventHandler(lucievent);

        StdObject appunti = new StdObject(15, NameConstants.APPUNTI, "Ecco gli appunti che cercavi");
        appunti.setAlias(new String[]{"quaderno", "quadernetto"});
        cameragenitori.addObject(appunti);

        Event appuntiEvent = new Event(null, Arrays.asList(new StdObject[]{appunti}), "Qui dovrebbero trovarsi i tuoi appunti, prendili così potrai completare il tuo studio", 20);
        appuntiEvent.setEndPhrase("Finalmente hai trovato i tuoi appunti per l'esame, ora sarai preparatissimo");
        EventHandler app = new AppuntiEventHandler(appuntiEvent);
        cameragenitori.setEventHandler(app);

        StdObject penna = new StdObject(16, NameConstants.PENNA, "Una penna nera");
        zaino.addObject(penna);
    }

    @Override
    public void actionHandler(PhraseReduction action) throws Exception {
        if (action.getCommand().getType() == CommandType.NORD) {
            move(getCurrentRoom().getNorth());
        } else if (action.getCommand().getType() == CommandType.SOUTH) {
            move(getCurrentRoom().getSouth());
        } else if (action.getCommand().getType() == CommandType.EAST) {
            move(getCurrentRoom().getEast());
        } else if (action.getCommand().getType() == CommandType.WEST) {
            move(getCurrentRoom().getWest());
        } else if (action.getCommand().getType() == CommandType.PICK_UP) {
            if (action.getMyObject() != null)
                throw new ObjectOwnedException();
            take(action.getToObject());
        } else if (action.getCommand().getType() == CommandType.INVENTORY) {
            inventory();
        } else if (action.getCommand().getType() == CommandType.LOOK_AT) {
            if (action.getToObject() != null)
                lookAt(action.getToObject());
            else
                lookAt(action.getMyObject());
        } else if (action.getCommand().getType() == CommandType.GIVE) {
            give(action.getMyObject(), (NPC) action.getToCharacter());
        } else if (action.getCommand().getType() == CommandType.OPEN) {
            if (!(action.getMyObject() != null && action.getToObject() != null)) {
                if (action.getMyObject() != null) {
                    open(action.getMyObject());
                } else if (action.getToObject() != null) {
                    open(action.getToObject());
                }
            }
        } else if (action.getCommand().getType() == CommandType.CLOSE) {
            if (!(action.getMyObject() != null && action.getToObject() != null)) {
                if (action.getMyObject() != null) {
                    close(action.getMyObject());
                } else if (action.getToObject() != null) {
                    close(action.getToObject());
                }
            }
        } else if (action.getCommand().getType() == CommandType.DROP) {
            drop(action.getMyObject());
        } else if (action.getCommand().getType() == CommandType.TALK_TO) {
            talkTo((NPC) action.getToCharacter());
        } else if (action.getCommand().getType() == CommandType.PUSH) {
            if (!(action.getMyObject() != null && action.getToObject() != null)) {
                if (action.getMyObject() != null) {
                    push(action.getMyObject());
                } else if (action.getToObject() != null) {
                    push(action.getToObject());
                }
            }
        } else if (action.getCommand().getType() == CommandType.PLAY) {
            if (!(action.getMyObject() != null && action.getToObject() != null)) {
                if (action.getMyObject() != null) {
                    play(action.getMyObject());
                } else if (action.getToObject() != null) {
                    play(action.getToObject());
                }
            }
        } else {
            throw new CommandNotValidException();
        }
    }

    private void move(Room destination) throws Exception {
        if (destination != null && !destination.isLocked()) {
            out.print(destination + " : " + destination.getDescription());
            setCurrentRoom(destination);
            if (destination.getEventHandler() != null && !destination.getEventHandler().getEvent().isStarted() && !destination.getName().toLowerCase().equals(NameConstants.STANZETTA)) {
                destination.getEventHandler().startEvent(out);
            }
        } else if (destination == null) {
            throw new OutOfMapException();
        } else if (destination.isLocked()) {
            throw new LockedException();
        }
    }

    private void inventory() throws Exception {
        StringBuilder strInventory = new StringBuilder("Ecco il tuo inventario: ");
        if (getMainCharacter().getInventory().getList().isEmpty()) {
            throw new EmptyInvException();
        } else {
            for (StdObject object : getMainCharacter().getInventory().getList()) {
                strInventory.append("\n - " + object);
            }
            out.print(strInventory.toString());
        }
    }

    private void lookAt(StdObject toObject) throws Exception {
        String look;
        if (!getCurrentRoom().isVisible()) {
            throw new NoLightException();
        } else {
            if (toObject == null && getCurrentRoom().getLook() != null) {
                look = getCurrentRoom() + " : " + getCurrentRoom().getLook();
                out.print(look);
            } else if (toObject != null && toObject.getDescription() != null) {
                look = toObject + " : " + toObject.getDescription();
                out.print(look);
            } else {
                throw new NoDescriptionException();
            }
        }
    }

    private void take(StdObject toObject) throws Exception {
        if (toObject == null) {
            throw new ObjectNotFoundException();
        } else if (toObject.isTakeable() && toObject.isVisible()) {
            getMainCharacter().getInventory().add(toObject);
            for (StdObject object : getCurrentRoom().getObjects()) {
                if (object instanceof ObjectContainer) {
                    ((ObjectContainer) object).getObjects().remove(toObject);
                }
            }
            getCurrentRoom().getObjects().remove(toObject);
            out.print("Hai raccolto " + toObject);
        } else {
            throw new UnTakeableException();
        }
    }

    private void give(StdObject myObject, NPC toCharacter) throws Exception {
        if (myObject == null || toCharacter == null)
            throw new CommandNotValidException();
        else {
            if (toCharacter.getAccepted().contains(myObject)) {
                out.print("Hai dato " + myObject + " a " + toCharacter);
                toCharacter.getInventory().add(myObject);
                getMainCharacter().getInventory().remove(myObject);
            }
        }
    }

    private void talkTo(NPC toCharacter) {
        if (toCharacter.getSentence() != null) {
            out.print(toCharacter + ": \"" + toCharacter.getSentence() + "\"");
        } else {
            out.print(toCharacter + ": " + "\"non ho nulla da dirti\"");
        }
    }

    private void open(StdObject object) throws Exception {
        if (object instanceof ObjectContainer) {
            if (!((ObjectContainer) object).isOpen() && !((ObjectContainer) object).isLocked()) {
                out.println("Hai aperto : " + object);
                ((ObjectContainer) object).setOpen(true);
                out.print(object + " contiene:");
                for( StdObject obj : ((ObjectContainer) object).getObjects()){
                    out.print(" " + obj);
                    if(obj != ((ObjectContainer) object).getObjects().get(((ObjectContainer) object).getObjects().size() -1)){
                        out.print(",");
                    }
                }
            } else if (((ObjectContainer) object).isLocked()) {
                throw new LockedException();
            } else if (((ObjectContainer) object).isOpen()) {
                out.print(object + " è già aperto");
            }
        } else {
            throw new NotAContainerException();
        }
    }

    private void close(StdObject object) throws Exception {
        if (object instanceof ObjectContainer) {
            if (((ObjectContainer) object).isOpen() && !((ObjectContainer) object).isLocked()) {
                out.print("Hai chiuso : " + object);
                ((ObjectContainer) object).setOpen(false);
            } else if (((ObjectContainer) object).isLocked()) {
                throw new LockedException();
            } else if (!((ObjectContainer) object).isOpen()) {
                out.print(object + " è già chiuso");
            }
        } else {
            throw new NotAContainerException();
        }
    }

    private void drop(StdObject myObject) throws Exception {
        if (myObject != null) {
            getMainCharacter().getInventory().remove(myObject);
            getCurrentRoom().addObject(myObject);
            out.print("Hai lasciato : " + myObject);
        } else {
            throw new CommandNotValidException();
        }
    }

    private void play(StdObject object) throws NoSoundException {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(object.getSound().toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            out.print("Attento alle orecchie!");
        } catch (Exception e) {
            throw new NoSoundException();
        }
    }

    private void push(StdObject object) throws NotPushableException {
        if (object.isPushable() && !object.isPushed()) {
            object.setPushed(true);
            out.print("Hai premuto: " + object);
        } else {
            throw new NotPushableException();
        }
    }
}
