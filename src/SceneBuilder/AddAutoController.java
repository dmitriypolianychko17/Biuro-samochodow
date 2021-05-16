package SceneBuilder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import samochodyDAO.katalogMarekAutoDAO;
import samochodydb.katalog_marek_auto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddAutoController implements Initializable {
    @FXML
    private Button addAutoButton;

    @FXML
    private Button backAutoHomeButton;

    @FXML
    private TextField markaAuto;

    @FXML
    private TextField kosztTransportowania;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        addAutoButton.setOnAction(event -> {
            String Marka = markaAuto.getText().trim();
            int koszt =Integer.parseInt(kosztTransportowania.getText().trim());

            if (!Marka.equals(""))
                Auto(Marka,koszt);
            else System.out.println("Puste");
        });

        backAutoHomeButton.setOnAction(event -> {
            backAutoHomeButton.getScene().getWindow().hide();
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
    public  void Auto (String Marka, int koszt){
        katalogMarekAutoDAO katalogDAO = new katalogMarekAutoDAO();
        katalog_marek_auto auto = new katalog_marek_auto(Marka, koszt);
        katalogDAO.saveAuto(auto);
        katalogDAO.getAllKatalog().add(auto);
    }
}
