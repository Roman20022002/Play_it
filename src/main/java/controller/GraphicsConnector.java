package controller;

import javafx.scene.image.Image;
import model.Board;

import java.util.ArrayList;

public class GraphicsConnector {
    public GraphicsConnector(GameRunner gr) {
        this.gr = gr;
        board = gr.getBoard();
    }


    private final GameRunner gr;
    private Board board;

    private final char EMPTY_FIELD_CHAR = '-';
    /**
     * I want this method to return me an arraylist (can be another data structure)
     * of the 1 dimensional coordinates of all the fields to which the piece that is
     * located on the provided x and y coordinate can move to.
     *
     * @param x x coordinate of a piece
     * @param y y coordinate of a piece
     * @return an arraylist that contains all the 1 dimensional coordinates of the spots a piece can move to.
     */
    public ArrayList<Integer> getMoveAbleSpots(int x, int y){
        return new ArrayList<>();
    }

    /**
     * I want this to be a method that receives an int for the players where:
     * 0 = human
     * 1 = AI #1
     * etc.
     *
     * @param player1 the player with the white pieces
     * @param player2 the player with the black pieces
     */
    public void setPlayers(int player1, int player2){

    }

    /**
     * I want this to be a method that receives the initial and target position of a piece so that
     * it can be moved like that in the back end of the game this way the front end does not have
     * to deal with the actual moving of the pieces and only the displaying.
     *
     * @param initialX the initial x coordinate of the piece that is moved
     * @param initialY the initial y coordinate of the piece that is moved
     * @param finalX the final x coordinate of the piece that is moved
     * @param finalY the final y coordinate of the piece that is moved
     */
    public void doMove(int initialX, int initialY, int finalX, int finalY){

    }


    /**
     * I want this method to return the url of the image of the piece located at that spot
     * so say it is a white king, you return "gui/white_king.png".
     * this String can easily automatically be build by using string concatenation.
     * e.g.: String URL = "gui/"+color+"_"+pieceName+".png";
     * where color would be "white" or "black".
     * and pieceName "king", "queen" etc.
     *
     * @param x x coordinate of the piece
     * @param y y coordinate of the piece
     * @return the URL of the image. return null if the spot is empty
     */
    public String getImage(int x, int y){
        char field = board.getCharOfField(x,y);
        switch(field){
            case ""
        }

    }

    Image blackBishop = new Image("gui/black_bishop.png");
    Image blackKing = new Image("gui/black_king.png");
    Image blackKnight = new Image("gui/black_knight.png");
    Image blackPawn = new Image("gui/black_pawn.png");
    Image blackQueen = new Image("gui/black_queen.png");
    Image blackRook = new Image("gui/black_rook.png");

    Image whiteBishop = new Image("gui/white_bishop.png");
    Image whiteKing = new Image("gui/white_king.png");
    Image whiteKnight = new Image("gui/white_knight.png");
    Image whitePawn = new Image("gui/white_pawn.png");
    Image whiteQueen = new Image("gui/white_queen.png");
    Image whiteRook = new Image("gui/white_rook.png");

    /**
     * I want this method to check for me if the move attempted is legal.
     * the piece will start in the position (initialX, initialY) with this the piece
     * can be identified. Then it tries to move to (finalX, finalY).
     *
     * @param initialX initial x coordinate of piece
     * @param initialY initial y coordinate of piece
     * @param finalX final x coordinate of piece
     * @param finalY final y coordinate of piece
     * @return returns whether the move that is attempted is legal.
     */
    public boolean canMove(int initialX, int initialY, int finalX, int finalY){
        return true;
    }


    /**
     * checks whether there is a piece locate on the field with the provided coordinates.
     *
     * @param x x coordinate of a field
     * @param y y coordinate of a field
     * @return whether there is a piece on that field
     */
    public boolean hasPiece(int x, int y){
        char field = board.getCharOfField(x,y);

        return Character.compare(field, EMPTY_FIELD_CHAR) != 0;
    }
}

