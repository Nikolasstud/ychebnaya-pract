package com.example.lllll.Models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String name;
    public String date;

    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER)
    private Collection<Tovar> tovars;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }

    public String getdate() {
        return date;
    }
    public void setdate(String date) {
        this.date = date;
    }

    public Collection<Tovar> getTovars() {
        return tovars;
    }
    public void setTovars(Collection<Tovar> tovars) {
        this.tovars = tovars;
    }



    public Provider() { }

    public Provider(String name, String date) {
        this.name = name;
        this.date = date;

    }
}
