/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.gui;

import Pidev.entities.Categorie;
import Pidev.entities.Forum;
import Pidev.services.ServiceCategorie;
import Pidev.services.ServiceForum;
import Pidev.utils.AlertMaker;
import Pidev.utils.Upload;
import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abn
 */
public class BackForumListFXMLController implements Initializable {

        @FXML
    private TableView<Forum> tc_table;
    @FXML
    private TableColumn<Forum, Integer> c_id;
    @FXML
    private TableColumn<Forum, String> c_image;

    @FXML
    private TextField t_search;
    @FXML
    private Text t_image;

    ObservableList<Forum> myTransport = FXCollections.observableArrayList();
   
    ServiceForum sh = new ServiceForum();
    ServiceCategorie sc = new ServiceCategorie();

    Forum h = new Forum();
    @FXML
    private Text t_error;
   
    Stage stage;
    Scene scene;
    @FXML
    private TableColumn<Forum, String> c_content;
    @FXML
    private TableColumn<Forum, Integer> c_idCategorie;
    @FXML
    private TableColumn<Forum, Integer> c_views;
    @FXML
    private TableColumn<Forum, Integer> c_jaime;
    @FXML
    private TableColumn<Forum, Integer> c_jaimepas;
    @FXML
    private TextArea t_content;
    @FXML
    private ComboBox<Categorie> cb_category;
    @FXML
    private TableColumn<Forum, Integer> c_action;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
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
    private void onClickStats(ActionEvent event) {
    }


    @FXML
    private void onClickReset(ActionEvent event) {
        t_content.setText("");
        t_image.setText("");
        t_search.setText("");
         t_error.setText("");
         cb_category.getSelectionModel().clearSelection();
    }

    @FXML
    private void onClickAdd(ActionEvent event) {
        
        if(!t_content.getText().equals("") || !t_image.getText().equals("") && cb_category.getSelectionModel().getSelectedItem() != null )
        { 
            h.setContent(t_content.getText());
        h.setIdCategorie(cb_category.getSelectionModel().getSelectedItem().getId());
       
        sh.ajouter(h);
        onClickReset(event);
        loadData();
        AlertMaker.sendNotificationApi("Forum Created!", "Forum Created Sucessfully!");
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
                    Logger.getLogger(BackForumListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        ObservableList<Forum> myTransport = FXCollections.observableArrayList();
        c_action.setCellFactory(param -> {
            //Set up the ImageView
            Button btn = new Button("supprimer");
            //Set up the Table
             TableCell<Forum, Integer> cell = new TableCell<Forum, Integer>() {
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
                             AlertMaker.sendNotificationApi("Forum Deleted!", "Forum Sucessfully Deleted!");
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
        c_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        c_idCategorie.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
        c_jaime.setCellValueFactory(new PropertyValueFactory<>("jaime"));
        c_jaimepas.setCellValueFactory(new PropertyValueFactory<>("jaimepas"));
        c_jaimepas.setCellValueFactory(new PropertyValueFactory<>("views"));
                

        c_image.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();

            imageview.setFitHeight(100);
            imageview.setFitWidth(100);
            //Set up the Table
            TableCell<Forum, String> cell = new TableCell<Forum, String>() {
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
        
        cb_category.getItems().setAll(sc.getAll());
        
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
    


}

