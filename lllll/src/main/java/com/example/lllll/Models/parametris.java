package com.example.lllll.Models;

import javax.persistence.*;

@Entity
@Table(name = "Parametris")
public class parametris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String stranaProizv;
    public String proizv;
    @OneToOne(optional = true, mappedBy = "Parametris")
    private Tovar owner;

    public Tovar getOwner() {return owner;}
    public void setOwner(Tovar owner){this.owner = owner;}

    public parametris() { }

    public parametris(String stranaProizv, String proizv) {
        this.stranaProizv = stranaProizv;
        this.proizv = proizv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getstranaProizv() {
        return stranaProizv;
    }

    public void setstranaProizv(String stranaProizv) {
        this.stranaProizv = stranaProizv;
    }

    public String getproizv() {
        return proizv;
    }

    public void setproizv(String proizv) {
        this.proizv = proizv;
    }
}
