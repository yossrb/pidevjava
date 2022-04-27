package Pidev.services;


import Pidev.entities.Categorie;
import Pidev.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceCategorie implements IService<Categorie>{
    Connection cnx;
    public ServiceCategorie (){
        cnx = DataSource.getInstance().getCnx();}

///*****************************Add Categorie***************************/
    @Override
    public void ajouter(Categorie categorie) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `categorie`(  `nom`) VALUES ('"+categorie.getNom()+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
        }
    
/*****************************Afficher Categorie***************************/
    
    public List<Categorie> getAll() {
        try {
            List<Categorie> LR = new ArrayList<>();
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM categorie";
            ResultSet rs= stm.executeQuery(query);
            while (rs.next()){
                Categorie categorie = new Categorie();
                categorie.setId(rs.getInt("id"));
                categorie.setNom(rs.getString("nom"));
               
                LR.add(categorie);
            }
            return LR;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
 /******************************* Delete Categorie *********************************************/
    @Override
    public void supprimer(int id) {
        try {
            Statement stm = cnx.createStatement();
            String query = "delete from categorie where id="+id;
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    
/******************************* FindById Categorie *********************************************/
    public Categorie SearchById(long id) throws SQLException{
        Statement stm = cnx.createStatement();
        Categorie categorie = new Categorie();
        String query = "select * from categorie where id="+id;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
                categorie.setId(rs.getInt("id"));
                categorie.setNom(rs.getString("nom"));
        }
        return categorie;
        
        }     
    
/******************************* Modifier Reclamation *********************************************/
    @Override
    public void modifier(Categorie categorie){
        try {
            Statement stm = cnx.createStatement();
            String query = "UPDATE `categorie` SET `nom`='"+categorie.getNom()+"' where id="+categorie.getId();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}


