package com.example.lllll.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tovar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tovar;
    private Integer articul;
    private Integer ves;
    private String cvet;
    private Integer kolvo;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTovar() {
        return tovar;
    }
    public  void setTovar(String tovar) {
        this.tovar = tovar;
    }

    public Integer getArticul() {
        return articul;
    }
    public  void setArticul(Integer articul) {
        this.articul = articul;
    }

    public Integer getVes() {
        return ves;
    }
    public  void setVes(Integer ves) {
        this.ves = ves;
    }

    public String getCvet() {
        return cvet;
    }
    public  void setCvet(Integer cvet) {
        this.ves = cvet;
    }

    public Integer getKolvo() {
        return kolvo;
    }
    public  void setKolvo(Integer kolvo) {
        this.kolvo = kolvo;
    }

    public Tovar(){ }

    public Tovar(String tovar, Integer articul, Integer ves, String cvet, Integer kolvo) {
        this.tovar = tovar;
        this.articul = articul;
        this.ves = ves;
        this.cvet = cvet;
        this.kolvo = kolvo;
    }
}
