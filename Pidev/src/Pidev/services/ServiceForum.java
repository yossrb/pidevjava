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
import Pidev.entities.Forum;

/**
 *
 * @author abdelazizmezri
 */
public class ServiceForum implements IService<Forum> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Forum p) {
        try {
            String req = "INSERT INTO `forum`(`content`, `idcategorie_id`, `image`)"
                    + "VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getContent());
            ps.setInt(2, p.getIdCategorie());
            ps.setString(3, p.getImage());
            ps.executeUpdate();
            System.out.println("Forum created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `forum` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Forum deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Forum p) {
        try {
            String req = "UPDATE `forum` SET `content`=?,`idcategorie_id`=?,`image`=?,`views`=?,`jaime`=?,`jaimepas`=? WHERE `id`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getContent());
            ps.setInt(2, p.getIdCategorie());
            ps.setString(3, p.getImage());
            ps.setInt(4, p.getViews());
            ps.setInt(5, p.getJaime());
            ps.setInt(6, p.getJaimepas());
            ps.executeUpdate();
            System.out.println("forum updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Forum> getAll() {
        List<Forum> list = new ArrayList<>();
        try {
            String req = "SELECT `id`, `content`, `idcategorie_id`, `image`, `views`, `jaime`, `jaimepas` FROM `forum`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Forum p = new Forum(rs.getInt(1) , rs.getString(2) , rs.getInt(3) ,   rs.getString(4) , rs.getInt(5) , rs.getInt(6) , rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
   
    
     public Forum getById(int id) {
        Forum forum = null;
        try {
            String req = "SELECT `id`, `content`, `idcategorie_id`, `image`, `views`, `jaime`, `jaimepas` FROM `forum` = " + id  ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Forum p = new Forum(rs.getInt(1) , rs.getString(2) , rs.getInt(3) ,   rs.getString(4) , rs.getInt(5) , rs.getInt(6) , rs.getInt(7));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return forum;
    }
    
    public List<Forum> searchAll(String search) {
        List<Forum> list = new ArrayList<>();
        try {
            String req = "SELECT `id`, `content`, `idcategorie_id`, `image`, `views`, `jaime`, `jaimepas` FROM `forum` where content like '%" + search + "%' or idcategorie_id '=" + search + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Forum p = new Forum(rs.getInt(1) , rs.getString(2) , rs.getInt(3) ,   rs.getString(4) , rs.getInt(5) , rs.getInt(6) , rs.getInt(7));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}
