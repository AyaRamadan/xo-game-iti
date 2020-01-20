/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkmode;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tec_tac_toe.Home;
import static tec_tac_toe.Home.serverIp;

/**
 * FXML Controller class
 *
 * @author Mina
 */
public class RegisterationController extends Thread implements Initializable {

    @FXML
    private ImageView Background;
    @FXML
    private TextField UserName;
    @FXML
    private Label NameLabel;
    @FXML
    private Label PassLabel;
    @FXML
    private TextField Password;
    @FXML
    private ImageView Logo;
    @FXML
    private Button Go;
    @FXML
    private Button register;
    @FXML
    private Label label;

    public static Socket s;
    public static DataInputStream dis;
    public static PrintStream ps;
    public static ArrayList<String> onlineUsers = new ArrayList<String>();

    public static boolean firingRefreshButton = false;
    String serverIp;
    FXMLLoader fxmlLoader;
    Parent root;
    Stage stage;
    boolean valid = false;
    public static String user;
    public static ObservableList<String> items = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Thread th;
        th = new Thread(this);

//        TextInputDialog dialog = new TextInputDialog("walter");
//        dialog.setTitle("Text Input Dialog");
//        dialog.setHeaderText("Please enter server ip address");
//        dialog.setContentText("Server ip:");
//        Optional<String> result = dialog.showAndWait();
//        if (result.isPresent()) {
//            System.out.println("your ip is: " + result.get());
//            serverIp = result.get();
//           
//        }
        serverIp = Home.serverIp;
        th.start();
        Go.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (UserName.getText() != null && Password.getText() != null) {

                    System.out.println(user);
                    if (!(UserName.getText().contains(".")) || !(Password.getText().contains("."))) {
                        user = UserName.getText();
                        try {
                            ps = new PrintStream(s.getOutputStream());
                            ps.println("login" + "." + UserName.getText() + "." + Password.getText());
                            UserName.clear();
                            Password.clear();

                        } catch (IOException ex) {
                            Logger.getLogger(RegisterationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        label.setText("(.)charcter is not allowed");
                    }
                }

            }
        });
        register.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (UserName.getText() != null && Password.getText() != null) {
                    label.setVisible(false);
                    String user = UserName.getText();
                    String pass = Password.getText();
                    if (!user.contains(".") && !pass.contains(".")) {
                        try {
                            ps = new PrintStream(s.getOutputStream());
                            ps.println("register" + "." + UserName.getText() + "." + Password.getText());
                            UserName.clear();
                            Password.clear();
                        } catch (IOException ex) {
                            Logger.getLogger(RegisterationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        label.setVisible(true);
                        label.setText("(.) is not allowed");
                    }
                } else {
                    label.setVisible(true);
                    label.setText("You Must Insert UserName And Password");
                }
            }
        });
    }

    @Override
    public void run() {
        while (true) {
            try {

                s = new Socket(serverIp, 5005);
                dis = new DataInputStream(s.getInputStream());
                String reply = dis.readLine();
                System.out.println(reply);
                onlineUsers = new ArrayList(Arrays.asList(reply.split("[.]")));
                for (int i = 0; i < onlineUsers.size(); i++) {
                    System.out.println(onlineUsers.get(i));
                }
                if (onlineUsers.get(0).equals("active")) {
                    Platform.runLater(() -> {
                        items.clear();
                        items.addAll(onlineUsers);
                        items.remove(user);
                        items.remove("active");
                    });
                } else if (onlineUsers.get(0).equals("request")) {
                   if (onlineUsers.get(1).equals(user)) {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setResizable(true);
                            alert.setTitle("request to play");
                            ButtonType yesButton = new ButtonType("Yes");
//                            ButtonType noButton = new ButtonType("No");
                            ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                            alert.getButtonTypes().setAll(yesButton, cancelButton);
                            alert.show();
                        });
                    }
                } else if (onlineUsers.get(0).equals("valid")) {
                    Platform.runLater(() -> {
                        try {
                            fxmlLoader = new FXMLLoader(getClass().getResource("listview.fxml"));
                            root = (Parent) fxmlLoader.load();
                            stage = new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setTitle("Active users");
                            stage.setScene(new Scene(root));
                            stage.setResizable(false);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                }
            } catch (IOException ex) {
                //  Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//              TextArea.clear();
//              TextArea.setText("Server is disconnected...");
//              break;
            }
        }
    }
}
