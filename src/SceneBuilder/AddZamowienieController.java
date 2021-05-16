package SceneBuilder;

import cfg.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import samochodyDAO.KlienciDAO;
import samochodyDAO.zamowieniaDAO;
import samochodydb.klienci;
import samochodydb.zamowienia;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddZamowienieController implements Initializable {
        ObservableList<klienci> kliencis = FXCollections.observableArrayList();
        ArrayList<klienci> listaKl = new ArrayList<>();
    @FXML
    private DatePicker dataZamowienia;

    @FXML
    private Button addZamowienie;

    @FXML
    private Button backZamHome;

    @FXML
    private ComboBox zamowienieKlienciCombo;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        listaKl = (ArrayList) session.createCriteria(klienci.class).list();
        kliencis.addAll(listaKl);
        zamowienieKlienciCombo.setItems(kliencis);

        addZamowienie.setOnAction(event -> {
            klienci idklient = (klienci) zamowienieKlienciCombo.getValue();
            LocalDate datai = dataZamowienia.getValue();
            Date data = Date.valueOf(datai);
            if (!data.equals(""))
                Zamowienie(data, idklient);
            else
                System.out.println("Puste");
        });

        backZamHome.setOnAction(event -> {
            backZamHome.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/SceneBuilder/Home.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent Home = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(Home));
            stage.show();
        });
    }

    public void Zamowienie(Date data, klienci idklient) {

        zamowieniaDAO zamDAO = new zamowieniaDAO();
        zamowienia zam = new zamowienia(data);
        zam.setKlient(idklient);
        zamDAO.saveZamowienie(zam);
        zamDAO.getAllZamowienia().add(zam);
    }
}
