/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkmode;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static networkmode.listViewController.player;
import static networkmode.onlineBoard.boardButtons;
import static networkmode.onlineBoard.loseing;
import static networkmode.onlineBoard.xTurn;
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
    public static boolean playing = false;

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
    public static String buttonid;
    public static String buttonVal;
    Media media;

    public static ObservableList<String> items = FXCollections.observableArrayList();
    String receiving;

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
        UserName.setText(null);
        Password.setText(null);
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
                } else if (UserName.getText() == null || Password.getText() == null) {
                    System.out.println("null user and pass");
                    label.setVisible(true);
                    label.setText("You Must Insert Username And Password");
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
                    System.out.println("online users ");
                    System.out.println(onlineUsers.get(i));
                }
                if (onlineUsers.get(0).equals("active")) {
                    Platform.runLater(() -> {
                        items.clear();
                        items.addAll(onlineUsers);
                        items.remove(user);
                        items.remove("active");
                    });
                } else if (onlineUsers.get(0).equals("request") && playing == false) {
                    if (onlineUsers.get(1).equals(user)) {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "request", ButtonType.OK, ButtonType.CANCEL);
                            alert.setResizable(true);
                            alert.setTitle("request to play");
                            alert.setContentText(onlineUsers.get(1) + "a user wants to play with you");
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                try {
                                    playing = true;
                                    System.out.println("ok");
                                    ps = new PrintStream(s.getOutputStream());
                                    ps.println("accept" + "." + onlineUsers.get(2) + "." + user);
                                    try {
                                        fxmlLoader = new FXMLLoader(getClass().getResource("onlinegameboard.fxml"));
                                        root = (Parent) fxmlLoader.load();
                                        stage = new Stage();
                                        stage.initModality(Modality.APPLICATION_MODAL);
                                        stage.setScene(new Scene(root));
                                        stage.setResizable(false);
                                        stage.show();
                                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                            @Override
                                            public void handle(WindowEvent event) {
                                                playing = false;
                                                System.out.println(playing);
                                            }
                                        });
                                    } catch (IOException ex) {
                                        Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(RegisterationController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            if (result.get() == ButtonType.CANCEL) {
                                try {
                                    System.out.println("CANCEL");
                                    ps = new PrintStream(s.getOutputStream());
                                    ps.println("refuse" + "." + onlineUsers.get(2) + "." + user);
                                } catch (IOException ex) {
                                    Logger.getLogger(RegisterationController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    }
                } else if (onlineUsers.get(0).equals("request") && playing == true) {
                    try {
                        ps = new PrintStream(s.getOutputStream());
                        ps.println("playing" + "." + onlineUsers.get(2) + "." + user);
                    } catch (IOException ex) {
                        Logger.getLogger(listViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (onlineUsers.get(0).equals("valid")) {
                    if (onlineUsers.get(1).equals(user)) {
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
                } else if (onlineUsers.get(0).equals("accept")) {
                    if (onlineUsers.get(1).equals(user)) {
                        playing = true;
                        Platform.runLater(() -> {
                            try {
                                fxmlLoader = new FXMLLoader(getClass().getResource("onlinegameboard.fxml"));
                                root = (Parent) fxmlLoader.load();
                                stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.setScene(new Scene(root));
                                stage.setResizable(false);
                                stage.show();
                                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                    @Override
                                    public void handle(WindowEvent event) {
                                        playing = false;
                                        System.out.println(playing);
                                    }
                                });
                            } catch (IOException ex) {
                                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }
                } else if (onlineUsers.get(0).equals("play") && onlineUsers.get(6).equals("gamecont")) {
//                    if(onlineUsers.get(1).equals(user)){
//                        receiving=onlineUsers.get(1);
//                    }else{
//                        receiving=onlineUsers.get(2);
//                    }

//                    System.out.println("online user is"+onlineUsers.get(1) );
                    if (onlineUsers.get(2).equals(user)) {
//                        System.out.println("heelo online user");
                        Platform.runLater(() -> {
                            for (int i = 0; i < 9; i++) {
                                boardButtons.get(i).setDisable(false);
//                            if (reply.startsWith("b")) {
                                System.out.println(boardButtons.get(i).getId());
//                                System.out.println(msg[0]);

                                if (boardButtons.get(i).getId().equals(onlineUsers.get(3))) {
                                    boardButtons.get(i).setText(onlineUsers.get(4));
                                    boardButtons.get(i).setOpacity(1);

                                    xTurn = Boolean.parseBoolean(onlineUsers.get(5));
//                                boardButtons.get(i).setText("esraa");
                                    if (onlineUsers.get(4).equals("X")) {
                                        boardButtons.get(i).setStyle("-fx-text-fill: #FEFF49");

                                    }
                                    if (onlineUsers.get(4).equals("O")) {
                                        boardButtons.get(i).setStyle("-fx-text-fill: #FF3E80");
                                    }
//                                    onlineBoard.editable =true;

                                }

                            }
                        });
                    }
                } else if (onlineUsers.get(0).equals("play") && onlineUsers.get(6).equals("gameends")) {
                    Platform.runLater(() -> {
//                        for (int i = 0; i < 9; i++) {
//                            boardButtons.get(i).setDisable(false);
////                            if (reply.startsWith("b")) {
//                            System.out.println(boardButtons.get(i).getId());
////                                System.out.println(msg[0]);
//
//                            if (boardButtons.get(i).getId().equals(onlineUsers.get(3))) {
//                                boardButtons.get(i).setText(onlineUsers.get(4));
//                                boardButtons.get(i).setOpacity(1);
//                                xTurn = Boolean.parseBoolean(onlineUsers.get(5));
////                                boardButtons.get(i).setText("esraa");
//                                if (onlineUsers.get(4).equals("X")) {
//                                    boardButtons.get(i).setStyle("-fx-text-fill: #FEFF49");
//
//                                }
//                                if (onlineUsers.get(4).equals("O")) {
//                                    boardButtons.get(i).setStyle("-fx-text-fill: #FF3E80");
//                                }
////                                    onlineBoard.editable =true;
//
//                            }
//
//                        }

                        String path = "src/assets/fail.mp4";
                        media = new Media(new File(path).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        MediaView mediaView = new MediaView(mediaPlayer);
                        mediaPlayer.setAutoPlay(true);
                        Label winning = new Label("You loose :D");
                        winning.setAlignment(Pos.CENTER);
                        VBox content = new VBox(10, winning, mediaView);
                        content.setAlignment(Pos.CENTER);
                        Dialog d1 = new Dialog();
                        d1.setResizable(true);
                        d1.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
                        d1.getDialogPane().setContent(content);
                        d1.getDialogPane().setMinHeight(500);
                        d1.getDialogPane().setMinWidth(500);

                        d1.setOnShowing(e -> mediaPlayer.play());
                        d1.setOnCloseRequest(e -> mediaPlayer.stop());
                        d1.show();
                    });

                } else if (onlineUsers.get(0).equals("invalid")) {
                    Platform.runLater(() -> {
                        label.setVisible(true);
                        label.setText("Wrong User name or password");
                    });
                } else if (onlineUsers.get(0).equals("refuse")) {
                    if (onlineUsers.get(1).equals(user)) {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "request", ButtonType.OK);
                            alert.setResizable(true);
                            alert.setTitle("Requesr Refused");
                            alert.setContentText("Your Request to Play Was Refused");
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            Optional<ButtonType> result = alert.showAndWait();
                        });
                    }
                } else if (onlineUsers.get(0).equals("invalid")) {
                    Platform.runLater(() -> {
                        label.setVisible(true);
                        label.setText("Wrong User name or password");
                    });
                } else if (onlineUsers.get(0).equals("refuse")) {
                    if (onlineUsers.get(1).equals(user)) {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "request", ButtonType.OK);
                            alert.setResizable(true);
                            alert.setTitle("Requesr Refused");
                            alert.setContentText("Your Request to Play Was Refused");
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            Optional<ButtonType> result = alert.showAndWait();
                        });

                    }

                } else if (onlineUsers.get(0).equals("playing")) {
                        System.out.println("playing");
                        if (onlineUsers.get(1).equals(user)) {
                            System.out.println("playing");
                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "request", ButtonType.OK);
                                alert.setResizable(true);
                                alert.setTitle("respond");
                                alert.setContentText(onlineUsers.get(2) + " is already playing");
                                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                                Optional<ButtonType> result = alert.showAndWait();
                            });
                        }
                    }

            } catch (IOException ex) {

//                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "request", ButtonType.OK);
                    alert.setResizable(true);
                    alert.setTitle("ERROR");
                    alert.setContentText("SERVER IS DISCONNECTED...");
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    Optional<ButtonType> result = alert.showAndWait();
                });
                break;
            }
        }
    }
}
