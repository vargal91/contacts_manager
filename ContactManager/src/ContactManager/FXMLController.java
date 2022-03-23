package ContactManager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController implements Initializable {

    DB ab = new DB();
    @FXML
    private TextField txtNev;

    @FXML
    private TextField txtCim;

    @FXML
    private TextField txtElerhetoseg;

    @FXML
    private TableView<Szemely> tblSzemely;

    @FXML
    private TableColumn<Szemely, String> oSzemely;

    @FXML
    private TableView<Cim> tblCim;

    @FXML
    private TableColumn<Cim, String> oCim;

    @FXML
    private TableView<Elerhetoseg> tblElerhetoseg;

    @FXML
    private TableColumn<Elerhetoseg, String> oElerhetoseg;

    @FXML
    void cimhozzaad() {
        int index = tblSzemely.getSelectionModel().getSelectedIndex();
        int id = tblSzemely.getItems().get(index).getSzemelyid();
        if (id == -1) {
            Panel.hiba("Hiba", "Nem választottál nevet");
            return;
        }
        String s = txtCim.getText().trim();
        if (s.length() < 1 || s.length() > 50) {
            Panel.hiba("Hiba", "Nem adtál meg címet");
            txtCim.requestFocus();
            return;
        }
        ab.cbeir(s, id);
        ab.cbeolvas(tblCim.getItems(), id);
    }

    @FXML
    void elerhozzaad() {
        int index = tblCim.getSelectionModel().getSelectedIndex();
        int id = tblCim.getItems().get(index).getCimid();
        if (id == -1) {
            Panel.hiba("Hiba", "Nem választottál címet");
            return;
        }
        String s = txtElerhetoseg.getText().trim();
        if (s.length() < 1 || s.length() > 50) {
            Panel.hiba("Hiba", "Nem adtál meg elérhetőséget");
            txtElerhetoseg.requestFocus();
            return;
        }
        ab.ebeir(s, id);
        ab.ebeolvas(tblElerhetoseg.getItems(), id);
    }

    @FXML
    void mezotorles() {
        ab.ebeolvas(tblElerhetoseg.getItems(), -1);
        ab.cbeolvas(tblCim.getItems(), -1);
        txtNev.clear();
        txtCim.clear();
        txtElerhetoseg.clear();
    }

    @FXML
    void nevhozzad() {
        String s = txtNev.getText().trim();
        if (s.length() < 1 || s.length() > 50) {
            Panel.hiba("Hiba", "Név nem lett megadva, kérlek add meg a "
                    + "megrendelő nevét!");
            txtNev.requestFocus();
            return;
        }
        ab.szbeir(s);
        beolvas();
    }

    @FXML
    void ctorles() {
        int index1 = tblSzemely.getSelectionModel().getSelectedIndex();
        int sz = tblSzemely.getItems().get(index1).getSzemelyid();
        int index = tblCim.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return;
        }
        if (!Panel.igennem("Törlés", "Figyelem, ha törli ezt a címet, akkor a hozzátartozó elérhetőség is törlődik!\nBiztos benne?")) {
            return;
        }
        int id = tblCim.getItems().get(index).getCimid();
        int sor = ab.ctorol(id);
        if (sor > 0) {
            ab.cbeolvas(tblCim.getItems(), sz);
            ab.ebeolvas(tblElerhetoseg.getItems(), -1);
        }
    }

    @FXML
    void etorles() {
        int index1 = tblCim.getSelectionModel().getSelectedIndex();
        int sz = tblCim.getItems().get(index1).getCimid();
        int index = tblElerhetoseg.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return;
        }
        if (!Panel.igennem("Törlés", "Biztosan törölni szeretné ezt az elérhetőséget?")) {
            return;
        }
        int id = tblElerhetoseg.getItems().get(index).getAz();
        int sor = ab.etorol(id);
        if (sor > 0) {
            ab.ebeolvas(tblElerhetoseg.getItems(), sz);
        }
    }

    @FXML
    void njtorles() {
        int index = tblSzemely.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return;
        }
        if (!Panel.igennem("Törlés", "Biztosan törölni szeretné ezt a névjegyet?\nMinden hozzátartozó adat el fog tűnni! ")) {
            return;
        }
        int id = tblSzemely.getItems().get(index).getSzemelyid();
        int sor = ab.njtorol(id);
        if (sor > 0) {
            beolvas();
            ab.cbeolvas(tblCim.getItems(), -1);
            ab.ebeolvas(tblElerhetoseg.getItems(), -1);
        }
    }

    @FXML
    void nevmodosit() {
        int index = tblSzemely.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return;
        }
        int id = tblSzemely.getItems().get(index).getSzemelyid();
        String nev = txtNev.getText().trim();
        if (nev.length() < 1 || nev.length() > 50) {
            Panel.hiba("Hiba", "Név nem lett megadva, kérlek add meg a nevet!");
            txtNev.requestFocus();
            return;
        }

        ab.nevmodosit(nev, id);
        beolvas();
        ab.ebeolvas(tblElerhetoseg.getItems(), -1);
        Integer szuro = tblSzemely.getItems().get(index).getSzemelyid();
        ab.cbeolvas(tblCim.getItems(), szuro);

    }

    @FXML
    void cimmodosit() {
        int index = tblCim.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return;
        }
        int id = tblCim.getItems().get(index).getCimid();
        String cim = txtCim.getText().trim();
        if (cim.isEmpty()) {
            Panel.hiba("Hiba", "Cím nem lett megadva, kérlek add meg a címet!");
            txtCim.requestFocus();
            return;
        }

        ab.cimmodosit(cim, id);
        beolvas();
        Integer szuro = tblCim.getItems().get(index).getSzemelyid();
        ab.cbeolvas(tblCim.getItems(), szuro);
    }

    @FXML
    void elermodosit() {
        int index = tblElerhetoseg.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return;
        }
        int id = tblElerhetoseg.getItems().get(index).getAz();
        String elerhetoseg = txtElerhetoseg.getText().trim();
        if (elerhetoseg.isEmpty()) {
            Panel.hiba("Hiba", "Elérhetőség nem lett megadva, kérlek add meg az elérhetőséget!");
            txtElerhetoseg.requestFocus();
            return;
        }

        ab.elermodosit(elerhetoseg, id);
        beolvas();
        Integer szuro = tblElerhetoseg.getItems().get(index).getCimid();
        ab.ebeolvas(tblElerhetoseg.getItems(), szuro);
    }

    public void beolvas() {
        ab.szbeolvas(tblSzemely.getItems());
    }

    private void clekerdez() {
        int i = tblSzemely.getSelectionModel().getSelectedIndex();
        Integer szuro = tblSzemely.getItems().get(i).getSzemelyid();
        ab.cbeolvas(tblCim.getItems(), szuro);
        ab.ebeolvas(tblElerhetoseg.getItems(), -1);
    }

    private void elekerdez() {
        int f = tblCim.getSelectionModel().getSelectedIndex();
        if (f == -1) {
            return;
        }
        Integer szuro = tblCim.getItems().get(f).getCimid();
        ab.ebeolvas(tblElerhetoseg.getItems(), szuro);

    }

    private void sztablabol(int i) {
        if (i == -1) {
            return;
        }
        Szemely sz = tblSzemely.getItems().get(i);
        txtNev.setText("" + sz.getNev());
        clekerdez();
        elekerdez();
        txtCim.clear();
        txtElerhetoseg.clear();

    }

    private void ctablabol(int i) {
        if (i == -1) {
            return;
        }
        Cim c = tblCim.getItems().get(i);
        txtCim.setText("" + c.getCim());
        txtElerhetoseg.clear();
        elekerdez();
    }

    private void etablabol(int i) {
        if (i == -1) {
            return;
        }
        Elerhetoseg c = tblElerhetoseg.getItems().get(i);
        txtElerhetoseg.setText("" + c.getElerhetoseg());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oSzemely.setCellValueFactory(new PropertyValueFactory<>("nev"));
        oCim.setCellValueFactory(new PropertyValueFactory<>("cim"));
        oElerhetoseg.setCellValueFactory(new PropertyValueFactory<>("elerhetoseg"));
        beolvas();
        tblSzemely.getSelectionModel().selectedIndexProperty().addListener(
                (o, regi, uj) -> sztablabol(uj.intValue()));
        tblCim.getSelectionModel().selectedIndexProperty().addListener(
                (o, regi, uj) -> ctablabol(uj.intValue()));
        tblElerhetoseg.getSelectionModel().selectedIndexProperty().addListener(
                (o, regi, uj) -> etablabol(uj.intValue()));
    }

}

