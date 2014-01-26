package connect4;

public abstract class Player {
    protected char token;
    protected Board board;

    public Player(char token, Board board) {
        this.token = token;
        this.board = board;
    }

    public char token() {
        return token;
    }

    protected abstract int selectMovement();
}