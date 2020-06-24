package events;

import games.GenericGame;

import java.io.PrintStream;
import java.io.Serializable;

import main.userInterface.Printer;

public abstract class EventHandler implements Serializable {

    Event event;

    public EventHandler(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void startEvent(Printer out){
        event.setStarted(true);
        out.print("\n" + event.getDescription());
    }

    public abstract void completeEvent(GenericGame game, Printer out);
}
