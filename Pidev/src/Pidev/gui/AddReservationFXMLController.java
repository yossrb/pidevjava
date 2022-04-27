/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.gui;

import Pidev.entities.Hotel;
import Pidev.entities.Reservation;
import Pidev.services.ServiceHotel;
import Pidev.services.ServiceReservation;
import Pidev.utils.AlertMaker;
import Pidev.utils.SettingsUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abn
 */
public class AddReservationFXMLController implements Initializable {

    @FXML
    private Text t_error;
    @FXML
    private TextField t_paiment;
    @FXML
    private ComboBox<Hotel> cb_hotel;
    @FXML
    private DatePicker dp_startDate;
    @FXML
    private DatePicker dp_endDate;
    
       
    Stage stage;
    Scene scene;
        
    ServiceReservation sr = new ServiceReservation();
    ServiceHotel sh = new ServiceHotel();
    Reservation r = new Reservation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
                cb_hotel.getItems().setAll(sh.getAll());

        cb_hotel.getSelectionModel().select(SettingsUtil.res.getHotel());
    }    

     @FXML
    private void onClickReset() {
        t_paiment.setText("");
        dp_endDate.setValue(null);
        dp_startDate.setValue(null);
        t_error.setText("");
        cb_hotel.getSelectionModel().clearSelection();
    }

    @FXML
    private void onClickAdd(ActionEvent event) {
            LocalDate end = dp_endDate.getValue();
            LocalDate start = dp_startDate.getValue();
            long millis=System.currentTimeMillis();
            Date dateNow=new Date(millis);
            System.out.println(start);
            if(!t_paiment.getText().equals("") &&  start != null &&  end != null && cb_hotel.getSelectionModel().getSelectedItem() != null)
        {
          r.setPaiment(t_paiment.getText());
          r.setDate(dateNow); // get date systelm
          r.setDateDebut(new Date(start.getYear() , start.getMonthValue() , start.getDayOfMonth()));
          r.setDateFin(new Date(end.getYear() , end.getMonthValue() , end.getDayOfMonth()));
          r.setHotel(cb_hotel.getSelectionModel().getSelectedItem().getId());
          sr.ajouter(r);
            onClickReset();
        
        AlertMaker.sendNotificationApi("Reservation", "Your Reservation has been approved!");
        
         Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.setWidth(1220);
        stage.setHeight(850);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("HotelListFXML.fxml")), 1200, 800);
        } catch (IOException ex) {
             Logger.getLogger(AddReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
        
        
        } else 
        {
            t_error.setText("Please verify all fields !");
        }
    }

    @FXML
    private void onCbHotelClicked(ActionEvent event) {
    }

    @FXML
    private void onClickCancel(ActionEvent event) {
           Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
         stage.setWidth(1220);
        stage.setHeight(850);

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("HotelListFXML.fxml")), 1200, 800);
        } catch (IOException ex) {
             Logger.getLogger(BackHotelListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }
}
