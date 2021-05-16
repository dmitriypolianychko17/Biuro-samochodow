package samochodydb;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "zamowione_kursy")
public class zamowione_kursy implements Serializable {
    private static final long serialVersionUID=-300025L;

    @Id
    @Column(name = "numer_inwentarzowy")
    private int numer_inwentarzowy;

    @Column(name = "ciezar_ladunku")
    private int ciezar_ladunku;

    @Column(name="odleglosc")
    private int odleglosc;

    @ManyToOne (optional = false)
    @JoinColumn(name = "id_zamowienia")
    private zamowienia zamowienie;

    @OneToMany(mappedBy = "kurs")
    private Collection<samochody> samochodies = new ArrayList<samochody>();



    public zamowione_kursy( int numer_inwentarzowy, int ciezar_ladunku,int odleglosc ) {
        this.numer_inwentarzowy = numer_inwentarzowy;
        this.ciezar_ladunku = ciezar_ladunku;
        this.odleglosc = odleglosc;
    }

    public zamowione_kursy() {
    }


    public int getNumer_inwentarzowy() {
        return numer_inwentarzowy;
    }

    public void setNumer_inwentarzowy(int numer_inwentarzowy) {
        this.numer_inwentarzowy = numer_inwentarzowy;
    }

    public int getCiezar_ladunku() {
        return ciezar_ladunku;
    }

    public void setCiezar_ladunku(int ciezar_ladunku) {
        this.ciezar_ladunku = ciezar_ladunku;
    }

    public int getOdleglosc() {
        return odleglosc;
    }

    public void setOdleglosc(int odleglosc) {
        this.odleglosc = odleglosc;
    }

    public zamowienia getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(zamowienia zamowienie) {
        this.zamowienie = zamowienie;
    }

    @Override
    public String toString() {
        return  " " + numer_inwentarzowy;
    }
}
