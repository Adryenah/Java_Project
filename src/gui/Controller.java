package gui;

import Domain.Cake;
import Service.CakeService;
import Service.OrderService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;


public class Controller {
    private OrderService Oservice;

    private CakeService Cservice;

    public Controller(OrderService oservice, CakeService cservice) {
        Oservice = oservice;
        Cservice = cservice;
    }

    @FXML
    private Button addCake;
    @FXML
    private Button deleteCake;
    @FXML
    private Button updateCake;
    @FXML
    private Button viewCakes;
    @FXML
    private ListView<Cake> CakesListView;

    @FXML
    private TextField idCake_TextField;

    @FXML
    private TextField cakeName_TextField;

    @FXML
    private TextField cakeLayerFlv_TextField;

    @FXML
    private TextField cakeFrosting_TextField;

    @FXML
    private TextField cakeWeight_TextField;

    @FXML
    private TextField cakePrice_TextField;

    @FXML
    private TextField cakeLayers_TextField;

    private ObservableList<Cake> Cakes = FXCollections.observableArrayList();

    public void UpdateCakesListView(){
        Cakes.clear();
        for (Cake C: Cservice.getAllItems())
            Cakes.add(C);
        CakesListView.setItems(Cakes);
    }

    public void clickedAddCake(ActionEvent actionEvent) {
        int id = Integer.parseInt(idCake_TextField.getText());
        String Cname = cakeName_TextField.getText();
        String ClyrFlv = cakeLayerFlv_TextField.getText();
        String CFrost = cakeFrosting_TextField.getText();
        double CW = Double.parseDouble(cakeWeight_TextField.getText());
        double CP = Double.parseDouble(cakePrice_TextField.getText());
        int CL = Integer.parseInt(cakeLayers_TextField.getText());
        try {

            Cservice.addCake(id, Cname, ClyrFlv, CFrost, CW, CP, CL);
            UpdateCakesListView();
            System.out.println("Cake added successfully!");

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }

    }

    private void validateId(Integer id) {
        if (id < 0)
            throw new RuntimeException("ID not available!");
    }

    public void clickedDeleteCake(ActionEvent actionEvent) {
        int id = Integer.parseInt(idCake_TextField.getText());

        try {

            Cservice.deleteCake(id);
            UpdateCakesListView();
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void clickedUpdateCake(ActionEvent actionEvent) {
        int id = Integer.parseInt(idCake_TextField.getText());
        String Cname = cakeName_TextField.getText();
        String ClyrFlv = cakeLayerFlv_TextField.getText();
        String CFrost = cakeFrosting_TextField.getText();
        double CW = Double.parseDouble(cakeWeight_TextField.getText());
        double CP = Double.parseDouble(cakePrice_TextField.getText());
        int CL = Integer.parseInt(cakeLayers_TextField.getText());

        try {
            Cake oldCake = Cservice.findCake(id);
            Cservice.updateCake(id, Cname, ClyrFlv, CFrost, CW, CP, CL);
            UpdateCakesListView();
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }

    }

    public void showAllCakes(ActionEvent actionEvent) {
        Cake C=CakesListView.getSelectionModel().getSelectedItem();
        if(C!=null){
            idCake_TextField.setText(String.valueOf(C.getId()));
            cakeName_TextField.setText(String.valueOf(C.getCake_Name()));
            cakeLayerFlv_TextField.setText(String.valueOf(C.getCakeLayer_flv()));
            cakeFrosting_TextField.setText(String.valueOf(C.getCake_frosting()));
            cakeWeight_TextField.setText(String.valueOf(C.getWeight()));
        }
        UpdateCakesListView();
    }
}