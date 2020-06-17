package events;

import entities.objects.StdObject;
import exceptions.EmptyInvException;
import games.GenericGame;
import parser.PhraseReduction;

public class InventoryEvent implements Event{

    private StringBuilder inventoryList = new StringBuilder("Ecco cosa c'è nel tuo inventario:\n");

    @Override
    public void handleEvent(PhraseReduction action, GenericGame game) throws Exception {
        if(game.getMainCharacter().getInventory().isEmpty()){
            throw new EmptyInvException();
        }
        else{
            for(StdObject object : game.getMainCharacter().getInventory()){
                inventoryList.append(" - " + object + "\n");
            }
        }
    }

    public String getInventoryList() {
        return inventoryList.toString();
    }
}
