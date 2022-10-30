package com.example.lllll.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;


@Entity
public class Tovar {

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

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

    @OneToOne(optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name="parametris_id")
    private parametris Parametris;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Provider provider;

    @ManyToMany
    @JoinTable(name = "shop_tovar",
            joinColumns = @JoinColumn(name ="tovar_id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id"))
    private List<shop> shops;


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

    public parametris getParametris() {
        return Parametris;
    }
    public void setParametris(parametris Parametris) {
        this.Parametris = Parametris;
    }

    public Provider getProvider() {
        return provider;
    }
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getstranaProizv(){return Parametris.getstranaProizv();}
    public String getproizv(){return Parametris.getproizv();}

    public String getname(){return provider.getname();}
    public String getdate(){return provider.getdate();}

    private String username;
    private String password;
    private Boolean active;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public List<shop> getShops() {
        return shops;
    }
    public void setShops(List<shop> shops1) {
        this.shops = shops1;
    }

    public Tovar() { }

    public Tovar(String username, String password, Boolean active,Set<Role> roles, String nameTovar, Integer articul, Integer ves, String cvet, Integer kolvo, parametris Parametris, Provider provider) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.nameTovar = nameTovar;
        this.articul = articul;
        this.ves = ves;
        this.cvet = cvet;
        this.kolvo = kolvo;
        this.Parametris = Parametris;
        this.provider = provider;
    }
}
