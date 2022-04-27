/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.entities;

import java.sql.Date;

/**
 *
 * @author Abn
 */
public class Reservation {
    private int id ;
    private int hotel ;
    private Date date ;
    private String paiment ;
    private Date dateDebut ;
    private Date dateFin ;
    private Hotel hotelFk;

    public Reservation() {
    }

    public Hotel getHotelFk() {
        return hotelFk;
    }

    public void setHotelFk(Hotel hotelFk) {
        this.hotelFk = hotelFk;
    }

    public Reservation(int id, int hotel, Date date, String paiment, Date dateDebut, Date dateFin) {
        this.id = id;
        this.hotel = hotel;
        this.date = date;
        this.paiment = paiment;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel() {
        return hotel;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPaiment() {
        return paiment;
    }

    public void setPaiment(String paiment) {
        this.paiment = paiment;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", hotel=" + hotel + ", date=" + date + ", paiment=" + paiment + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }

 
    
    
}
