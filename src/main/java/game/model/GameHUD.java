package game.model;

public class GameHUD implements Observer {

    private final Arena arena;

    private int gold;
    private int lives;
    private int wave;
    private String currentMessage = "";
    private int messageTimer = 0;

    public int getGold() {
        return gold;
    }

    public int getLives() {
        return lives;
    }

    public int getWave() {
        return wave;
    }

    public int getMessageTimer() {
        return messageTimer;
    }

    public String getCurrentMessage() {
        return currentMessage;
    }

    public GameHUD(Arena arena) {
        this.arena = arena;
        this.arena.attach(this);
        update();
    }

    @Override
    public void update() {
        gold = arena.getGold();
        lives = arena.getLives();
        wave = arena.getWave();
    }

    public void showMessage(String s){
        this.currentMessage = s;
        this.messageTimer = 50;
    }

    public void tickMessage() {
        if (messageTimer > 0) messageTimer--;
    }
}