package com.example.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "developer.all", query = "Select d from Developer d"),
        @NamedQuery(name = "developer.byName", query = "Select d from Developer d where d.name = :name"),
        @NamedQuery(name = "developer.clear", query = "Delete from Developer")

})
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Game> games;


    public Developer() {
        this.games = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGames() {
        return games;
    }

    @Override
    public String toString() {
        return "Developer [id=" + id + ", name=" + name + ", games=" + games + "]";
    }


}
