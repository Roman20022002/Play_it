package gui.SceneLayouts;

import gui.ChessGUI;
import gui.Menus.DisplayMenu;
import gui.Menus.HighlightMenu;
import gui.Menus.TurnMenu;
import gui.PlayerSelection.PlayerSelection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
/**
 * a class for designing the start scene which contains
 * 2 menus for players choices and 3 setting menus for size, highlight and turns
 */
public class StartScreen extends BorderPane {
    private ChessGUI chessGUI;
    private int playerOne = 1;
    private int playerTwo = 1;
    private PlayerSelection playerSelection;

    /**
     * @param chessGUI
     */
    public StartScreen(ChessGUI chessGUI) {
        this.chessGUI = chessGUI;
        setStyle("-fx-background-color: #336699;");

        this.playerSelection = new PlayerSelection(this);

        MenuBar menuBar = new MenuBar();
        DisplayMenu displayMenu = new DisplayMenu(chessGUI);
        HighlightMenu highlightMenu = new HighlightMenu();
        TurnMenu turnMenu = new TurnMenu();
        menuBar.getMenus().addAll(displayMenu, highlightMenu, turnMenu);

        setTop(menuBar);

        setCenter(playerSelection);
    }

    /**
     * @param playerNumber
     * @param player
     */
    public void setPlayer(int playerNumber, int player){
        if(playerNumber==1){
            this.playerOne = player;
        }
        else{
            this.playerTwo = player;
        }
    }

    /**
     *
     */
    public void startGame() {
        chessGUI.startGame(playerOne,playerTwo);
    }

    /**
     * 
     */
    public void updateGraphics(){
        setMinSize(chessGUI.getWidth(),chessGUI.getHeight());
        setMaxSize(chessGUI.getWidth(),chessGUI.getHeight());
        playerSelection.updateGraphics(chessGUI.getWidth(), chessGUI.getHeight());
    }
}
