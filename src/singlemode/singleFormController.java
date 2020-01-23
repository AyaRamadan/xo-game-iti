
package singlemode;

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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tec_tac_toe.ChangeView;
import tec_tac_toe.Home;


/**
 *
 * @author mohamedx
 */
public class singleFormController implements Initializable {
    static int flag=0;
    private Label label;
    @FXML
    private AnchorPane Anchorpane;
    @FXML
    private ImageView Imgback;
    @FXML
    private TextField Textname;
    @FXML
    public RadioButton ChooseX;

    @FXML
    private RadioButton ChooseO;
    private Button Okbtn;
    @FXML
    private Button Cancelbtn;
    FXMLLoader fxmlLoader;
    Parent root1;
    Stage stage;
    boolean xSelected;
    boolean oSelected;
    public static String namex;
    @FXML
    private CheckBox CheckBox;
    @FXML
    private Button ok;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ChangeView ch=new ChangeView();
        final ToggleGroup group = new ToggleGroup();

        ChooseX.setToggleGroup(group);

        ChooseO.setToggleGroup(group);


        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (Textname.getText() != null && Textname.getText().length() != 0 && Textname.getText() != " ") {
                    namex = Textname.getText() + " :";
                } else {
                    namex = "playerx " + ":";
                }
                  try {
                    
                    ch.changeScene("/twomode/gameboard2.fxml", event);

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
                    Logger.getLogger(singleFormController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        ChooseX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameboardController.xTurn = true;
                GameboardController.oTurn = false;
                flag=1;
        
            }
        });
        ChooseO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameboardController.xTurn = false;
                GameboardController.oTurn = true;
                flag=0;
            }
        });
        CheckBox.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                GameboardController.record = true;
            }
        });
    }

}
