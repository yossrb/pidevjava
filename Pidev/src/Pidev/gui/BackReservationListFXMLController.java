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
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abn
 */
public class BackReservationListFXMLController implements Initializable {

    @FXML
    private TableView<Reservation> tc_table;
    @FXML
    private TableColumn<Reservation, Integer> c_id;
    @FXML
    private TableColumn<Reservation, Integer> c_hotel;
    @FXML
    private TableColumn<Reservation, Date> c_date;
    @FXML
    private TableColumn<Reservation, String> c_paiment;
    @FXML
    private TableColumn<Reservation, Date> c_startDate;
    @FXML
    private TableColumn<Reservation, Date> c_endDate;
    @FXML
    private TableColumn<Reservation, Integer> c_action;
    @FXML
    private TextField t_paiment;
    @FXML
    private ComboBox<Hotel> cb_hotel;

    Stage stage;
    Scene scene;
    @FXML
    private Text t_error;
    @FXML
    private TextField t_search;
    
    ServiceReservation sr = new ServiceReservation();
    ServiceHotel sh = new ServiceHotel();
    Reservation r = new Reservation();
    ObservableList<Reservation> myTransport = FXCollections.observableArrayList();

    @FXML
    private DatePicker dp_startDate;
    @FXML
    private DatePicker dp_endDate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                onClickReset();

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
             Logger.getLogger(BackReservationListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
             Logger.getLogger(BackReservationListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onClickStats(ActionEvent event) {
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
        loadData();
        } else 
        {
            t_error.setText("Please verify all fields !");
        }
    }

    @FXML
    private void onCbHotelClicked(ActionEvent event) {
    }



    
      private void loadData()
    {
         tc_table.getItems().clear();  
        
        c_action.setCellFactory(param -> {
            //Set up the ImageView
            Button btn = new Button("supprimer");
            //Set up the Table
             TableCell<Reservation, Integer> cell = new TableCell<Reservation, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                    if(!empty && item != null) {
                        btn.setOnAction((event)
                                -> {
                            try {
                            sr.supprimer(item);
                            // update table
                             myTransport.setAll(sr.getAll());
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
        c_hotel.setCellFactory((param) -> {
                Text txt = new Text();
                         TableCell<Reservation, Integer> cell = new TableCell<Reservation, Integer>() {
                             @Override
                             protected void updateItem(Integer item, boolean empty) {
                                 super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                                 
                                 if(item != null)
                                 {
                                     Hotel h = new Hotel();
                                     h = sh.getById(item);
                                     txt.setText( String.valueOf( h.getId()) + "-" + h.getNom() );
                                     setGraphic(txt);
                                 }
                                 }
                           
                         
                         };
            return cell; //To change body of generated lambdas, choose Tools | Templates.
        });
        
        c_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        c_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        c_hotel.setCellValueFactory(new PropertyValueFactory<>("hotel"));
        c_paiment.setCellValueFactory(new PropertyValueFactory<>("paiment"));
        c_startDate.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        c_endDate.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        c_action.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        myTransport.setAll(sr.getAll());
        tc_table.getItems().setAll(myTransport);
        
        cb_hotel.getItems().setAll(sh.getAll());
        
    }

    @FXML
    private void onRechercheTyped(KeyEvent event) {
        myTransport.setAll(sr.searchAll(t_search.getText()));
        tc_table.setItems(myTransport);
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
