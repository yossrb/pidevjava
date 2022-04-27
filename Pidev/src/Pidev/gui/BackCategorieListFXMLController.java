/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.gui;

import Pidev.entities.Categorie;
import Pidev.entities.Reclamation;
import Pidev.entities.Response;
import Pidev.services.ServiceCategorie;
import Pidev.services.ServiceReclamation;
import Pidev.services.ServiceResponse;
import Pidev.utils.AlertMaker;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abn
 */
public class BackCategorieListFXMLController implements Initializable {

    @FXML
    private TableColumn<Categorie, String> c_nom;
    @FXML
    private TextField t_nom;
    @FXML
    private TableView<Categorie> tc_table;
    @FXML
    private TableColumn<Categorie, Integer> c_id;
    @FXML
    private TableColumn<Categorie, Integer> c_action;
    @FXML
    private Text t_error;
    @FXML
    private TextField t_search;

    ServiceCategorie sh = new ServiceCategorie();

    Categorie h = new Categorie();
        ObservableList<Categorie> myTransport = FXCollections.observableArrayList();
   
    Stage stage;
    Scene scene;

    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }    

   
    @FXML
    private void onClickHotels(ActionEvent event) {
         Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
         stage.setWidth(1220);
        stage.setHeight(850);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("BackHotelListFXML.fxml")), 1200, 800);
        } catch (IOException ex) {
             Logger.getLogger(BackHotelListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
             Logger.getLogger(BackHotelListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onClickStats(ActionEvent event) {
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
             Logger.getLogger(BackHotelListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
             Logger.getLogger(BackHotelListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    

    }

    @FXML
    private void onClickReset(ActionEvent event) {
        t_nom.setText("");
        t_error.setText("");
    }

    @FXML
    private void onClickAdd(ActionEvent event) {
        
        if(!t_nom.getText().equals(""))
        { 

        h.setNom(t_nom.getText());
        
        sh.ajouter(h);
        onClickReset(event);
        loadData();
        AlertMaker.sendNotificationApi("categorie Added!", "Category Sucessfully Added!");
        } else 
        {
            t_error.setText("Please verify all fields !");
        }
    }
    @FXML
    private void onRechercheTyped(KeyEvent event) {
        
        try {
            if (t_search.getText().equals(""))
            {
                loadData();
            }else {
            myTransport.setAll( sh.SearchById( Integer.parseInt(t_search.getText())));
            tc_table.getItems().setAll(myTransport);
            }
        } catch (Exception ex) {
            Logger.getLogger(BackReclamationListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
      private void loadData()
    {
         tc_table.getItems().clear();  
        
        c_action.setCellFactory(param -> {
            //Set up the ImageView
            Button btn = new Button("supprimer");
            //Set up the Table
             TableCell<Categorie, Integer> cell = new TableCell<Categorie, Integer>() {
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
                             AlertMaker.sendNotificationApi("Categorie Deleted!", "Category Successfully Deleted!");
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
        c_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));       
        c_action.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        myTransport.setAll(sh.getAll());
        tc_table.getItems().setAll(myTransport);
        
    }


    private void onClickCategories(ActionEvent event) {
          Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.setWidth(1220);
        stage.setHeight(850);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("BackCategorieListFXML.fxml")), 1200, 800);
        } catch (IOException ex) {
             Logger.getLogger(BackForumListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }



  
    

    @FXML
    private void onClickResponses(ActionEvent event) {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.setWidth(1220);
        stage.setHeight(850);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("BackResponseListFXML.fxml")), 1200, 800);
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
