package model.pieces;

import model.Board;

//TODO add promotion

/**
 * Pawn piece
 */
public class PawnPiece extends ChessPiece {

    public PawnPiece(boolean white, Board board, int index_x, int index_y) {
        super(white, index_x, index_y, board);
    }

    public char getPieceChar() {
        if (this.isWhite)
            return 'P';
        else
            return 'p';
    }

    public boolean[][] validMoves() {

        boolean[][] valid_moves = new boolean[Board.getBoardSize()][Board.getBoardSize()];


        if(isTurn()) {
            if(isWhite) {
                //first move --> 2 options if conditions reunited
                if( (withinBounds(index_y,-1)) && (isOpenSpot(index_x,index_y-1)) && !(checkForEnemyPiece(index_x,index_y-1)) ) {
                    if( (index_y==6) && (isOpenSpot(index_x,index_y-2)) && !(checkForEnemyPiece(index_x,index_y-2)) ) {
                        valid_moves[index_x][index_y-1] = true;
                        valid_moves[index_x][index_y-2] = true;
                        setHasValidMove(true);
                    }
                    else {
                        valid_moves[index_x][index_y-1] = true;
                        setHasValidMove(true);
                    }
                }

                if( (withinBounds(index_x,-1)) && (withinBounds(index_y,-1)) && (isOpenSpot(index_x-1,index_y-1)) && (checkForEnemyPiece(index_x-1,index_y-1)) ) {
                    valid_moves[index_x-1][index_y-1] = true;
                    setHasValidMove(true);
                }
                if( (withinBounds(index_x,1)) && (withinBounds(index_y,-1)) && (isOpenSpot(index_x+1,index_y-1)) && (checkForEnemyPiece(index_x+1,index_y-1)) ) {
                    valid_moves[index_x+1][index_y-1] = true;
                    setHasValidMove(true);
                }
            }
            else{
                if( (withinBounds(index_y, 1)) && (isOpenSpot(index_x,index_y+1)) && (!checkForEnemyPiece(index_x,index_y+1)) ) {
                    if( (index_y==1) && (isOpenSpot(index_x,index_y+2)) && (!checkForEnemyPiece(index_x,index_y+2)) ) {
                        valid_moves[index_x][index_y+1] = true;
                        valid_moves[index_x][index_y+2] = true;
                        setHasValidMove(true);
                    }
                    else {
                        valid_moves[index_x][index_y+1] = true;
                        setHasValidMove(true);
                    }
                }
                if( (withinBounds(index_x,-1)) && (withinBounds(index_y,1)) && (isOpenSpot(index_x-1,index_y+1)) && (checkForEnemyPiece(index_x-1,index_y+1)) ) {
                    valid_moves[index_x-1][index_y+1] = true;
                    setHasValidMove(true);
                }
                if( (withinBounds(index_x,1)) && (withinBounds(index_y,1)) && (isOpenSpot(index_x+1,index_y+1)) && (checkForEnemyPiece(index_x+1,index_y+1)) ) {
                    valid_moves[index_x+1][index_y+1] = true;
                    setHasValidMove(true);
                }
            }
        }

        //en passant
        if(this.currentBoard.getEnPassantAuthorized() > -1) {
            if( (this.index_x + 1 == this.currentBoard.getEnPassantAuthorized()) || (this.index_x - 1 == this.currentBoard.getEnPassantAuthorized()) ) {
                if( (this.isWhite) && (this.index_y == 3) ) {
                    valid_moves[this.currentBoard.getEnPassantAuthorized()][2] = true;
                    setHasValidMove(true);
                }
                else if( (!this.isWhite) && (this.index_y == 4) ) {
                    valid_moves[this.currentBoard.getEnPassantAuthorized()][5] = true;
                    setHasValidMove(true);
                }
            }
        }

        if(checkAllFalse(valid_moves)){
            setHasValidMove(false);
        }
        return valid_moves;
    }
}