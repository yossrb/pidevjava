/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.gui;

import Pidev.entities.Hotel;
import Pidev.services.ServiceHotel;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abn
 */
public class HotelListFXMLController implements Initializable {

    @FXML
    private ListView<Hotel> lHotel;

    ServiceHotel sh = new ServiceHotel();
        Stage stage;
    Scene scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadList();
                
                }
     private void loadList() {
        lHotel.getItems().clear();  
        
        ObservableList<Hotel> myTransport = FXCollections.observableArrayList();
        lHotel.setCellFactory((ListView<Hotel> param) -> {
            ListCell<Hotel> cell = new ListCell<Hotel>() {
                @Override
                protected void updateItem(Hotel item, boolean empty) {
                    super.updateItem(item, empty);
                    if(item != null)
                    {
                    HBox page = new HBox();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("CellHotelListFXML.fxml"));
                    try {
                        page = loader.load();
                    } catch (IOException ex) {
                        System.err.println(ex);
                    }
                    CellHotelListFXMLController ctrl = loader.getController(); 
                    System.out.println(item);
                    ctrl.setAllFields(item);
                    setGraphic(page); }
                }
            };
            return cell;
        });

        myTransport.setAll(sh.getAll());
        lHotel.getItems().setAll(myTransport);
    }

    @FXML
    private void onClickBack(ActionEvent event) {
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
    
    
    private void onClickFront(ActionEvent event) {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.setWidth(1220);
        stage.setHeight(850);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("LoginFXML.fxml")), 1200, 800);
        } catch (IOException ex) {
             Logger.getLogger(HotelListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }

    private void onClickHotels(ActionEvent event) {
          Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.setWidth(1220);
        stage.setHeight(850);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("BackHotelListFXML.fxml")), 1200, 800);
        } catch (IOException ex) {
             Logger.getLogger(HotelListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }

    private void onClickCategories(ActionEvent event) {
          Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.setWidth(1220);
        stage.setHeight(850);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("BackCategorieListFXML.fxml")), 1200, 800);
        } catch (IOException ex) {
             Logger.getLogger(HotelListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }



    private void onClickReclmations(ActionEvent event) {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.setWidth(1220);
        stage.setHeight(850);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("BackReclamationListFXML.fxml")), 1200, 800);
        } catch (IOException ex) {
             Logger.getLogger(HotelListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }
    

    private void onClickResponses(ActionEvent event) {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.setWidth(1220);
        stage.setHeight(850);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("BackResponseListFXML.fxml")), 1200, 800);
        } catch (IOException ex) {
             Logger.getLogger(HotelListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }
    
}
