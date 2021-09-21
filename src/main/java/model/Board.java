package model;

import controller.GameRunner;
import model.pieces.ChessPiece;
import java.util.*;

public class Board {

    private static final int BOARDSIZE = 8;
    private ChessPiece[][] boardModel;
    private BoardUpdater boardUpdater;
    private GameRunner gameRunner;
    private int priseEnPassantAuthorized = -1;
    

    public Board(GameRunner gameRunner) {
        this.gameRunner=gameRunner;
        boardModel = new ChessPiece[8][8];
    }

    public static int getBoardSize() {
        return BOARDSIZE;
    }
    
    private ArrayList<ChessPiece> whitePieces = new ArrayList<ChessPiece>;
    private ArrayList<ChessPiece> blackPieces = new ArrayList<ChessPiece>;
    
    public void addPiece(ChessPiece piece) {
    	if(piece.isWhite() == true) {
    		whitePieces.add(piece);
    	}
    	else {
    		blackPieces.add(piece);
    	}
    }
    
    public ArrayList<ChessPiece> getWhitePieces(){
    	return whitePieces;
    }
    
    public ArrayList<ChessPiece> getBlackPieces(){
    	return blackPieces;
    }

    public ChessPiece[][] getField() {
        return boardModel;
    }

    public ChessPiece getPiece(int index_x, int index_y) {
        return boardModel[index_x][index_y];
    }

    public void printBoard() {
        System.out.println("--- Board State ---\n");
        for (int i = 0; i < boardModel[0].length; i++) {
            for (int j = 0; j < boardModel.length; j++) {
                System.out.print("[ " + getCharOfField(j, i) + " ] ");
                // System.out.print("[ " + j + " " + i + " ] ");
            }
            System.out.println();
        }


    }

    public void setBoardUpdater(BoardUpdater boardUpdater) {
        this.boardUpdater = boardUpdater;
    }

    public BoardUpdater getBoardUpdater() {
        return boardUpdater;
    }

    public char getCharOfField(int x, int y) {
        if (boardModel[x][y] == null) {
            return '-';
        }
        else {
            return boardModel[x][y].getPieceChar();
        }
    }

    public boolean getWhiteMove(){
        return gameRunner.getWhiteMove();
    }

    public void doMove(){
        gameRunner.doMove();
    }

}
