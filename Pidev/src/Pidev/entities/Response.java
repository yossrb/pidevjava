/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pidev.entities;

import java.time.LocalDateTime;

public class Response {
    private int id;
    private int reclamation_id;
    private String reponse;
    private LocalDateTime date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReclamation_id() {
        return reclamation_id;
    }

    public void setReclamation_id(int reclamation_id) {
        this.reclamation_id = reclamation_id;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Response(int id, int reclamation_id, String reponse, LocalDateTime date) {
        this.id = id;
        this.reclamation_id = reclamation_id;
        this.reponse = reponse;
        this.date = date;
    }

    public Response(int reclamation_id, String reponse, LocalDateTime date) {
        this.reclamation_id = reclamation_id;
        this.reponse = reponse;
        this.date = date;
    }

    public Response() {
    }

    @Override
    public String toString() {
        return "Response{" + "id=" + id + ", reclamation_id=" + reclamation_id + ", reponse=" + reponse + ", date=" + date + '}';
    }

 
    
}
