/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package records;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Aya
 */
public class RecordsController implements Initializable {
    FXMLLoader fxmlLoader;
    Parent root1;
    
    private Label label;
    @FXML
    private ListView<String> onRecord;
    @FXML
    private ListView<String> offRecord;
    @FXML
    private ImageView backBtn;
    private ObservableList<String> obvl = FXCollections.observableArrayList();
    private ObservableList<String> obvl2 = FXCollections.observableArrayList();

    static  File f;
    Stage stage;
    @FXML
    private Button Ref1;
    @FXML
    private Button Ref2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       ChangeView ch = new ChangeView();
        backBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    ch.changeScene("/tec_tac_toe/home.fxml", event);
//                Stage stage = (Stage) backBtn.getScene().getWindow();
//                stage.close();
                } catch (IOException ex) {
                    Logger.getLogger(RecordsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
         Ref1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                obvl.clear();
                offRecord.setItems(obvl);
                listRecords();

            }
        });
         Ref2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                obvl2.clear();
                onRecord.setItems(obvl2);
                listOnlineRecords();

            }
        });
        
        onRecord.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

            @Override
            public void handle(javafx.scene.input.MouseEvent event) {

                   if (onRecord.getSelectionModel().getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Empty cell");
                } else {

                            
                       try {
                           f =new File("c:\\onRecords\\"+onRecord.getSelectionModel().getSelectedItem()+".txt");
                           ch.changeScene("gameboard.fxml", event);
                       } catch (IOException ex) {
                           Logger.getLogger(RecordsController.class.getName()).log(Level.SEVERE, null, ex);
                       }
                                
                         
                                
                            
                }
            }
        });
   
        offRecord.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if (offRecord.getSelectionModel().getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Empty cell");
                } else {

                            try {
                                f =new File("c:\\offRecords\\"+offRecord.getSelectionModel().getSelectedItem()+".txt");
                                ch.changeScene("gameboard.fxml", event);
                                
                            } catch (IOException ex) {
                                Logger.getLogger(RecordsController.class.getName()).log(Level.SEVERE, null, ex);
                                
                            }
                }
            }
        });
    }
    
    public void listRecords() {
        File dir = new File("c:\\offRecords");
        String[] files = dir.list();

        for (String file : files) {
        
         obvl.add(file.split("\\.")[0]);
         offRecord.setItems(obvl);
        }

    }
    public void listOnlineRecords() {
        File dir = new File("c:\\onRecords");
        String[] files = dir.list();

        for (String file : files) {

            obvl2.add(file.split("\\.")[0]);
            onRecord.setItems(obvl2);
        }

}
}
