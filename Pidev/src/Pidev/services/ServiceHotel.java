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

/**
 *
 * @author abdelazizmezri
 */
public class ServiceHotel implements IService<Hotel> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Hotel p) {
        try {
            String req = "INSERT INTO `hotel`(`nom`, `description`, `prix`, `localisataion`, `image`)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getDescription());
            ps.setDouble(3, p.getPrix());
            ps.setString(4, p.getLocalisation());
            ps.setString(5, p.getImage());
            ps.executeUpdate();
            System.out.println("Hotel created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `hotel` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Hotel deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Hotel p) {
        try {
            String req = "UPDATE `hotel` SET `nom`=?,`description`=?,`prix`=?,`localisataion`=?,`image`=? WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getDescription());
            ps.setDouble(3, p.getPrix());
            ps.setString(4, p.getLocalisation());
            ps.setString(5, p.getImage());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
            System.out.println("publicatioin updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Hotel> getAll() {
        List<Hotel> list = new ArrayList<>();
        try {
            String req = "SELECT `id`, `nom`, `description`, `prix`, `localisataion`, `image` FROM `hotel`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Hotel p = new Hotel(rs.getInt(1), rs.getString(2), rs.getString(3) , rs.getDouble(4) , rs.getString(5) , rs.getString(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    public Hotel getByName(String name) {
        Hotel hotel = null;
        try {
            String req = "SELECT `id`, `nom`, `description`, `prix`, `localisataion`, `image` FROM `hotel` where nom like '" + name + "'" ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                hotel = new Hotel(rs.getInt(1), rs.getString(2), rs.getString(3) , rs.getDouble(4) , rs.getString(5) , rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return hotel;
    }
    
     public Hotel getById(int id) {
        Hotel hotel = null;
        try {
            String req = "SELECT `id`, `nom`, `description`, `prix`, `localisataion`, `image` FROM `hotel` where id = " + id  ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                hotel = new Hotel(rs.getInt(1), rs.getString(2), rs.getString(3) , rs.getDouble(4) , rs.getString(5) , rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return hotel;
    }
    
    public List<Hotel> searchAll(String search) {
        List<Hotel> list = new ArrayList<>();
        try {
            String req = "SELECT `id`, `nom`, `description`, `prix`, `localisataion`, `image` FROM `hotel` where nom like '%" + search + "%' or localisataion like '%" + search + "%'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Hotel p = new Hotel(rs.getInt(1), rs.getString(2), rs.getString(3) , rs.getDouble(4) , rs.getString(5) , rs.getString(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}
