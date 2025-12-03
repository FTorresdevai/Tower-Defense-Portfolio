package game.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;
import game.audio.SoundManager;
import game.model.factories.TowerFactory;
import game.states.PlayState;

public class ShopStateController {
    private final int targetX;
    private final int targetY;


    public ShopStateController(int x, int y) {
        this.targetX = x;
        this.targetY = y;
    }

    public void handleInput(Game context, KeyStroke input) throws Exception {
        if (input == null) return;

        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '1') {
            buyBasicTower(context);
        }
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '2') {
            buyRapidTower(context);
        }
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '3') {
            buySniperTower(context);
        }
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '4') {
            buyBombTower(context);
        }
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '5') {
            buyFrostTower(context);
        }
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '6') {
            buyTeslaTower(context);
        }

        if (input.getKeyType() == KeyType.Escape) {
            context.setState(new PlayState());
        }
    }

    public void update(Game context) throws Exception {
    }

    private void buyBasicTower(Game context) {
        int cost = 50;

        if (context.getArena().getGold() >= cost) {
            SoundManager.getInstance().play("sfx_bought");
            context.getArena().addTower(TowerFactory.createBasicTower(targetX, targetY));
            context.getArena().removeGold(cost);
        } else {
            context.getHUD().showMessage("Not enough gold!");
        }
        context.setState(new PlayState());
    }
    private void buyRapidTower(Game context) {
        int cost = 65;
        if (context.getArena().getGold() >= cost) {
            SoundManager.getInstance().play("sfx_bought");
            context.getArena().addTower(TowerFactory.createRapidTower(targetX, targetY));
            context.getArena().removeGold(cost);
        }
        else{
            context.getHUD().showMessage("Not enough gold!");
        }
        context.setState(new PlayState());
    }
    private void buySniperTower(Game context) {
        int cost = 120;
        if (context.getArena().getGold() >= cost) {
            SoundManager.getInstance().play("sfx_bought");
            context.getArena().addTower(TowerFactory.createSniperTower(targetX, targetY));
            context.getArena().removeGold(cost);
        }
        else{
            context.getHUD().showMessage("Not enough gold!");
        }
        context.setState(new PlayState());
    }
    private void buyBombTower(Game context) {
        int cost = 150;
        if (context.getArena().getGold() >= cost) {
            SoundManager.getInstance().play("sfx_bought");
            context.getArena().addTower(TowerFactory.createBombTower(targetX, targetY));
            context.getArena().removeGold(cost);
        }
        else{
            context.getHUD().showMessage("Not enough gold!");
        }
        context.setState(new PlayState());
    }
    private void buyFrostTower(Game context) {
        int cost = 80;
        if (context.getArena().getGold() >= cost) {
            SoundManager.getInstance().play("sfx_bought");
            context.getArena().addTower(TowerFactory.createFrostTower(targetX, targetY));
            context.getArena().removeGold(cost);
        }
        else{
            context.getHUD().showMessage("Not enough gold!");
        }
        context.setState(new PlayState());
    }
    private void buyTeslaTower(Game context) {
        int cost = 180;
        if (context.getArena().getGold() >= cost) {
            SoundManager.getInstance().play("sfx_bought");
            context.getArena().addTower(TowerFactory.createTeslaTower(targetX, targetY));
            context.getArena().removeGold(cost);
        }
        else{
            context.getHUD().showMessage("Not enough gold!");
        }
        context.setState(new PlayState());
    }


}
