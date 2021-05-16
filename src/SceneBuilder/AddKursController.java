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
import samochodyDAO.zamowioneKursyDAO;
import samochodydb.zamowienia;
import samochodydb.zamowione_kursy;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddKursController implements Initializable {
    ObservableList<zamowienia> zamowienias = FXCollections.observableArrayList();
    ArrayList<zamowienia> listaZam = new ArrayList<>();
    @FXML
    private TextField numerInwentarzowy;
    @FXML
    private TextField ciezarLadunku;
    @FXML
    private TextField odleglosc;
    @FXML
    private ComboBox zamowieniaComboBox;
    @FXML
    private Button dodajKursButton;
    @FXML
    private Button deleteKursButton;
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        listaZam =(ArrayList) session.createCriteria(zamowienia.class).list();
        zamowienias.addAll(listaZam);
        zamowieniaComboBox.setItems(zamowienias);

        dodajKursButton.setOnAction(event -> {
            zamowienia idzam =(zamowienia) zamowieniaComboBox.getValue();
            int numer_Inwentarzowy = Integer.parseInt(numerInwentarzowy.getText().trim());
            int ciezar_Ladunku = Integer.parseInt(ciezarLadunku.getText().trim());
            int odl = Integer.parseInt((odleglosc.getText().trim()));
            Kurs(idzam,numer_Inwentarzowy,ciezar_Ladunku,odl);
        });

        deleteKursButton.setOnAction(event -> {
            deleteKursButton.getScene().getWindow().hide();
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

    public void Kurs (zamowienia idzam, int numer_Inwentarzowy, int ciezar_Ladunku, int odl) {
        zamowioneKursyDAO kursDAO = new zamowioneKursyDAO();
        zamowione_kursy kurs = new zamowione_kursy(numer_Inwentarzowy,ciezar_Ladunku, odl);
        kurs.setZamowienie(idzam);
        kursDAO.saveKurs(kurs);
        kursDAO.getAllKourses().add(kurs);
    }
}
