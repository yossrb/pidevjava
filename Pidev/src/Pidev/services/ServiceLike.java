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
import Pidev.entities.Hotel;
import Pidev.entities.Like;
import Pidev.entities.Reservation;

/**
 *
 * @author abdelazizmezri
 */
public class ServiceLike implements IService<Like> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Like p) {
        try {
            String req = "INSERT INTO `like`(`hotel_id`, `rate`)"
                    + "VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getHotelId());
            ps.setInt(2, p.getRate());
            ps.executeUpdate();
            System.out.println("Like created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `like` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Like deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Like p) {
        try {
            String req = "UPDATE `like` SET `rate`=?  WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);                     
            ps.setInt(1, p.getRate());
            ps.setInt(2, p.getId());
            ps.executeUpdate();
            System.out.println("like updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Like> getAll() {
        List<Like> list = new ArrayList<>();
        try {
            String req = "SELECT `id`, `hotel_id`, `rate` FROM `like`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Like p = new Like(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    
    public Like getLikeByHotel(int id) {
        Like like = null;
        try {
            String req = "SELECT `id`, `hotel_id`, `rate` FROM `like` where hotel_id = " + id ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                like = new Like(rs.getInt(1), rs.getInt(2), rs.getInt(3) );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return like;
    }
}
