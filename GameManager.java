public class GameManager {
    public enum GameState {
        MAIN_MENU,
        PLAYING,
        GAME_OVER}
    private GameState currentState;
    private int score;
    private int highScore;
    private Player player;
    private Pipe pipe;
    public GameManager(Player player, Pipe pipe) {
        this.player = player;
        this.pipe = pipe;
        initGame();}
    public void initGame() {
        currentState = GameState.MAIN_MENU;
        score = 0;
        player.reset();
        pipe.reset();}
    public void update() {
        if (currentState != GameState.PLAYING) return;
        player.update();
        pipe.update();
        if (checkCollision()) {
            triggerGameOver();
            return;}
        updateScore();}
    private boolean checkCollision() {
        return player.isOnGround() || pipe.collidesWith(player);}

    private void updateScore() {
        if (pipe.checkAndMarkPassed(player)) {
            score++;}}
    public void startGame() {
        if (currentState == GameState.MAIN_MENU) {
            score = 0;
            player.reset();
            pipe.reset();
            currentState = GameState.PLAYING;}}
    private void triggerGameOver() {
        currentState = GameState.GAME_OVER;
        if (score > highScore) {
            highScore = score;}}
    public void restartGame() {
        if (currentState == GameState.GAME_OVER) {
            score = 0;
            player.reset();
            pipe.reset();
            currentState = GameState.PLAYING;}}
    public void returnToMenu() {
        initGame();}
    public GameState getCurrentState() {
        return currentState;}
    public int getScore() {
        return score;}
    public int getHighScore() {
        return highScore;}
    public boolean isPlaying() {
        return currentState == GameState.PLAYING;}
    public boolean isGameOver() {
        return currentState == GameState.GAME_OVER;}
    public boolean isInMenu() {
        return currentState == GameState.MAIN_MENU;}}
