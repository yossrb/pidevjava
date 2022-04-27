/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.gui;

import Pidev.entities.Hotel;
import Pidev.entities.HotelCellCallBack;
import Pidev.entities.Reservation;
import Pidev.services.ServiceReservation;
import Pidev.utils.SettingsUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abn
 */
public class CellHotelListFXMLController implements Initializable , HotelCellCallBack {

    @FXML
    private HBox hbox;
    @FXML
    private ImageView iv_image;
    @FXML
    private Text t_name;
    @FXML
    private TextArea t_description;
    @FXML
    private Button btn_reservation;
    
    Stage stage;
    Scene scene;

    Hotel h = new Hotel();
    
    ServiceReservation sr = new  ServiceReservation();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setAllFields(Hotel hotel) {
        System.out.println(hotel);
        t_description.setText(hotel.getDescription());
        t_name.setText(hotel.getNom());
        System.out.println(getClass().getResourceAsStream("resources/" + hotel.getImage()));
        if(getClass().getResourceAsStream("resources/" + hotel.getImage()) != null)
        iv_image.setImage(new Image(getClass().getResourceAsStream("resources/" + hotel.getImage())));
        h = new Hotel();
        h = hotel;
        
        
    }

    @FXML
    private void onClickReservation(ActionEvent event) {
        SettingsUtil.res.setHotelFk(h);
        
       Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.setWidth(800);
        stage.setHeight(450);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("AddReservationFXML.fxml")), 800, 450);
        } catch (IOException ex) {
             Logger.getLogger(BackReservationListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
        
        
    }
    
}
