/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.tests;

import java.sql.Date;
import Pidev.entities.Hotel;
import Pidev.entities.Like;
import Pidev.entities.Reservation;
import Pidev.services.ServiceHotel;
import Pidev.services.ServiceLike;
import Pidev.services.ServiceReservation;

/**
 *
 * @author abdelazizmezri
 */
public class MainClass {

    public static void main(String[] args) {

        ServiceHotel sp = new ServiceHotel();
        ServiceReservation sr = new ServiceReservation();
        ServiceLike sl = new ServiceLike();
        
        Hotel p = new Hotel(0, "elMovimpik", "hotel de luxe 5 etoiles", 150.0, "", "");
        
        sp.ajouter(p);
        System.out.println(sp.getAll());
        sp.supprimer(sp.getByName("elMovimpik").getId());
        System.out.println(sp.getAll());

        Reservation r = new Reservation(0, sp.getByName("elMovimpik").getId(), new Date(2022, 4, 13), "espece", new Date(2022, 4, 20), new Date(2022, 4, 25));        
        sr.ajouter(r);
        System.out.println(sr.getAll());
        sr.supprimer(sr.getReservation(r).getId());
        System.out.println(sr.getAll());
        
        Like l = new Like(0, sp.getByName("elMovimpik").getId() , 5);
        sl.ajouter(l);
        System.out.println(sl.getAll());
        sl.supprimer(sl.getLikeByHotel(sp.getByName("elMovimpik").getId()).getId());
        System.out.println(sl.getAll());
    }

}
