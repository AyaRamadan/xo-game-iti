/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package records;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Aya
 */
public class boardController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView bg;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button6;
    @FXML
    private Button button5;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Label player1;
    @FXML
    private Label score1;
    @FXML
    private Label player2;
    @FXML
    private Label score2;
    @FXML
    private ImageView backButton;
    @FXML
    private Label draw;
    @FXML
    private Label drawScore;
    public Button [] boardButtons;

    String[] moves;
    String record = "";
    FileInputStream fis = null;
    int content;
    int count = 6;
    Stage stage;
    @FXML
    private ImageView playAgain;
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ChangeView ch = new ChangeView();

        boardButtons=new Button[]{button1,button2,button3,button4,button5,button6,button7,button8,button9};
        boardButtons[0]=(button1);
        boardButtons[1]=(button2);
        boardButtons[2]=(button3);
        boardButtons[3]=(button4);
        boardButtons[4]=(button5);
        boardButtons[5]=(button6);
        boardButtons[6]=(button7);
        boardButtons[7]=(button8);
        boardButtons[8]=(button9);

        try {
            playRecord(RecordsController.f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(boardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    ch.changeScene("Records.fxml", event);
//                Stage stage = (Stage) backButton.getScene().getWindow();
//                stage.close();
                } catch (IOException ex) {
                    Logger.getLogger(boardController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        playAgain.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    for (int i = 0; i < 9; i++) {         
                        boardButtons[i].setText("");
                    }
                    playRecord(RecordsController.f);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(boardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });

    }

    public void playRecord(File f) throws FileNotFoundException {

        try {
            FileInputStream fis = new FileInputStream(f);

            while ((content = fis.read()) != -1) {
                record += (char) content;
            }
            moves = record.split(",");
            player1.setText(moves[0]);
            score1.setText(moves[1]);
            player2.setText(moves[2]);
            score1.setText(moves[3]);
            draw.setText(moves[4]);
            drawScore.setText(moves[5]);
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished((e) -> {
                for (int i = count; i < moves.length; count += 2) {
                     for(int j=0;j<9;j++){
                         if(moves[i].equals(boardButtons[j].getId())){
                              boardButtons[j].setOpacity(1);
                              if(moves[i+1].equals("X"))
                                   boardButtons[j].setStyle("-fx-text-fill: #FEFF49");
                               else
                                   boardButtons[j].setStyle("-fx-text-fill: #FF3E80");
                              
                              boardButtons[j].setText(moves[i+1]);
                             
                              System.out.println(boardButtons[j].getText());              
                         }
                     }

                    count += 2;
                    break;
                }
                pause.playFromStart();
            });
            pause.play();

        } catch (IOException ex) {
            Logger.getLogger(RecordsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(RecordsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
             
        
    }

}
