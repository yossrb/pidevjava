/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.gui;

import Pidev.entities.Hotel;
import Pidev.entities.Reservation;
import Pidev.services.ServiceHotel;
import Pidev.utils.Upload;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abn
 */
public class BackHotelListFXMLController implements Initializable {

    @FXML
    private TableView<Hotel> tc_table;
    @FXML
    private TableColumn<Hotel, Integer> c_id;
    @FXML
    private TableColumn<Hotel, String> c_image;
    @FXML 
    private TableColumn<Hotel, String> c_name;
    @FXML 
    private TableColumn<Hotel, String> c_description;

    @FXML
    private TableColumn<Hotel, Double> c_prix;
    @FXML
    private TableColumn<Hotel, String> c_location;
    @FXML
    private TableColumn<Hotel, Integer> c_action;
    @FXML
    private TextField t_search;
    @FXML
    private TextField t_name;
    @FXML
    private TextArea t_description;
    @FXML
    private TextField t_prix;
    @FXML
    private TextField t_location;
    @FXML
    private Text t_image;

    ObservableList<Hotel> myTransport = FXCollections.observableArrayList();
   
    ServiceHotel sh = new ServiceHotel();
    Hotel h = new Hotel();
    @FXML
    private Text t_error;
   
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
    private void onClickStats(ActionEvent event) {
    }


    @FXML
    private void onClickReset(ActionEvent event) {
        t_description.setText("");
        t_location.setText("");
        t_name.setText("");
        t_prix.setText("");
         t_error.setText("");
    }

    @FXML
    private void onClickAdd(ActionEvent event) {
        
        if(!t_description.getText().equals("") || !t_location.getText().equals("") || !t_name.getText().equals("") || !t_prix.getText().equals(""))
        { 
            h.setDescription(t_description.getText());
        h.setLocalisation(t_location.getText());
        h.setNom(t_name.getText());
        h.setPrix(Double.parseDouble(t_prix.getText()));
        sh.ajouter(h);
        onClickReset(event);
        loadData();
        } else 
        {
            t_error.setText("Please verify all fields !");
        }
    }

    @FXML
    private void onClickAttach(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter exjpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter exjpg2 = new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg");
        FileChooser.ExtensionFilter expng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(exjpg, exjpg2, expng);
        fileChooser.setTitle("Choose an image File");

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            if (file.length() < 6000000) {

                try {
                    t_image.setText(file.getName());
                    h.setImage(file.getName());
                    Upload u = new Upload();
                    u.upload(file);
                    
                    System.out.println(file.getName());
                } catch (IOException ex) {
                    Logger.getLogger(BackHotelListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choose another image");
            }

        }

    }
    
    private void loadData()
    {
         tc_table.getItems().clear();  
        
        ObservableList<Hotel> myTransport = FXCollections.observableArrayList();
        c_action.setCellFactory(param -> {
            //Set up the ImageView
            Button btn = new Button("supprimer");
            //Set up the Table
             TableCell<Hotel, Integer> cell = new TableCell<Hotel, Integer>() {
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
        c_location.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        c_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        c_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        c_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        c_image.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();

            imageview.setFitHeight(100);
            imageview.setFitWidth(100);
            //Set up the Table
            TableCell<Hotel, String> cell = new TableCell<Hotel, String>() {
                public void updateItem(String item, boolean empty) {
                    if (item != null && !item.isEmpty()) {
                        System.out.println(getClass().getResourceAsStream("resources/" + item));

                if(getClass().getResourceAsStream("resources/" + item) != null)
                 imageview.setImage(new Image(getClass().getResourceAsStream("resources/" + item)));
              //          imageview.setImage(new Image(item));
                        setGraphic(imageview);
                    } else 
                    {
                        setGraphic(null);
                        setText(null);
                    }
                }
            };
            // Attach the imageview to the cell
            
            return cell;
        });
        c_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        c_action.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        myTransport.setAll(sh.getAll());
        tc_table.getItems().setAll(myTransport);
    }

   
    @FXML
    private void onRechercheTyped(KeyEvent event) {
        
        myTransport.setAll(sh.searchAll(t_search.getText()));
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
            scene = new Scene(FXMLLoader.load(getClass().getResource("BackHotelListFXML.fxml")), 1200, 800);
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
