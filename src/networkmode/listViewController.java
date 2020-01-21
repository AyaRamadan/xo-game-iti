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

import static networkmode.RegisterationController.firingRefreshButton;
import static networkmode.RegisterationController.items;

import static networkmode.RegisterationController.onlineUsers;
import static networkmode.RegisterationController.ps;
import static networkmode.RegisterationController.s;
import static networkmode.RegisterationController.user;

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
   public static String player;

//    @FXML
//    public void handleMouseClick(MouseEvent arg0) {
//
//        System.out.println("clicked on " + listOfPlayers.getSelectionModel().getSelectedItem());
//    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        for(int i=0; i<items.length();i++){
//            
//        }

        for (String item : items) {
            System.out.println(item);
        }

        listOfPlayers.setItems(items);

        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println("refresh");
                try {
                    onlineUsers.clear();
                    ps = new PrintStream(s.getOutputStream());
                    ps.println("refresh" + "." + "null" + "." + "null");
                } catch (IOException ex) {
                    Logger.getLogger(listViewController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

//        if(firingRefreshButton==true){
//            refresh.fire();
//        }
//        System.out.println(listOfPlayers.getSelectionModel().getSelectedItem());
//        listOfPlayers.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//            @Override
//
//            public void handle(MouseEvent event) {
//                System.out.println("hello");
//                System.out.println("clicked on " + listOfPlayers.getSelectionModel().getSelectedItem());
//            }
//        });
    }

//    public void handleMouseClick(MouseEvent arg0) {
//
//        System.out.println("clicked on " + listOfPlayers.getSelectionModel().getSelectedItem());
//    }
    public void handleMouseClick(MouseEvent arg0) {
        try {
            player=listOfPlayers.getSelectionModel().getSelectedItem();
            ps = new PrintStream(s.getOutputStream());
            ps.println("request" + "." + player + "." + user);
            System.out.println(player);
        } catch (IOException ex) {
            Logger.getLogger(listViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
