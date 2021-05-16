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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import samochodyDAO.samochodyDAO;
import samochodydb.katalog_marek_auto;
import samochodydb.samochody;
import samochodydb.zamowione_kursy;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddSamochodController implements Initializable {
    ObservableList<zamowione_kursy> kursies = FXCollections.observableArrayList();
    ArrayList<zamowione_kursy> listaKurs = new ArrayList<>();
    ObservableList<katalog_marek_auto> autos = FXCollections.observableArrayList();
    ArrayList<katalog_marek_auto> listaAuto = new ArrayList<>();
    @FXML
    private TextField firmaT;
    @FXML
    private Button addFirmaButton;
    @FXML
    private Button backHomeFirmaButton;
    @FXML
    private ComboBox kursyComboBox;
    @FXML
    private ComboBox katalogComboBox;
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        listaKurs =(ArrayList) session.createCriteria(zamowione_kursy.class).list();
        kursies.addAll(listaKurs);
        kursyComboBox.setItems(kursies);
        listaAuto=(ArrayList) session.createCriteria(katalog_marek_auto.class).list();
        autos.addAll(listaAuto);
        katalogComboBox.setItems(autos);

        addFirmaButton.setOnAction(event -> {
            String firma = firmaT.getText().trim();
            zamowione_kursy kursy = (zamowione_kursy) kursyComboBox.getValue();
            katalog_marek_auto katalogA = (katalog_marek_auto) katalogComboBox.getValue();
            if (!firma.equals("")) Samochod (firma, kursy, katalogA);
            else System.out.println("Puste");
        });
        backHomeFirmaButton.setOnAction(event -> {
            backHomeFirmaButton.getScene().getWindow().hide();
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
    public void  Samochod (String firma, zamowione_kursy kursy, katalog_marek_auto katalogA){
        samochodyDAO samDAO = new samochodyDAO();
        samochody samo = new samochody(firma);
        samo.setKatalog(katalogA);
        samo.setKurs(kursy);
        samDAO.saveSamochod(samo);
        samDAO.getAllSamochody().add(samo);
    }
}
