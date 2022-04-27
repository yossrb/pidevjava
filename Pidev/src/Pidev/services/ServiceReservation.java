/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.services;

import Pidev.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Pidev.entities.Reservation;

/**
 *
 * @author abdelazizmezri
 */
public class ServiceReservation implements IService<Reservation> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reservation p) {
        try {
            String req = "INSERT INTO `reservation`(`hotel_id`, `date`, `paiement_m`, `date_debut`, `date_fin`)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getHotel());
            ps.setDate(2, p.getDate());
            ps.setString(3, p.getPaiment());
            ps.setDate(4, p.getDateDebut());
            ps.setDate(5, p.getDateFin());
            ps.executeUpdate();
            System.out.println("Reservation created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `reservation` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reservation p) {
        try {
            String req = "UPDATE `reservation` SET `hotel_id`=?,`date`=?,`paiement_m`=?,`date_debut`=?,`date_fin`=?  WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
                       ps.setInt(1, p.getHotel());
            ps.setDate(2, p.getDate());
            ps.setString(3, p.getPaiment());
            ps.setDate(4, p.getDateDebut());
            ps.setDate(5, p.getDateFin());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
            System.out.println("reservation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "SELECT r.`id`, hotel_id, `date`, `paiement_m`, `date_debut`, `date_fin` FROM `reservation` r join hotel h on h.id = hotel_id  ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Reservation p = new Reservation(rs.getInt(1), rs.getInt(2), rs.getDate(3) , rs.getString(4) , rs.getDate(5) , rs.getDate(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    public Reservation getReservation(Reservation r) {
        Reservation reservation = null;
        try {
            String req = "SELECT `id`, `hotel_id`, `date`, `paiement_m`, `date_debut`, `date_fin` FROM `reservation`  where hotel_id = " + r.getHotel() 
                    + " and date = '" + r.getDate() +"'" ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                reservation = new Reservation(rs.getInt(1) , rs.getInt(2) , rs.getDate(3) ,rs.getString(4) , rs.getDate(5) , rs.getDate(6) );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return reservation;
    }
    
    
    public List<Reservation> searchAll(String search) {
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "SELECT r.`id`, hotel_id, `date`, `paiement_m`, `date_debut`, `date_fin` FROM `reservation` r join hotel h on h.id = hotel_id where h.nom like '%" + search + "%' or paiement_m like '%" + search + "%'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Reservation p = new Reservation(rs.getInt(1), rs.getInt(2), rs.getDate(3) , rs.getString(4) , rs.getDate(5) , rs.getDate(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}
