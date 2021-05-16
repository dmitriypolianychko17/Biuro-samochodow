package samochodydb;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="samochody")
public class samochody implements Serializable {
    private static final long serialVersionUID=-300025L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_samochodu", unique = true)
    private int id_samochodu;

    @Column (name ="firma")
    private String firma;

    @ManyToOne (optional = false)
    @JoinColumn(name = "id_marki")
    private katalog_marek_auto katalog;

    @ManyToOne (optional = false)
    @JoinColumn(name = "numer_inwentarzowy")
    private zamowione_kursy kurs;

    public samochody(String firma) {
        this.id_samochodu = id_samochodu;
        this.firma = firma;
    }

    public samochody() {
    }

    public int getId_samochodu() {
        return id_samochodu;
    }

    public void setId_samochodu(int id_samochodu) {
        this.id_samochodu = id_samochodu;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public katalog_marek_auto getKatalog() {
        return katalog;
    }

    public void setKatalog(katalog_marek_auto katalog) {
        this.katalog = katalog;
    }

    public zamowione_kursy getKurs() {
        return kurs;
    }

    public void setKurs(zamowione_kursy kurs) {
        this.kurs = kurs;
    }

    @Override
    public String toString() {
        return " " + id_samochodu+ " " +firma;

    }
}
