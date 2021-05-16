package SceneBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import samochodyDAO.*;
import samochodydb.*;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private ObservableList<klienci> klienciData;
    private ObservableList<samochody> SamochodyData;
    private ObservableList<zamowione_kursy> kursyData;
    private ObservableList<zamowienia> zamowieniaData;
    private ObservableList<katalog_marek_auto> katalogData;
    //TABLEVIEW samochody
    @FXML
    private TableView<samochody> samochodyTableView;

    @FXML
    private TableColumn<samochody, Integer> idSamochoduColumn;

    @FXML
    private TableColumn<samochody, String> firmaColumn;

    @FXML
    private TableColumn<samochody, zamowione_kursy> numerInwentarzowy1Column;

    @FXML
    private TableColumn<samochody, katalog_marek_auto> idMarki1Column;

    // TABLEVIEW katalog marek auto
    @FXML
    private TableView<katalog_marek_auto> katalogTableView;

    @FXML
    private TableColumn<katalog_marek_auto, Integer> idMarkiColumn;

    @FXML
    private TableColumn<katalog_marek_auto, String> markaColumn;

    @FXML
    private TableColumn<katalog_marek_auto, Integer> skosztTransportColumn;

    // TABLEVIEW zamowione_kursy
    @FXML
    private TableView<zamowione_kursy> kursyTableView;

    @FXML
    private TableColumn<zamowione_kursy, zamowienia> idZamowienia1Column;

    @FXML
    private TableColumn<zamowione_kursy, Integer> numerInwentarzowyColumn;

    @FXML
    private TableColumn<zamowione_kursy, Integer> ciezarLadunkuColumn;

    @FXML
    private TableColumn<zamowione_kursy, Integer> odlegloscColumn;

    // TableView zamowienia
    @FXML
    private TableView<zamowienia> zamowieniaTableView;

    @FXML
    private TableColumn<zamowienia, Integer> idZamowieniaColumn;

    @FXML
    private TableColumn<zamowienia, Date> dataColumn;

    @FXML
    private TableColumn<zamowienia, klienci> idKlienta1Column;

    // TABLEVIEW klienci
    @FXML
    private TableView<klienci> klienciTableView;

    @FXML
    private TableColumn<klienci, Integer> idKlientaColumn;

    @FXML
    private TableColumn<klienci, String> imieColumn;

    @FXML
    private TableColumn<klienci, String> nazwiskoColumn;

    @FXML
    private TableColumn<klienci, String> adresColumn;

    @FXML
    private TableColumn<klienci, Integer> telefonColumn;

    @FXML
    private Button addKlient;

    @FXML
    private Button addZamowienie;

    @FXML
    private  Button usunKlientaButton;

    @FXML
    private  Button usunZamowienieButton;

    @FXML
    private Button addKursButton;

    @FXML
    private Button usunKursButton;

    @FXML
    private  Button addAutoButton;

    @FXML
    private Button deleteAutoButton;

    @FXML
    private Button addSamochodButton;

    @FXML
    private Button deleteSamochodButton;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb){
          //wyświetlanie sceny która dodaje nowego klienta
        addKlient.setOnAction(event -> {
            addKlient.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/SceneBuilder/AddKlient.fxml"));

            try{
                loader.load();
            } catch (IOException e){
                e.printStackTrace();
            }
            Parent AddKlient = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(AddKlient));
            stage.show();
        });
            // wyświetlanie sceny która dodaje nowe zamowienie
        addZamowienie.setOnAction(event -> {
            addZamowienie.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/SceneBuilder/AddZamowienie.fxml"));

            try{
                loader.load();
            } catch (IOException e){
                e.printStackTrace();
            }
            Parent AddZamowienie = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(AddZamowienie));
            stage.show();
        });
        // wyświetlanie sceny która dodaje nowy kurs
        addKursButton.setOnAction(event -> {
            addKursButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/SceneBuilder/AddKurs.fxml"));

            try{
                loader.load();
            } catch (IOException e){
                e.printStackTrace();
            }
            Parent AddKurs = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(AddKurs));
            stage.show();
        });
        // wyświetlanie sceny która dodaje nowe auto
        addAutoButton.setOnAction(event -> {
            addAutoButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/SceneBuilder/AddAuto.fxml"));
            try {
                loader.load();
            } catch (IOException e){
                e.printStackTrace();
            }
            Parent AddAuto = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene((AddAuto)));
            stage.show();
        });
        // wyświetlanie sceny która dodaje nowa firme
        addSamochodButton.setOnAction(event -> {
            addSamochodButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/SceneBuilder/AddSamochod.fxml"));
            try {
                loader.load();
            } catch (IOException e){
                e.printStackTrace();
            }
            Parent AddSamochod =loader.getRoot();
            Stage stage =new Stage();
            stage.setScene(new Scene(AddSamochod));
            stage.show();
        });

        // usuwanie wybranego klienta przez przycisk
      usunKlientaButton.setOnAction((ActionEvent)->{
            if (klienciTableView.getSelectionModel().getSelectedItem() !=null){
                KlienciDAO klienciDAO = new KlienciDAO();
                klienci klient = (klienci) klienciTableView.getSelectionModel().getSelectedItem();
                klienciDAO.deleteKlient(klient.getId_klienta());
                klienciTableView.setItems(FXCollections.observableList(klienciDAO.getAllKlients()));
            }
        });
        // usuwanie wybranego zamówiena przez przycisk
      usunZamowienieButton.setOnAction((ActionEvent) ->{
          if (zamowieniaTableView.getSelectionModel().getSelectedItem() !=null){
              zamowieniaDAO ZamowieniaDAO = new zamowieniaDAO();
              zamowienia zamow = (zamowienia) zamowieniaTableView.getSelectionModel().getSelectedItem();
              ZamowieniaDAO.deleteZamowienie(zamow.getId_zamowienia());
              zamowieniaTableView.setItems(FXCollections.observableList(ZamowieniaDAO.getAllZamowienia()));
          }
      });
        // usuwanie wybranego kursu przez przycisk
      usunKursButton.setOnAction((ActionEvent) ->{
          if (kursyTableView.getSelectionModel().getSelectedItem() !=null){
              zamowioneKursyDAO kursyDAO = new zamowioneKursyDAO();
              zamowione_kursy kursy = (zamowione_kursy) kursyTableView.getSelectionModel().getSelectedItem();
              kursyDAO.deleteKurs(kursy.getNumer_inwentarzowy());
              kursyTableView.setItems(FXCollections.observableList(kursyDAO.getAllKourses()));
          }
      });
        // usuwanie wybranego auta przez przycisk
        deleteAutoButton.setOnAction((ActionEvent) ->{
            if (katalogTableView.getSelectionModel().getSelectedItem() !=null){
                katalogMarekAutoDAO katalogDAO = new katalogMarekAutoDAO();
                katalog_marek_auto katalog = (katalog_marek_auto) katalogTableView.getSelectionModel().getSelectedItem();
                katalogDAO.deleteKatalog(katalog.getId_marki());
                katalogTableView.setItems(FXCollections.observableList(katalogDAO.getAllKatalog()));
            }
        });
        // usuwanie firmy przez przycisk
        deleteSamochodButton.setOnAction((ActionEvent) ->{
            if(samochodyTableView.getSelectionModel().getSelectedItem() !=null){
                samochodyDAO samDAO = new samochodyDAO();
                samochody sam = (samochody) samochodyTableView.getSelectionModel().getSelectedItem();
                samDAO.deleteSamochod(sam.getId_samochodu());
                samochodyTableView.setItems(FXCollections.observableList(samDAO.getAllSamochody()));
            }
        });
        // Dodanie firmy do tableview
        idSamochoduColumn.setCellValueFactory(new PropertyValueFactory<samochody, Integer>("id_samochodu"));
        firmaColumn.setCellValueFactory(new PropertyValueFactory<samochody,String>("firma"));
        numerInwentarzowy1Column.setCellValueFactory(new PropertyValueFactory<samochody,zamowione_kursy>("kurs"));
        idMarki1Column.setCellValueFactory(new PropertyValueFactory<samochody,katalog_marek_auto>("katalog"));
        samochodyDAO samDAO = new samochodyDAO();
        samochodyTableView.setItems(FXCollections.observableArrayList(samDAO.getAllSamochody()));

        // Dodanie aut do tableview
        idMarkiColumn.setCellValueFactory(new PropertyValueFactory<katalog_marek_auto,Integer>("id_marki"));
        markaColumn.setCellValueFactory(new PropertyValueFactory<katalog_marek_auto, String>("marka"));
        skosztTransportColumn.setCellValueFactory(new PropertyValueFactory<katalog_marek_auto, Integer>("koszt_transportowania"));
        katalogMarekAutoDAO katDAO = new katalogMarekAutoDAO();
        katalogTableView.setItems(FXCollections.observableArrayList(katDAO.getAllKatalog()));

        // Dodanie kursów do tableview
        numerInwentarzowyColumn.setCellValueFactory(new PropertyValueFactory<zamowione_kursy,Integer>("numer_inwentarzowy"));
        ciezarLadunkuColumn.setCellValueFactory(new PropertyValueFactory<zamowione_kursy, Integer>("ciezar_ladunku"));
        odlegloscColumn.setCellValueFactory(new PropertyValueFactory<zamowione_kursy, Integer>("odleglosc"));
        idZamowienia1Column.setCellValueFactory(new PropertyValueFactory <zamowione_kursy, zamowienia>("zamowienie"));
        zamowioneKursyDAO ZamowioneKursyDAO = new zamowioneKursyDAO();
        kursyTableView.setItems(FXCollections.observableArrayList(ZamowioneKursyDAO.getAllKourses()));
        // Dodanie zamówień do tableview
        idZamowieniaColumn.setCellValueFactory(new PropertyValueFactory<zamowienia, Integer>("id_zamowienia"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<zamowienia, Date>("data_zamowienia"));
        idKlienta1Column.setCellValueFactory(new PropertyValueFactory<zamowienia, klienci>("klient"));
        zamowieniaDAO ZamowieniaDAO = new zamowieniaDAO();
        zamowieniaTableView.setItems(FXCollections.observableArrayList(ZamowieniaDAO.getAllZamowienia()));
        // Dodanie klientów do tableview
        idKlientaColumn.setCellValueFactory(new PropertyValueFactory<klienci, Integer>("id_klienta"));
        imieColumn.setCellValueFactory(new PropertyValueFactory<klienci, String>("imie"));
        nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<klienci, String>("nazwisko"));
        adresColumn.setCellValueFactory(new PropertyValueFactory<klienci, String>("adres"));
        telefonColumn.setCellValueFactory(new PropertyValueFactory<klienci, Integer>("telefon"));
        KlienciDAO klienciDAO = new KlienciDAO();
        klienciTableView.setItems(FXCollections.observableArrayList(klienciDAO.getAllKlients()));
        }
}
