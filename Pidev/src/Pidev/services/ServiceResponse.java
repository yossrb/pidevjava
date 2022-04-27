package Pidev.services;


import Pidev.entities.Response;
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

public class ServiceResponse implements IService<Response>{
    Connection cnx;
    public ServiceResponse (){
        cnx = DataSource.getInstance().getCnx();}

///*****************************Add Response***************************/
    @Override
    public void ajouter(Response response) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `reponse`( `reclamation_id`, `reponse`, `date`) VALUES ('"+response.getReclamation_id()+"','"+response.getReponse()+"','"+response.getDate()+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
        }
    
/*****************************Afficher Response***************************/
    
    public List<Response> getAll() {
        try {
            List<Response> LR = new ArrayList<>();
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM reponse";
            ResultSet rs= stm.executeQuery(query);
            while (rs.next()){
                Response response = new Response();
                response.setId(rs.getInt("id"));
                response.setReclamation_id(rs.getInt("reclamation_id"));
                response.setReponse(rs.getString("reponse"));
                response.setDate(rs.getTimestamp(4).toLocalDateTime());
                LR.add(response);
            }
            return LR;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
 /******************************* Delete Response *********************************************/
    @Override
    public void supprimer(int id) {
        try {
            Statement stm = cnx.createStatement();
            String query = "delete from reponse where id="+id;
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    
/******************************* FindById Response *********************************************/
    public Response SearchById(long id) throws SQLException{
        Statement stm = cnx.createStatement();
        Response response = new Response();
        String query = "select * from reponse where id="+id;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        response.setId(rs.getInt("id"));
        response.setReclamation_id(rs.getInt("reclamation_id"));
        response.setReponse(rs.getString("reponse"));
        response.setDate(rs.getTimestamp(4).toLocalDateTime());}
        return response;
        
        }     
    
/******************************* Modifier Reclamation *********************************************/
    @Override
    public void modifier(Response response){
        try {
            Statement stm = cnx.createStatement();
            String query = "UPDATE `reponse` SET `reclamation_id`='"+response.getReclamation_id()+"',`reponse`='"+response.getReponse()+"',`date`='"+LocalDateTime.now()+"' where id="+response.getId();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}


