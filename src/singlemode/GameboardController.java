/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singlemode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author hi-tech
 */
public class GameboardController implements Initializable {
    int f=0;
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
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    public static Label Computer;
    @FXML
    private Label score1;
    @FXML
    private Label player1;
    @FXML
    private Label score2;
    @FXML
    private ImageView newGame;
    @FXML
    private ImageView backButton;
    @FXML
    private Label draw;
    @FXML
    private Label drawScore;
    boolean GameEnds = false;
    public static boolean xTurn;
    public static boolean oTurn;
    public static boolean record=false;
    Label winning ; 

    LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
    String save="";
    int n;
    Random random = new Random();
    int randomNumber;
    int counter = 0;
    Media media;
    String[][] character = {{"0", "0", "0"}, {"0", "0", "0"}, {"0", "0", "0"}};
    public ArrayList<Button> boardButtons = new ArrayList<Button>();
    EventHandler<ActionEvent> eventHandler = (ActionEvent e) -> {
        actionPerformed(e);
    };
    @FXML
    private Label player2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ChangeView ch = new ChangeView();
        player1.setText(singleFormController.namex);
        boardButtons.add(button1);
        boardButtons.add(button2);
        boardButtons.add(button3);
        boardButtons.add(button4);
        boardButtons.add(button5);
        boardButtons.add(button6);
        boardButtons.add(button7);
        boardButtons.add(button8);
        boardButtons.add(button9);
        for (int i = 0; i < 9; i++) {
            boardButtons.get(i).setText("");
            boardButtons.get(i).addEventHandler(ActionEvent.ACTION, e -> {
                actionPerformed(e);
            });
        }
        if(singleFormController.flag==1){
            xTurn=true;
        }
        else{
            oTurn=true;
        }
        newGame.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                save="";  
                lhm.clear();
                GameEnds = false;
                counter = 0;
                for (int i = 0; i < 9; i++) {
                    boardButtons.get(i).setText("");
                }
                if (oTurn == false) {
                    xTurn = true;
                } else {
                    xTurn = false;
                }
            
            }
        });
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    ch.changeScene("/tec_tac_toe/home.fxml", event);
//                Stage stage = (Stage) backButton.getScene().getWindow();
//                stage.close();
                } catch (IOException ex) {
                    Logger.getLogger(GameboardController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    private void winner() {
        
        
        String t00 = boardButtons.get(0).getText();
        String t01 = boardButtons.get(1).getText();
        String t02 = boardButtons.get(2).getText();
        String t10 = boardButtons.get(3).getText();
        String t11 = boardButtons.get(4).getText();
        String t12 = boardButtons.get(5).getText();
        String t20 = boardButtons.get(6).getText();
        String t21 = boardButtons.get(7).getText();
        String t22 = boardButtons.get(8).getText();

        if ((t00.equals(t01) && t00.equals(t02) && !t00.equals(""))
                || (t10.equals(t11) && t10.equals(t12) && !t10.equals(""))
                || (t20.equals(t21) && t20.equals(t22) && !t20.equals(""))
                || (t00.equals(t10) && t00.equals(t20) && !t00.equals(""))
                || (t01.equals(t11) && t01.equals(t21) && !t01.equals(""))
                || (t02.equals(t12) && t02.equals(t22) && !t02.equals(""))
                || (t00.equals(t11) && t00.equals(t22) && !t00.equals(""))
                || (t02.equals(t11) && t02.equals(t20) && !t02.equals(""))) {
            GameEnds = true;
            if(f==1){
            String path = "src/assets/success.mp4";
             media = new Media(new File(path).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);
            mediaPlayer.setAutoPlay(true);
            Label winning = new Label("Congratulations You won"); 
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

            }else if(f==2){
                String path = "src/assets/fail.mp4";
                media = new Media(new File(path).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                 MediaView mediaView = new MediaView(mediaPlayer);
                mediaPlayer.setAutoPlay(true);
                Label winning = new Label("You Are the looooser "); 
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
            }
           
           

            if(record==true){
            recordGame();
             }
            

        }
        if (counter == 9 && GameEnds == false) {
            drawScore.setText(Integer.valueOf(drawScore.getText()) + 1 + "");
        }
        if (GameEnds == true) {
           
            if (oTurn == true) {
                if (xTurn == true) {
                    score1.setText(Integer.valueOf(score1.getText()) + 1 + "");
                } else {
                    score2.setText(Integer.valueOf(score2.getText()) + 1 + "");
                }
            } else {
                if (xTurn == true) {
                    score2.setText(Integer.valueOf(score2.getText()) + 1 + "");
                    
                } else {
                    score1.setText(Integer.valueOf(score1.getText()) + 1 + "");
                }
            }
        }
      
    }

    private void actionPerformed(ActionEvent e) {
        Button clickedButton = (Button) e.getSource();
        if (GameEnds == false && clickedButton.getText().equals("")) {
            f=1;
            counter++;
            clickedButton.setOpacity(1);
            if (xTurn == true) {
                
                clickedButton.setStyle("-fx-text-fill: #FEFF49");
                clickedButton.setText("X");
                lhm.put(clickedButton.getId(), clickedButton.getText());
               
                xTurn = false;
            } else {
                clickedButton.setStyle("-fx-text-fill: #FF3E80");
                clickedButton.setText("O");
                lhm.put(clickedButton.getId(), clickedButton.getText());
                 
                xTurn = true;
               
            }

            winner();
            

            if (GameEnds == false && counter < 9) {
                for (int i = 0; i < 9; i++) {
                    boardButtons.get(i).removeEventHandler(ActionEvent.ACTION, eventHandler);
                }
                counter++;
                for (;;) {
                    randomNumber = random.nextInt(9);
                    if (boardButtons.get(randomNumber).getText().equals("")) {
                        if (xTurn == false) {
                             boardButtons.get(randomNumber).setStyle("-fx-text-fill: #FF3E80");
                            boardButtons.get(randomNumber).setText("O");
                            lhm.put(boardButtons.get(randomNumber).getId(),boardButtons.get(randomNumber).getText());
                             
                            xTurn = true;
                        } else {
                            boardButtons.get(randomNumber).setStyle("-fx-text-fill: #FEFF49");
                            boardButtons.get(randomNumber).setText("X");
                            lhm.put(boardButtons.get(randomNumber).getId(),boardButtons.get(randomNumber).getText());
                            
                            xTurn = false;
                        }

                      
                        boardButtons.get(randomNumber).setOpacity(1);
                        break;
                    }
                    f=2;
                }
                winner();
                
                for (Button boardButton : boardButtons) {
                    boardButton.addEventHandler(ActionEvent.ACTION, eventHandler);
                }
            }
        }
       
    }
    
                 
     void recordGame(){
        FileOutputStream fos;
        Path records = Paths.get("c:\\offRecords");
        try {
            Files.createDirectories(records);
             n = new File("c:\\offRecords").list().length+1;
            String selectedFile = "c:\\offRecords\\game"+n+".txt";
            
            save+=player1.getText()+","+score1.getText()+",";
            save+="Computer"+","+score2.getText()+",";
            save+="Draw"+","+drawScore.getText()+",";
            lhm.entrySet().forEach((t) -> {
                save+=t.getKey()+","+t.getValue()+",";
            });
            byte [] b=save.getBytes();
            try{
                fos =new FileOutputStream(selectedFile);
                fos.write(b);
                fos.flush();
                fos.close();
                
            }catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }catch (IOException ex){
                ex.printStackTrace();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(GameboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
