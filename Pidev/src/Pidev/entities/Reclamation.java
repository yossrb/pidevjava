/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.entities;

import java.time.LocalDateTime;

public class Reclamation {
    private int id;
    private int clienId;
    private String sujet;
    private String description;
    private LocalDateTime date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienId() {
        return clienId;
    }

    public void setClienId(int clienId) {
        this.clienId = clienId;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Reclamation(int id, int clienId, String sujet, String description, LocalDateTime date) {
        this.id = id;
        this.clienId = clienId;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
    }

    public Reclamation() {
    }

    public Reclamation(int clienId, String sujet, String description, LocalDateTime date) {
        this.clienId = clienId;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", clienId=" + clienId + ", sujet=" + sujet + ", description=" + description + ", date=" + date+'}' ;
    }


    
}
