package com.example.lllll.Models;

import javax.persistence.*;
import java.util.List;
@Entity
public class shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    private String nazvanie;

    @ManyToMany
    @JoinTable(name = "shop_furniture",
            joinColumns = @JoinColumn(name ="shop_id"),
            inverseJoinColumns = @JoinColumn(name = "tovar_id"))
    private List<Tovar> tovars;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String Address) { this.address = Address; }

    public String getnazvanie() {
        return nazvanie;
    }
    public void setnazvanie(String naz) {
        this.nazvanie = nazvanie;
    }

    public shop() { }

    public shop(String address, String nazvanie) {
        this.address = address;
        this.nazvanie = nazvanie;

    }
}
