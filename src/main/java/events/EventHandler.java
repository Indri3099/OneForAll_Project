package events;

import games.GenericGame;

import java.io.PrintStream;

public abstract class EventHandler {

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

    public void startEvent(PrintStream out){
        event.setStarted(true);
        out.println(event.getDescription());
    }

    public abstract void completeEvent(GenericGame game, PrintStream out);
}
