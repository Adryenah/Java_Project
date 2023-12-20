package gui;

import Domain.Cake;
import Domain.Order;
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
    private Button showAllCakes;
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
    @FXML
    private TextField orderId;
    @FXML
    private TextField orderCakeID;
    @FXML
    private TextField placerName;
    @FXML
    private TextField placeDate;
    @FXML
    private TextField pickupDate;

    @FXML
    private Button addOrder;
    @FXML
    private Button deleteOrder;
    @FXML
    private Button updateOrder;
    @FXML
    private Button showAllOrders;
    @FXML
    private ListView<Order> OrdersListView;


    private ObservableList<Order> Orders=FXCollections.observableArrayList();
    private ObservableList<Cake> Cakes = FXCollections.observableArrayList();

    public void UpdateOrdersListView(){
        Orders.clear();
        for(Order O: Oservice.getALLItems())
            Orders.add(O);
        OrdersListView.setItems(Orders);
    }

    public void clickedAddOrder(ActionEvent actionEvent) {
        int idC = Integer.parseInt(orderCakeID.getText());
        int idO = Integer.parseInt(orderId.getText());
        String Placername = placerName.getText();
        String PlaceDate = placeDate.getText();
        String PickUpDate = pickupDate.getText();
        try {
            Oservice.addOrder(Cservice.findCake(idC), idO,Placername,PlaceDate,PickUpDate);
            UpdateOrdersListView();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }

    }

    public void clickedDeleteOrder(ActionEvent actionEvent) {
        int id = Integer.parseInt(orderId.getText());

        try {
            Oservice.deleteOrder(id);
            UpdateOrdersListView();
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void clickedUpdateOrder(ActionEvent actionEvent) {
        int idC = Integer.parseInt(orderCakeID.getText());
        int idO = Integer.parseInt(orderId.getText());
        String Placername = placerName.getText();
        String PlaceDate = placeDate.getText();
        String PickUpDate = pickupDate.getText();

        try {
            Order oldOrder=Oservice.findOrder(idO);
            Oservice.addOrder(Cservice.findCake(idC), idO,Placername,PlaceDate,PickUpDate);
            UpdateOrdersListView();
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }

    }

    public void showAllOrders(ActionEvent actionEvent) {
        Order O=OrdersListView.getSelectionModel().getSelectedItem();
        if(O!=null){
            orderCakeID.setText(String.valueOf(O.getC()));
            orderId.setText(String.valueOf(O.getId()));
            placerName.setText(String.valueOf(O.getOrd_placer_name()));
            placeDate.setText(String.valueOf(O.getOrd_place_date()));
            pickupDate.setText(String.valueOf(O.getOrd_pckup_date()));
        }
        UpdateOrdersListView();
    }

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