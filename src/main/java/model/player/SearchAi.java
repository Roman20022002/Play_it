package model.player;

import controller.Board;
import controller.BoardUpdater;
import javafx.application.Platform;
import javafx.concurrent.Task;
import model.algorithm.AiTree;
import model.algorithm.ChessTreeNode;
import model.algorithm.Expectiminimax;
import model.algorithm.TreeNode;
import model.pieces.ChessPiece;

import java.util.ArrayList;
import java.util.Random;

public class SearchAi extends Player {
    private AiTree aiTree = new AiTree();
    private Expectiminimax expectiminimax = new Expectiminimax();
    private ChessTreeNode maxima;
    private Board board;
    private static final int ply = 3;

    public SearchAi(Board board) {
        this.board = board;
    }

    public void launch(Board board){
        System.gc();
        new Thread(() -> {
            try{
                Thread.sleep(50);
                ruleBasedAgent(board);
                ChessTreeNode move = getMaxima();
                if(move.isDoPromotion()){
                    board.storeMove();
                    BoardUpdater.runPromotion(board, move.getBoard(), move.getxFrom(), move.getyFrom(), move.getxTo(), move.getyTo());
                    if(Board.GUI_ON){
                        Platform.runLater(
                                new Thread(board::launchGuiUpdate)
                        );
                    }
                }
                else{
//                    if(!Board.GUI_ON) printBoard(board.getBoardModel(), board);
                    BoardUpdater.movePiece(board, move.getxFrom(), move.getyFrom(), move.getxTo(), move.getyTo());
                    if(Board.GUI_ON){
                        Platform.runLater(
                                new Thread(board::launchGuiUpdate)
                        );
                    }
                }
            }
            catch(Exception e){
                System.err.println("Piece might already have been moved due to glitch in the threading");
                e.printStackTrace();
            }
        }).start();

    }

    public void ruleBasedAgent(Board board) {
//        System.out.println(board.getWhiteMove());
        Board copy = board.clone();
        boolean maxIsWhite = board.getWhiteMove();
        ChessTreeNode root = new ChessTreeNode(copy, 0, null, 1, 1, 0, 0, 0, 0, maxIsWhite);
        double res = expectiminimax.expectiminimax(root, (ply*2)-1);
//        System.out.println(res);
        double maxValue = Double.MIN_VALUE;
        ArrayList<ChessTreeNode> highestNodes = new ArrayList<>();
        ChessTreeNode maxNode = (ChessTreeNode) root.getChildren().get(0);
        highestNodes.add(maxNode);
        for (TreeNode child : root.getChildren()) {
            ChessTreeNode subChild = (ChessTreeNode) child;
            if (subChild.getValue() >= maxValue) {
                if (subChild.getValue() == maxValue) {
                    highestNodes.add(subChild);
                    continue;
                }
                highestNodes.clear();
                highestNodes.add(subChild);
                maxValue = subChild.getValue();
            }
        }
        Random rand = new Random();
//        System.out.println(highestNodes.size());
        maxNode = highestNodes.get(rand.nextInt(highestNodes.size()));

//        System.out.println("move from: x=" + maxNode.getxFrom() + " y=" + maxNode.getyFrom() + " to: x=" + maxNode.getxTo() + " y=" + maxNode.getyTo());
//        printBoard(board.getBoardModel(), board);
        maxima = maxNode;
    }

    public ChessTreeNode getMaxima() {
        return maxima;
    }

    public int getPieceType(char pieceType){
        switch(pieceType){
            case 'n','N':
                return 2;
            case 'b','B':
                return 3;
            case 'r','R':
                return 4;
            case 'q','Q':
                return 5;
        }
        return 0;
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