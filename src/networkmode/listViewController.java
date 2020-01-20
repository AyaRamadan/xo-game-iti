package networkmode;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static networkmode.RegisterationController.dis;
import static networkmode.RegisterationController.items;
import static networkmode.RegisterationController.onlineUsers;
import static networkmode.RegisterationController.ps;
import static networkmode.RegisterationController.s;

/**
 * FXML Controller class
 *
 * @author Mina
 */
public class listViewController implements Initializable {

    @FXML
    private AnchorPane myPane;
    @FXML
    private ListView<String> listOfPlayers;
//    public static ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
    private Button refresh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        for(int i=0; i<items.length();i++){
//            
//        }
//        for (String item : items) {
//            System.out.println(item);
//        }
        listOfPlayers.setItems(items);

        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                System.out.println("refresh");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            ps = new PrintStream(s.getOutputStream());
                            onlineUsers.clear();
                            ps.println("refresh" + "." + "null" + "." + "null");
                            listOfPlayers.setItems(items);
//                    for (String item : items) {
//                        System.out.println(item);
//                    }
                        } catch (IOException ex) {
                            Logger.getLogger(listViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });
    }

    public void handleMouseClick(MouseEvent arg0) {
//        System.out.println("clicked on " + listOfPlayers.getSelectionModel().getSelectedItem());
    }
}
