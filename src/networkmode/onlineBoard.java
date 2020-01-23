package networkmode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
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
import static networkmode.RegisterationController.onlineUsers;
import static networkmode.RegisterationController.ps;
import static networkmode.RegisterationController.s;
import static networkmode.RegisterationController.user;

/**
 * FXML Controller class
 *
 * @author hi-tech
 */
public class onlineBoard implements Initializable {

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
    @FXML
    private CheckBox record;
    String[][] character = {{"0", "0", "0"}, {"0", "0", "0"}, {"0", "0", "0"}};
    public static boolean xTurn = true;
    boolean GameEnds = false;
    boolean computer = true;
	boolean recordGame=false;
    int counter = 0;
    Media media;
    String btnVal;
	String Save;
    String id;
    String playerReceive;

//    public static boolean editable;
    public static boolean loseing = false;

    LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
    public static ArrayList<Button> boardButtons = new ArrayList<Button>();
    EventHandler<ActionEvent> eventHandler = (ActionEvent e) -> {
        actionPerformed(e);
    };

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        player1.setText(TwoModeController.namex);
//        player2.setText(TwoModeController.nameo);
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

    /*    newGame.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xTurn = true;
                GameEnds = false;
                counter = 0;
                for (int i = 0; i < 9; i++) {
                    boardButtons.get(i).setText("");
                    boardButtons.get(i).setOpacity(0);
                }
            }
        });*/
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            }
        });
		record.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                recordGame=true;
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
            String path = "src/assets/success.mp4";
            media = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);
            mediaPlayer.setAutoPlay(true);
            Label winning = new Label("Congrats You won");
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
			if(recordGame==true){
               recordGame();
             }

        }
        if (counter == 9 && GameEnds == false) {
            drawScore.setText(Integer.valueOf(drawScore.getText()) + 1 + "");
        }
        if (GameEnds == true) {
            if (xTurn == true) {
                score1.setText(Integer.valueOf(score1.getText()) + 1 + "");
            } else {
                score2.setText(Integer.valueOf(score2.getText()) + 1 + "");
            }
        }
    }

    private void actionPerformed(ActionEvent e) {
        try {
            Button clickedButton = (Button) e.getSource();
            if (xTurn == true && clickedButton.getText().equals("") && GameEnds == false) {
                clickedButton.setStyle("-fx-text-fill: #FEFF49");
                clickedButton.setOpacity(1);
                clickedButton.setText("X");
				lhm.put(clickedButton.getId(),clickedButton.getText());
                counter++;
                winner();
                xTurn = false;
                for (int i = 0; i < 9; i++) {
                    boardButtons.get(i).setDisable(true);

                }
            } else if (xTurn == false && clickedButton.getText().equals("") && GameEnds == false) {
                clickedButton.setStyle("-fx-text-fill: #FF3E80");
                clickedButton.setOpacity(1);
                clickedButton.setText("O");
				lhm.put(clickedButton.getId(),clickedButton.getText());
                counter++;
                winner();
                xTurn = true;
                for (int i = 0; i < 9; i++) {
                    boardButtons.get(i).setDisable(true);

                }
            }

            btnVal = clickedButton.getText();
            id = clickedButton.getId();
//            mySocket = new Socket("127.0.0.1", 5005);
//                    msg = textMsg.getText();
//            dis = new DataInputStream(s.getInputStream());
            ps = new PrintStream(s.getOutputStream());
//            String[] arr = {btnVal, id};
//            System.out.println("receiver is" + onlineUsers.get(2)+ "user is"+ user );
//            ps.println("play" + "." + onlineUsers.get(2)+"." + user + "." + id + "." + btnVal);

            if (onlineUsers.get(2).equals(user)) {
                playerReceive = onlineUsers.get(1);
            } else {
                playerReceive = onlineUsers.get(2);
            }
            if (GameEnds == false) {
                ps.println("play" + "." + user + "." + playerReceive + "." + id + "." + btnVal + "." +xTurn+"."+ "gamecont");
            }
            if (GameEnds == true) {
                ps.println("play" + "." + user + "." + playerReceive + "." + id + "." + btnVal + "."+xTurn +"."+ "gameends");

            }

        } catch (IOException ex) {
            Logger.getLogger(onlineBoard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
	private void recordGame(){
            try{
                FileOutputStream fos;
                Path records = Paths.get("c:\\onRecords");
                
                Files.createDirectories(records);
                int n = new File("c:\\onRecords").list().length+1;
                String selectedFile = "c:\\onRecords\\game"+n+".txt";
                Save="";
                Save+=player1.getText()+","+score1.getText()+",";
                Save+=player2.getText()+","+score2.getText()+",";
                Save+="Draw"+","+drawScore.getText()+",";
                
                for(Map.Entry<String,String> entry:lhm.entrySet()){
                    
                    Save+=entry.getKey()+","+entry.getValue()+",";
                }
                
                byte [] b=Save.getBytes();
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
                
            }catch (IOException ex) {
                Logger.getLogger(onlineBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
}
