package samochodydb;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "klienci")

public class klienci implements Serializable {
    private static final long serialVersionUID = -300025L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_klienta", unique = true)
    private int id_klienta;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    @Column(name = "adres")
    private String adres;

    @Column(name = "telefon")
    private int telefon;

    @OneToMany(mappedBy = "klient")
    private Collection<zamowienia> zamowienie = new ArrayList<zamowienia>();

    public klienci(String imie, String nazwisko, String adres, int telefon) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
        this.telefon = telefon;
    }

    public klienci() {

    }

    public int getId_klienta() {
        return id_klienta;
    }

    public void setId_klienta(int id_klienta) {
        this.id_klienta = id_klienta;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }


    @Override
    public String toString() {
        return  imie + " " + nazwisko;
    }
}
