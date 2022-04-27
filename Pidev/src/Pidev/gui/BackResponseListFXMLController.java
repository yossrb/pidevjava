/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.gui;

import Pidev.entities.MailServerInfo;
import Pidev.entities.Reclamation;
import Pidev.entities.Response;
import Pidev.services.ServiceReclamation;
import Pidev.services.ServiceResponse;
import Pidev.utils.AlertMaker;
import Pidev.utils.EmailUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
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
public class BackResponseListFXMLController implements Initializable {
@FXML
    private TableView<Response> tc_table;
    @FXML
    private TableColumn<Response, Integer> c_id;
    @FXML
    private TableColumn<Response, Integer> c_action;
    @FXML
    private TableColumn<Response, String> c_sujet;
    @FXML
    private Text t_error;
    @FXML
    private TextField t_search;

    ServiceResponse sh = new ServiceResponse();
    ServiceReclamation sr = new ServiceReclamation();

    Response h = new Response();
        ObservableList<Response> myTransport = FXCollections.observableArrayList();
   
    Stage stage;
    Scene scene;
    @FXML
    private TableColumn<Response, Integer> c_reclamation;
    @FXML
    private ComboBox<Reclamation> cb_reclamation;
    @FXML
    private TextArea t_reponse;
    

    
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
             Logger.getLogger(BackResponseListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
             Logger.getLogger(BackResponseListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
             Logger.getLogger(BackResponseListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
             Logger.getLogger(BackResponseListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    

    }

    @FXML
    private void onClickReset(ActionEvent event) {
        t_reponse.setText("");
        cb_reclamation.getSelectionModel().clearSelection();
        t_error.setText("");
    }

    @FXML
    private void onClickAdd(ActionEvent event) {
        
        if(!t_reponse.getText().equals("") || cb_reclamation.getSelectionModel().getSelectedItem() != null )
        { 
        h.setReclamation_id(cb_reclamation.getSelectionModel().getSelectedItem().getId());
        h.setDate(LocalDateTime.now());
        h.setReponse(t_reponse.getText());
        
        sh.ajouter(h);
        onClickReset(event);
        loadData();
           MailServerInfo mailServerInfo = new MailServerInfo("smtp.gmail.com", 587, EmailUtil.c, EmailUtil.PASSWORD, Boolean.TRUE);
           try{
            
           EmailUtil.sendMail(mailServerInfo, "neoxam9@gmail.com",  "You Get A response From Us MagicMakers" , h.getReponse());
           
            AlertMaker.sendNotificationApi("Email", "Email Sucessfully Sent");
        
        } catch (Exception ex)
        {
                        AlertMaker.sendNotificationApi("Email Problem", "Email Not Sent Sent!");

        };
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
            Logger.getLogger(BackResponseListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
      private void loadData()
    {
         tc_table.getItems().clear();  
        
        c_action.setCellFactory(param -> {
            //Set up the ImageView
            Button btn = new Button("supprimer");
            //Set up the Table
             TableCell<Response, Integer> cell = new TableCell<Response, Integer>() {
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
        
        c_reclamation.setCellValueFactory(new PropertyValueFactory<>("reclamation_id"));
        c_sujet.setCellValueFactory(new PropertyValueFactory<>("reponse"));       
        c_action.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        myTransport.setAll(sh.getAll());
        tc_table.getItems().setAll(myTransport);
        
        cb_reclamation.getItems().setAll(sr.getAll());
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

}
