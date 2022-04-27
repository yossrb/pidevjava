/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.entities;

/**
 *
 * @author Abn
 */
public class Like {
    
    private int id ;
    private int hotelId;
    private int rate ;

    public Like(int id, int hotelId, int rate) {
        this.id = id;
        this.hotelId = hotelId;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Like{" + "id=" + id + ", hotelId=" + hotelId + ", rate=" + rate + '}';
    }
    
    
}
