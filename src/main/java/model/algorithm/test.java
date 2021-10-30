package model.algorithm;

import controller.*;
import model.pieces.BishopPiece;
import model.pieces.ChessPiece;
import model.pieces.KingPiece;

public class test {
    public static void main(String[] args) {
        GameRunner gameRunner = new GameRunner();
        Board board = new Board(gameRunner);
        GraphicsConnector graphicsConnector = new GraphicsConnector(gameRunner);
        board.setGraphicsConnector(graphicsConnector);

        KingPiece king1 = new KingPiece(true, 2,3);
        KingPiece king2 = new KingPiece(false, 5,6);
        BishopPiece bishop1 = new BishopPiece(true, 3,4);
        BishopPiece bishop2 = new BishopPiece(false, 6,1);

        BoardUpdater.addPiece(board, king1);
        BoardUpdater.addPiece(board, king2);
        BoardUpdater.addPiece(board, bishop1);
        BoardUpdater.addPiece(board, bishop2);
        board.setWhiteMove(true);
        Dice.rollTheDice(board);
        printBoard(board.getBoardModel(), board);

        boolean maxIsWhite = board.getWhiteMove();
        ChessTreeNode root = new ChessTreeNode(board, 0, null, 1, 1, 0,0,0,0);
        AiTree aiTree = new AiTree();
        aiTree.createChildren(root, false, maxIsWhite);



        for(TreeNode node: root.getChildren()){
            ChessTreeNode subNode = (ChessTreeNode) node;
            aiTree.createChildren(subNode, true, maxIsWhite);
        }

        Expectiminimax expectiminimax = new Expectiminimax();
        double res = expectiminimax.expectiminimax(root, 10);

        for(int i = 0; i<root.getChildren().size(); i++){
            ChessTreeNode child = (ChessTreeNode) root.getChildren().get(i);
            printBoard(child.getBoard().getBoardModel(), child.getBoard());
            System.out.println("board value: "+child.getValue());
        }

        System.out.println(board.getMovablePiece());
        System.out.println(res);
    }

    public static void printBoard(ChessPiece[][] boardModel, Board board) {
        System.out.println("--- Board State ---\n");
        for(int i = 0; i < boardModel[0].length; i++) {
            for (int j = 0; j < boardModel.length; j++) {
                System.out.print("[ " + board.getCharOffField(j,i) + " ] ");
                // System.out.print("[ " + j + " " + i + " ] ");
            }
            System.out.println();
        }
    }
}