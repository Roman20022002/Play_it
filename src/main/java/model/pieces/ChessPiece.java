package model.pieces;

import model.Board;

/**
 * Abstract class representing a chess piece
 */
public abstract class ChessPiece {

    protected boolean isWhite;
    protected int index_x;
    protected int index_y;
    protected Board currentBoard;

    public ChessPiece() { }

    /**
     * Constructor
     * @param isWhite
     * @param index_x
     * @param index_y
     */
    public ChessPiece(boolean isWhite, int index_x, int index_y, Board board) {

        this.isWhite = isWhite;
        this.index_x = index_x;
        this.index_y = index_y;
        this.currentBoard = board;

    }

    public int getIndex_x() {
        return this.index_x;
    }

    public int getIndex_y() {
        return this.index_y;
    }

    public boolean isWhite() {
        return this.isWhite;
    }

    public void move(int index_x, int index_y) {

        this.currentBoard.setEnPassantAuthorized(-1);
        if( (this.getPieceChar() == 'P') || (this.getPieceChar() == 'p') ) {
            if(Math.abs(this.index_y - index_y) == 2) {

                this.currentBoard.setEnPassantAuthorized(this.index_x);
            }
        }

        // Updates internal position
        this.index_x = index_x;
        this.index_y = index_y;
    }

    /**
     * Checks, if another piece on a different field has the same colour. (Own team)
     * @param index_x
     * @param index_y
     * @return
     */
    public boolean checkForOwnPiece(int index_x, int index_y) {
        if (currentBoard.getPiece(index_x, index_y) == null)
            return false;
        if (currentBoard.getField()[index_x][index_y].isWhite == this.isWhite)
            return true;
        else
            return false;
    }

    /**
     * Checks, if another piece on a different field has a different colour. (Enemy team)
     * @param index_x
     * @param index_y
     * @return
     */
    public boolean checkForEnemyPiece(int index_x, int index_y) {
        if (currentBoard.getPiece(index_x, index_y) == null)
            return false;
        if (currentBoard.getField()[index_x][index_y].isWhite != this.isWhite)
            return true;
        else
            return false;
    }

    private int BOARDSIZE = Board.getBoardSize();
    public boolean withinBounds(int variable, int increment) {
        return variable + increment < BOARDSIZE && variable + increment >= 0;
    }

    public boolean withinBoundsOneVariable(int value){
        return value < BOARDSIZE && value >= 0;
    }

    public boolean isOpenSpot(int x, int y) {
        if(!checkForOwnPiece(x,y)) {
            return true;
        }
        else{
            return false;
        }
    }

    public abstract boolean[][] validMoves();

    public abstract char getPieceChar();

    public boolean isOnOppositeRow(int x,int y) {
        if(isWhite){
            return y == 0;
        }
        else return y == 7;
    }

    public boolean isTurn() {
        if(isWhite) {
            return currentBoard.getWhiteMove();
        }
        else return !currentBoard.getWhiteMove();
    }
}
