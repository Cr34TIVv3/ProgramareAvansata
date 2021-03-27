public class Main {
    public static void main(String[] args) {
        Board board = new Board(6);
        board.addToken(new Token(1,3,11));
        board.addToken(new Token(2,5,5));
        board.addToken(new Token(3,1,6));
        board.addToken(new Token(4,4,2));
        board.addToken(new Token(4,6,1));
        board.addToken(new Token(5,2,8));

    }
}
