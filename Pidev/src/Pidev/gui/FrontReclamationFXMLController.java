package Pidev.gui;

import Pidev.entities.Hotel;
import Pidev.entities.Reclamation;
import Pidev.services.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abn
 */
public class FrontReclamationFXMLController implements Initializable {

    private TableView<Reclamation> tc_table;
    private TableColumn<Reclamation, Integer> c_id;
    private TableColumn<Reclamation, String> c_description;
    private TableColumn<Reclamation, Integer> c_action;
    private TableColumn<Reclamation, String> c_sujet;
    @FXML
    private Text t_error;
    private TextField t_sujet;
    @FXML
    private TextArea t_description;
    private TextField t_search;

    ServiceReclamation sh = new ServiceReclamation();
    Reclamation h = new Reclamation();
        ObservableList<Reclamation> myTransport = FXCollections.observableArrayList();
   
    Stage stage;
    Scene scene;
    @FXML
    private TextArea t_sujets;
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

   

    
    
    @FXML
    private void onClickReset(ActionEvent event) {
        t_sujet.setText("");
        t_description.setText("");
        t_error.setText("");
    }

    @FXML
    
    private void onClickAdd(ActionEvent event) {
        
        if(!t_description.getText().equals("") || !t_sujet.getText().equals("") )
        { 
            h.setDescription(t_description.getText());
        h.setClienId(1);
        h.setDate(LocalDateTime.now());
        h.setSujet(t_sujet.getText());
        sh.ajouter(h);
        onClickReset(event);
        loadData();
        } else 
        {
            t_error.setText("Please verify all fields !");
        }
    }
    
      private void loadData()
    {
         tc_table.getItems().clear();  
        
        c_action.setCellFactory(param -> {
            //Set up the ImageView
            Button btn = new Button("supprimer");
            //Set up the Table
             TableCell<Reclamation, Integer> cell = new TableCell<Reclamation, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                    if(!empty && item != null) {
                        btn.setOnAction((event)
                                -> {
                            try {
                            sh.supprimer(item);
                            // update table
                             myTransport.setAll(sh.getAll());
                             tc_table.setItems(myTransport);
                            } catch(Exception ex)
                            {
                                System.err.println(ex);
                            }
                           
                        });
                    }
                }

            };
            // Attach the imageview to the cell
            cell.setGraphic(btn);
            return cell;

        });
        c_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        c_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        c_sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));       
        c_action.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        myTransport.setAll(sh.getAll());
        tc_table.getItems().setAll(myTransport);
    }
      
      
    @FXML
    private void onClickFront(ActionEvent event) {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.setWidth(1220);
        stage.setHeight(850);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("LoginFXML.fxml")), 1200, 800);
        } catch (IOException ex) {
             Logger.getLogger(BackForumListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onClickHotels(ActionEvent event) {
          Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.setWidth(1220);
        stage.setHeight(850);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("HotelListFXML.fxml")), 1200, 800);
        } catch (IOException ex) {
             Logger.getLogger(HotelListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }

   



    @FXML
    private void onClickReclmations(ActionEvent event) {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.setWidth(1220);
        stage.setHeight(850);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("BackReclamationListFXML.fxml")), 1200, 800);
        } catch (IOException ex) {
             Logger.getLogger(BackForumListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }
    

    

    @FXML
    private void onClickReservations(ActionEvent event) {
         Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.setWidth(1220);
        stage.setHeight(850);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("BackReservationListFXML.fxml")), 1200, 800);
        } catch (IOException ex) {
             Logger.getLogger(BackForumListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onClickForums(ActionEvent event) {
         Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.setWidth(1220);
        stage.setHeight(850);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("BackForumListFXML.fxml")), 1200, 800);
        } catch (IOException ex) {
             Logger.getLogger(BackForumListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }
    

}
