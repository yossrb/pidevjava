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
public class Forum {

    private int id;
    private String content;
    private int idCategorie;
    private String image;
    private int views;
    private int jaime;
    private int jaimepas;

    public Forum() {
    }

    public Forum(int id, String content, int idCategorie, String image, int views, int jaime, int jaimepas) {
        this.id = id;
        this.content = content;
        this.idCategorie = idCategorie;
        this.image = image;
        this.views = views;
        this.jaime = jaime;
        this.jaimepas = jaimepas;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getJaime() {
        return jaime;
    }

    public void setJaime(int jaime) {
        this.jaime = jaime;
    }

    public int getJaimepas() {
        return jaimepas;
    }

    public void setJaimepas(int jaimepas) {
        this.jaimepas = jaimepas;
    }

    @Override
    public String toString() {
        return "Forum{" + "id=" + id + ", content=" + content + ", idCategorie=" + idCategorie + ", image=" + image + ", views=" + views + ", jaime=" + jaime + ", jaimepas=" + jaimepas + '}';
    }

}
