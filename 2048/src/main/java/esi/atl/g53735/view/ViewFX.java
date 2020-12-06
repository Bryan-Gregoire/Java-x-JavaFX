package esi.atl.g53735.view;

import esi.atl.g53735.controller.ControllerFX;
import esi.atl.g53735.model.Board;
import esi.atl.g53735.model.Direction;
import esi.atl.g53735.model.Game;
import esi.atl.g53735.model.LevelStatus;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author g53735
 */
public class ViewFX extends Application implements PropertyChangeListener,
        InterfaceViewFX {

    private ControllerFX controller;

    private final ListViewFX listView;
    private final BoardViewFX containBoard;
    private final scoreFX lblScore;

    public ViewFX() {
        this.containBoard = new BoardViewFX();
        listView = new ListViewFX();
        lblScore = new scoreFX();
    }

    /**
     * Set a controler.
     *
     * @param controller the given controller to set.
     */
    public void setController(ControllerFX controller) {
        this.controller = controller;
    }

    /**
     * Start of the view.
     *
     * @param stage the stage.
     */
    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        HBox containGame = new HBox();

        stage.setTitle("2048");
        Label title = new Label("2048");
        title.setTextFill(Paint.valueOf("#776e65"));
        title.setFont(Font.font("Arial", FontWeight.BOLD, 75));
        title.setPadding(new Insets(10));

        Button again = new Button("Recommencer");
        again.setStyle("-fx-background-color: #8f7a66");
        again.setTextFill(Paint.valueOf("#f9f6f2"));
        again.setFont(Font.font("Clear sans", FontWeight.BOLD, 20));
        again.setPrefSize(250, 30);

        again.addEventHandler(ActionEvent.ACTION,
                new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                controller.startGame();
                listView.addMessageList(" - Partie recommencer");
                root.requestFocus();
            }
        });

        containGame.getChildren().addAll(containBoard, listView);
        containGame.setAlignment(Pos.CENTER);
        containGame.setSpacing(5);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(title, containGame, lblScore, again);
        root.setOnKeyPressed((t) -> {
            keyDirection(t);
        });

        Scene scene = new Scene(root, 1000, 750);
        scene.setCursor(Cursor.HAND);
        stage.setScene(scene);
        stage.show();
        root.requestFocus();
    }

    /**
     * Change property of event.
     *
     * @param evt the event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Game.SCORE)) {
            lblScore.setText("Score : " + evt.getNewValue());
        }
        if (evt.getPropertyName().equals(Game.BOARD_MOVE)) {
            containBoard.buildBoard((Board) evt.getNewValue());
        }
        if (evt.getPropertyName().equals(Game.STATUS)) {
            if (evt.getNewValue() == LevelStatus.IN_PROGRESS) {
                listView.addMessageList(" - Bievenu au 2048.");
            } else if (evt.getNewValue() == LevelStatus.FAIL) {
                listView.addMessageList(" - Partie terminée");
                listView.addMessageList(" - Vous avez perdu.");
            } else if (evt.getNewValue() == LevelStatus.WIN) {
                listView.addMessageList(" - Partie terminée");
                listView.addMessageList(" - Vous avez gagner.");
            }
        }
    }

    /**
     * Move the values of the game board in the direction of the event.
     *
     * @param e the direction which move.
     */
    private void keyDirection(KeyEvent e) {
        switch (e.getCode()) {
            case UP:
                this.controller.move(Direction.UP);
                break;
            case DOWN:
                this.controller.move(Direction.DOWN);
                break;
            case RIGHT:
                this.controller.move(Direction.RIGHT);
                break;
            case LEFT:
                this.controller.move(Direction.LEFT);
                break;
            default:
        }
    }
}
