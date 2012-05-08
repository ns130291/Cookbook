package model;
// Generated 26.04.2012 16:15:26 by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Receipt generated by hbm2java
 */
public class Receipt implements java.io.Serializable {

    private Integer id;
    private String description;
    private String picture;
    private Integer degree;
    private Integer duration;
    private String title;
    private String author;
    private String note;
    private Set<IngredientTbl> ingredientTbls = new HashSet<IngredientTbl>(0);
    private Set<Equipment> equipments = new HashSet<Equipment>(0);

    public Receipt() {
    }

    public Receipt(String description, String title) {
        this.description = description;
        this.title = title;
    }

    public Receipt(String description, String picture, Integer degree, Integer duration, String title, String author, String note, Set<IngredientTbl> ingredientTbls, Set<Equipment> equipments) {
        this.description = description;
        this.picture = picture;
        this.degree = degree;
        this.duration = duration;
        this.title = title;
        this.author = author;
        this.note = note;
        this.ingredientTbls = ingredientTbls;
        this.equipments = equipments;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getDegree() {
        return this.degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<IngredientTbl> getIngredientTbls() {
        return this.ingredientTbls;
    }

    public void setIngredientTbls(Set<IngredientTbl> ingredientTbls) {
        this.ingredientTbls = ingredientTbls;
    }

    public Set<Equipment> getEquipments() {
        return this.equipments;
    }

    public void setEquipments(Set<Equipment> equipments) {
        this.equipments = equipments;
    }

    @Override
    public String toString() {
        return "[Rezept: " + title + " Zutaten: " + ingredientTbls.toString() + " Zubehör: " + equipments.toString() + "]";
    }
}
