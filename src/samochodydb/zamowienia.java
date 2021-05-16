package samochodydb;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "zamowienia")
public class zamowienia implements Serializable {

    private static final long serialVersionUID=-300025L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_zamowienia", unique= true)
    private Integer id_zamowienia;

    @Column(name = "data_zamowienia")
    private Date data_zamowienia;

    @ManyToOne (optional = false)
    @JoinColumn(name = "id_klienta")
    private klienci klient;

    @OneToMany(mappedBy = "zamowienie")
    private Collection <zamowione_kursy> kursy = new ArrayList<zamowione_kursy>();

    public zamowienia(Date data_zamowienia) {
        this.id_zamowienia = id_zamowienia;
        this.data_zamowienia = data_zamowienia;
    }

    public zamowienia() {
    }

    public int getId_zamowienia() {
        return id_zamowienia;
    }

    public void setId_zamowienia(int id_zamowienia) {
        this.id_zamowienia = id_zamowienia;
    }

    public Date getData_zamowienia() {
        return data_zamowienia;
    }

    public void setData_zamowienia(Date data_zamowienia) {
        this.data_zamowienia = data_zamowienia;
    }

    public klienci getKlient() { return klient; }

    public void setKlient(klienci klient) { this.klient = klient; }

    @Override
    public String toString() {
        return data_zamowienia + " ";
    }
}
