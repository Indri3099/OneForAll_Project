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

    public void startEvent(){
        event.setStarted(true);
    }

    public abstract void completeEvent(GenericGame game, PrintStream out);
}
