/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twomode;

import java.net.URL;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author hi-tech
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
    private ImageView newGame;
    @FXML
    private ImageView backButton;
    @FXML
    private Label draw;
    @FXML
    private Label drawScore;
    String[][] character = {{"0", "0", "0"}, {"0", "0", "0"}, {"0", "0", "0"}};
    boolean xTurn = true;
    boolean GameEnds = false;
    boolean computer = true;
    int counter = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        button8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                button8.setStyle("-fx-text-fill: white");
                if (xTurn == true && "0".equals(character[2][1]) && GameEnds == false) {
                    character[2][1] = "X";
                    button8.setOpacity(1);
                    button8.setText("X");
                    counter++;
                    winner();
                    xTurn = false;
                } else if (xTurn == false && "0".equals(character[2][1]) && GameEnds == false) {
                    character[2][1] = "O";
                    button8.setOpacity(1);
                    button8.setText("O");
                    counter++;
                    winner();
                    xTurn = true;
                }
            }
        });
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                button4.setStyle("-fx-text-fill: white");
                if (xTurn == true && "0".equals(character[1][0]) && GameEnds == false) {
                    character[1][0] = "X";
                    button4.setOpacity(1);;
                    button4.setText("X");
                    counter++;
                    winner();
                    xTurn = false;
                } else if (xTurn == false && "0".equals(character[1][0]) && GameEnds == false) {
                    character[1][0] = "O";
                    button4.setOpacity(1);;
                    button4.setText("O");
                    counter++;
                    winner();
                    xTurn = true;
                }
            }
        });
        button7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                button7.setStyle("-fx-text-fill: white");
                if (xTurn == true && "0".equals(character[2][0]) && GameEnds == false) {
                    character[2][0] = "X";
                    button7.setOpacity(1);;
                    button7.setText("X");
                    counter++;
                    winner();
                    xTurn = false;
                } else if (xTurn == false && "0".equals(character[2][0]) && GameEnds == false) {
                    character[2][0] = "O";
                    button7.setOpacity(1);;
                    button7.setText("O");
                    counter++;
                    winner();
                    xTurn = true;
                }
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                button2.setStyle("-fx-text-fill: white");
                if (xTurn == true && "0".equals(character[0][1]) && GameEnds == false) {
                    character[0][1] = "X";
                    button2.setOpacity(1);;
                    button2.setText("X");
                    counter++;
                    winner();
                    xTurn = false;
                } else if (xTurn == false && "0".equals(character[0][1]) && GameEnds == false) {
                    character[0][1] = "O";
                    button2.setOpacity(1);;
                    button2.setText("O");
                    counter++;
                    winner();
                    xTurn = true;
                }
            }
        });
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                button3.setStyle("-fx-text-fill: white");
                if (xTurn == true && "0".equals(character[0][2]) && GameEnds == false) {
                    character[0][2] = "X";
                    button3.setOpacity(1);
                    button3.setText("X");
                    counter++;
                    winner();
                    xTurn = false;
                } else if (xTurn == false && "0".equals(character[0][2]) && GameEnds == false) {
                    character[0][2] = "O";
                    button3.setOpacity(1);
                    button3.setText("O");
                    counter++;
                    winner();
                    xTurn = true;
                }
            }
        });
        button6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                button6.setStyle("-fx-text-fill: white");
                if (xTurn == true && "0".equals(character[1][2]) && GameEnds == false) {
                    character[1][2] = "X";
                    button6.setOpacity(1);;
                    button6.setText("X");
                    counter++;
                    winner();
                    xTurn = false;
                } else if (xTurn == false && "0".equals(character[1][2]) && GameEnds == false) {
                    character[1][2] = "O";
                    button6.setOpacity(1);;
                    button6.setText("O");
                    counter++;
                    winner();
                    xTurn = true;
                }
            }
        });
        button5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                button5.setStyle("-fx-text-fill: white");
                if (xTurn == true && "0".equals(character[1][1]) && GameEnds == false) {
                    character[1][1] = "X";
                    button5.setOpacity(1);
                    button5.setText("X");
                    counter++;
                    winner();
                    xTurn = false;
                } else if (xTurn == false && "0".equals(character[1][1]) && GameEnds == false) {
                    character[1][1] = "O";
                    button5.setOpacity(1);
                    button5.setText("O");
                    counter++;
                    winner();
                    xTurn = true;
                }
            }
        });
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                button1.setStyle("-fx-text-fill: white");
                if (xTurn == true && "0".equals(character[0][0]) && GameEnds == false) {
                    character[0][0] = "X";
                    button1.setOpacity(1);
                    button1.setText("X");
                    counter++;
                    winner();
                    xTurn = false;
                } else if (xTurn == false && "0".equals(character[0][0]) && GameEnds == false) {
                    character[0][0] = "O";
                    button1.setOpacity(1);;
                    button1.setText("O");
                    counter++;
                    winner();
                    xTurn = true;
                }
            }
        });
        button9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                button9.setStyle("-fx-text-fill: white");
                if (xTurn == true && "0".equals(character[2][2]) && GameEnds == false) {
                    character[2][2] = "X";
                    button9.setOpacity(1);;
                    button9.setText("X");
                    counter++;
                    winner();
                    xTurn = false;
                } else if (xTurn == false && "0".equals(character[2][2]) && GameEnds == false) {
                    character[2][2] = "O";
                    button9.setOpacity(1);;
                    button9.setText("O");
                    counter++;
                    winner();
                    xTurn = true;
                }
            }
        });

        newGame.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xTurn = true;
                GameEnds = false;
                counter = 0;
                button1.setText("");
                button2.setText("");
                button3.setText("");
                button4.setText("");
                button5.setText("");
                button6.setText("");
                button7.setText("");
                button8.setText("");
                button9.setText("");
                button1.setOpacity(0);
                button2.setOpacity(0);
                button3.setOpacity(0);
                button4.setOpacity(0);
                button5.setOpacity(0);
                button6.setOpacity(0);
                button7.setOpacity(0);
                button8.setOpacity(0);
                button9.setOpacity(0);
                character[0][0] = "0";
                character[0][1] = "0";
                character[0][2] = "0";
                character[1][0] = "0";
                character[1][1] = "0";
                character[1][2] = "0";
                character[2][0] = "0";
                character[2][1] = "0";
                character[2][2] = "0";
            }
        });
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();

            }
        });

    }

    private void winner() {
        String s1 = character[0][0];
        String s2 = character[0][1];
        String s3 = character[0][2];
        String s4 = character[1][0];
        String s5 = character[1][1];
        String s6 = character[1][2];
        String s7 = character[2][0];
        String s8 = character[2][1];
        String s9 = character[2][2];

        if ((s1.equals(s2) && s1.equals(s3) && s1 != "0")
                || (s4.equals(s5) && s4.equals(s6) && s4 != "0")
                || (s7.equals(s8) && s7.equals(s9) && s7 != "0")
                || (s1.equals(s5) && s1.equals(s9) && s1 != "0")
                || (s3.equals(s5) && s3.equals(s7) && s3 != "0")
                || (s1.equals(s4) && s1.equals(s7) && s1 != "0")
                || (s2.equals(s5) && s2.equals(s8) && s2 != "0")
                || (s3.equals(s6) && s3.equals(s9) && s3 != "0")) {
            GameEnds = true;
        }
        if (counter == 9 && GameEnds == false) {
            drawScore.setText(Integer.valueOf(drawScore.getText()) + 1 + "");
        }
        if (GameEnds == true) {
            if (xTurn == true) {
                score1.setText(Integer.valueOf(score1.getText()) + 1 + "");
            } else {
                score1.setText(Integer.valueOf(score1.getText()) + 1 + "");
            }
        }
    }

}
