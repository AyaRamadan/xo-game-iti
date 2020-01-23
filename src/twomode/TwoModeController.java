/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twomode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import singlemode.GameboardController;
import tec_tac_toe.Home;

/**
 * FXML Controller class
 *
 * @author hi-tech
 */
public class TwoModeController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private ImageView Imgback;
    @FXML
    private TextField Textnamex;
    @FXML
    private TextField Textnameo;
    @FXML
    private Button Okbtn;
    @FXML
    private Button Cancelbtn;
    FXMLLoader fxmlLoader;
    Parent root1;
    Stage stage;
    public static String namex;
    public static String nameo;
    @FXML
    private CheckBox CheckBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ChangeView ch =new ChangeView();
        
        Okbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (Textnamex.getText() != null && Textnamex.getText().length() != 0 && Textnamex.getText() != " ") {
                    namex = Textnamex.getText() + " :";
                } else {
                    namex = "playerx " + ":";
                }
                if (Textnameo.getText() != "" && Textnameo.getText() != null && Textnameo.getText().length() != 0) {
                    nameo = Textnameo.getText() + " :";
                } else {
                    nameo = "playery " + ":";
                }

                try {
                    
                    ch.changeScene("gameboard.fxml", event);

                } catch (IOException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        Cancelbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                try {
                    ch.changeScene("/tec_tac_toe/home.fxml", event);
                } catch (IOException ex) {
                    Logger.getLogger(TwoModeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        CheckBox.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                boardController.record=true;
            }
        });
        

    }

}
