package UI;

import Domain.Cake;
import Repository.CakeRepoDataBase;
import Service.CakeService;
import javafx.fxml.FXML;

import javax.swing.text.html.ListView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Scanner;

public class Controller {

    private CakeService Cservice;

    @FXML
    private ListView CakesListView;

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

    public Controller(){
        this.Cservice=new CakeService(new CakeRepoDataBase("data.db"));
    }

    public void clickedAddCake(ActionEvent actionEvent){
        try{
            int id=Integer.parseInt(idCake_TextField.getText());
            String Cname= cakeName_TextField.getText();
            String ClyrFlv=cakeLayerFlv_TextField.getText();
            String CFrost=cakeFrosting_TextField.getText();
            double CW=Double.parseDouble(cakeWeight_TextField.getText());
            double CP=Double.parseDouble(cakePrice_TextField.getText());
            int CL=Integer.parseInt(cakeLayers_TextField.getText());
            Cservice.addCake(id, Cname, ClyrFlv, CFrost, CW, CP, CL);

            System.out.println("Cake added successfully!");

        } catch (RuntimeException re) {
            if (re.getMessage().compareTo("This") == 0)
                System.out.println("Use a new ID for this cake, or update an existing one");
            if (re.getMessage().compareTo("ID not available!") == 0)
                System.out.println("Please use an id greater then 0");
        }

    }

    private void validateId(Integer id) {
        if (id < 0)
            throw new RuntimeException("ID not available!");
    }
    public void clickDeleteCake(ActionEvent actionEvent){
        try {
            System.out.print("Id of the cake to delete: ");
            int id=Integer.parseInt(idCake_TextField.getText());
            this.validateId(id);

            Cservice.deleteCake(id);
            System.out.println("Cake deleted successfully!");
        } catch (RuntimeException re) {
            if (re.getMessage().compareTo("ID not available!") == 0)
                System.out.println("Please use an id greater then 0");
            if (re.getMessage().compareTo("ID does not exist") == 0)
                System.out.println("This cake does not exist, consider adding it");
        }
    }

    public void clickUpdateCake(ActionEvent actionEvent) {
        try {
            System.out.print("cake's id: ");
            int id = Integer.parseInt(idCake_TextField.getText());
            this.validateId(id);

            Cake C = Cservice.findCake(id);
            String Cname= cakeName_TextField.getText();
            String ClyrFlv=cakeLayerFlv_TextField.getText();
            String CFrost=cakeFrosting_TextField.getText();
            double CW=Double.parseDouble(cakeWeight_TextField.getText());
            double CP=Double.parseDouble(cakePrice_TextField.getText());
            int CL=Integer.parseInt(cakeLayers_TextField.getText());
            Cservice.addCake(id, Cname, ClyrFlv, CFrost, CW, CP, CL);

            System.out.println("Cake added successfully!");

        } catch (RuntimeException re) {
            if (re.getMessage().compareTo("This") == 0)
                System.out.println("Use a new ID for this cake, or update an existing one");
            if (re.getMessage().compareTo("ID not available!") == 0)
                System.out.println("Please use an id greater then 0");
        }

    }

    public void showAllCakes(ActionEvent actionEvent) {

        Iterable<Cake> iterd = Cservice.getAllItems();
        Iterator<Cake> i = iterd.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }




}
