package Pidev.services;


import Pidev.entities.Reclamation;
import Pidev.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceReclamation implements IService<Reclamation>{
        Connection cnx;
    public ServiceReclamation (){
        cnx = DataSource.getInstance().getCnx();}

    @Override
    public void ajouter(Reclamation p) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `reclamation`(`client_id`,`sujet`, `description`, `date`) VALUES ('"+p.getClienId()+"','"+p.getSujet()+"','"+p.getDescription()+"','"+p.getDate()+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}    }

    @Override
    public void supprimer(int id) {
try {
            String req = "DELETE FROM `reclamation` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Hotel deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Reclamation p) {
            try {
                Statement stm = cnx.createStatement();
                String query = "UPDATE `reclamation` SET `client_id`='"+p.getClienId()+"',`sujet`='"+p.getSujet()+"',`description`='"+p.getDescription()+"',`date`='"+LocalDateTime.now()+"' where id="+p.getId();
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
            }
 }

    @Override
    public List<Reclamation> getAll() {
            try {
                List<Reclamation> LR = new ArrayList<>();
                Statement stm = cnx.createStatement();
                String query = "SELECT * FROM reclamation";
                ResultSet rs= stm.executeQuery(query);
                while (rs.next()){
                    Reclamation reclamation = new Reclamation();
                    reclamation.setId(rs.getInt("id"));
                    reclamation.setClienId(rs.getInt("client_id"));
                    reclamation.setSujet(rs.getString("sujet"));
                    reclamation.setDescription(rs.getString("description"));
                    reclamation.setDate(rs.getTimestamp(5).toLocalDateTime());
                    LR.add(reclamation);
                }
                
                return LR;
            } catch (SQLException ex) {
                Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
 
}

/*****************************Add Reclamation***************************/

    
/*****************************getAll Reclamation***************************/
   
    
 /******************************* Delete Reclamation *********************************************/
  
    
/******************************* FindById Reclamation *********************************************/
    public Reclamation SearchById(long id) throws SQLException{
        Statement stm = cnx.createStatement();
        Reclamation reclamation = new Reclamation();
        String query = "select * from reclamation where id="+id;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        reclamation.setId(rs.getInt("id"));
        reclamation.setClienId(rs.getInt("client_id"));
        reclamation.setSujet(rs.getString("sujet"));
        reclamation.setDescription(rs.getString("description"));
        reclamation.setDate(rs.getTimestamp(5).toLocalDateTime());}
        return reclamation;
        }
    
    
        
    
/******************************* Modifier Reclamation *********************************************/


/******************************* SearchBySujet Reclamation *********************************************/
    public Reclamation SearchBySujet(String sujet) throws SQLException{
        Statement stm = cnx.createStatement();
        Reclamation reclamation = new Reclamation();
        String query = "select * from reclamation where sujet like '%"+sujet+"%'";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        reclamation.setId(rs.getInt("id"));
        reclamation.setClienId(rs.getInt("client_id"));
        reclamation.setSujet(rs.getString("sujet"));
        reclamation.setDescription(rs.getString("description"));
        reclamation.setDate(rs.getTimestamp(5).toLocalDateTime());}
        return reclamation;
        }
    
/******************************* SearchByTitle Reclamation *********************************************/
    public Reclamation SearchByDate(LocalDateTime date) throws SQLException, ParseException{
        Statement stm = cnx.createStatement();
        Reclamation reclamation = new Reclamation();
        String query = "select * from reclamation where date='"+date+"'";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        reclamation.setId(rs.getInt("id"));
        reclamation.setClienId(rs.getInt("client_id"));
        reclamation.setSujet(rs.getString("sujet"));
        reclamation.setDescription(rs.getString("description"));
        reclamation.setDate(rs.getTimestamp(5).toLocalDateTime());}
        return reclamation;
        }   

/******************************* Nombre Reclamation *********************************************/     
    public int nbReclamation(){
        int nbReclamation = 0;
        try {
        ResultSet set = DataSource.getInstance().
        getCnx().prepareStatement("SELECT COUNT(id) FROM reclamation")
        .executeQuery();
        if (set.next()) {
        nbReclamation = set.getInt(1);}}
        catch (SQLException ex) {
        Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);}
        return nbReclamation;
        }


}
