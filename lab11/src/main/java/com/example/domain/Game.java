package com.example.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "game.developed", query = "Select g from Game g where g.developed = true"),
        @NamedQuery(name = "game.byTitle", query = "Select g from Game g where g.title = :title"),
        @NamedQuery(name = "game.all", query = "Select g from Game g"),
        @NamedQuery(name = "game.clear", query = "Delete from Game")
})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String title;

    private String directorName;

    private String directorSurname;

    private String company;

    private String price;

    private Boolean developed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public void setDirectorSurname(String directorSurname) {
        this.directorSurname = directorSurname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getDeveloped() {
        return developed;
    }

    public void setDeveloped(Boolean developed) {
        this.developed = developed;
    }

    @Override
    public String toString() {
        return "Game [id=" + id + ", title=" + title + ", directorName=" + directorName + ", directorSurname="
                + directorSurname + ", company=" + company + ", price=" + price + ", developed=" + developed + "]";
    }

}
