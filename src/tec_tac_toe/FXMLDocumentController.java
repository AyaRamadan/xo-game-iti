
package tec_tac_toe;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 *
 * @author Aya
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private ImageView Background;
    @FXML
    private ImageView Logo;
    @FXML
    private Button SinglePlayer;
    @FXML
    private Button TwoPlayers;
    @FXML
    private Button OnlineGame;
    @FXML
    private Button Exit;
    @FXML
    private Button Records;
    FXMLLoader fxmlLoader;
    Parent root1;
    Stage stage;
    public static String serverIp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ChangeView ch=new ChangeView();
        SinglePlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                
                try {
                    ch.changeScene("/singlemode/single.fxml", event);
                    
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
        

            }
        });
        TwoPlayers.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try {
                    ch.changeScene("/twomode/twoMode.fxml", event);
                    
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        OnlineGame.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                TextInputDialog dialog = new TextInputDialog("walter");
                dialog.setTitle("Text Input Dialog");
                dialog.setHeaderText("Please enter server ip address");
                dialog.setContentText("Server ip:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    serverIp = result.get();
                    System.out.println("your ip is: " + result.get());

                    try {
                         ch.changeScene("/networkmode/Registeration.fxml", event);

                    } catch (IOException ex) {
                        Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                    }

//                    }
                }

            }
        });
        Records.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                  
                try {
                    ch.changeScene("/records/Records.fxml", event);

                } catch (IOException ex) {
                  
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });        

        Exit.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
            
                ((Node) (event.getSource())).getScene().getWindow().hide();

            }
        });
    }

    public static boolean validate(final String ip) {
        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        return ip.matches(PATTERN);
    }
}
