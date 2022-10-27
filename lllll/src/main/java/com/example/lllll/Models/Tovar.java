package com.example.lllll.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
public class Tovar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1, max = 100, message = "Поле должно содержать от 1 до 100 символов")
    @NotBlank(message = "Поле не должно быть пустым!")
    private String nameTovar;
    @NotNull(message = "Поле не моет быть пустым")
    private Integer articul;
    @NotNull(message = "Поле не моет быть пустым")
    private Integer ves;
    @NotBlank(message = "Поле не должно быть пустым!")
    private String cvet;
    @NotNull(message = "Поле не должно быть пустым!")
    private Integer kolvo;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTovar() {
        return nameTovar;
    }
    public  void setNameTovar(String tovars) {
        this.nameTovar = tovars;
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
    public  void setCvet(String cvet) {
        this.cvet = cvet;
    }

    public Integer getKolvo() {
        return kolvo;
    }
    public  void setKolvo(Integer kolvo) {
        this.kolvo = kolvo;
    }

    public Tovar(){ }

    public Tovar(String nameTovar, Integer articul, Integer ves, String cvet, Integer kolvo) {
        this.nameTovar = nameTovar;
        this.articul = articul;
        this.ves = ves;
        this.cvet = cvet;
        this.kolvo = kolvo;
    }
}
