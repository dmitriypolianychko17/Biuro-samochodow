package SceneBuilder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import samochodyDAO.KlienciDAO;
import samochodydb.klienci;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddKlientController implements Initializable {
    @FXML
    private TextField imie;
    @FXML
    private TextField nazwisko;
    @FXML
    private TextField adres;
    @FXML
    private TextField telefon;
    @FXML
    private Button AddKlient;
    @FXML
    private Button ExitAddKlient;
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AddKlient.setOnAction(event -> {
            String imieKlienta = imie.getText().trim();
            String nazwiskoKlienta = nazwisko.getText().trim();
            String adresKlienta = adres.getText().trim();
            int telefonKlienta = Integer.parseInt(telefon.getText().trim());

            if (!imieKlienta.equals("") && !nazwiskoKlienta.equals("") && !adresKlienta.equals(""))
                Klient(imieKlienta, nazwiskoKlienta, adresKlienta, telefonKlienta);
            else
                System.out.println("Puste");
        });

        ExitAddKlient.setOnAction(event -> {
            ExitAddKlient.getScene().getWindow().hide();
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
    public void Klient(String imieKlienta, String nazwiskoKlienta, String adresklienta, int telefonKlienta) {
        KlienciDAO klienciDAO = new KlienciDAO();
        klienci klient = new klienci(imieKlienta, nazwiskoKlienta, adresklienta, telefonKlienta);
        klienciDAO.saveKlient(klient);
        klienciDAO.getAllKlients().add(klient);
    }
}
