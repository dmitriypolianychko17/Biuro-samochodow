package samochodydb;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="katalog_marek_auto")
public class katalog_marek_auto implements Serializable {
    private static final long serialVersionUID=-300025L;

    @Id
    @GeneratedValue
    @Column(name = "id_marki", unique = true)
    private int id_marki;

    @Column(name = "marka")
    private String marka;

    @Column(name = "koszt_transportowania")
    private int koszt_transportowania;

    @OneToMany(mappedBy = "katalog")
    private Collection<samochody> samochod = new ArrayList<samochody>();

    public katalog_marek_auto(String marka, int koszt_transportowania) {
        this.id_marki = id_marki;
        this.marka = marka;
        this.koszt_transportowania = koszt_transportowania;
    }

    public katalog_marek_auto() {
    }

    public int getId_marki() { return id_marki; }

    public void setId_marki(int id_marki) {
        this.id_marki = id_marki;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public int getKoszt_transportowania() {
        return koszt_transportowania;
    }

    public void setKoszt_transportowania(int koszt_transportowania) {
        this.koszt_transportowania = koszt_transportowania;
    }

    @Override
    public String toString() {
        return  marka  + " " + koszt_transportowania;
    }
}
