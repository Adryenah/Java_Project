import Domain.Cake;
import Domain.Order;
import Repository.*;
import Service.CakeService;
import Service.OrderService;
import Testing.DomainTesting;
import Testing.RepoTesting;
import Testing.ServiceTesting;
import UI.Ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Main {

    public static CakeRepo createBaseCakeRepository() {
        CakeRepo repoC = new CakeRepo();

        Cake c1 = new Cake(1, "Albinuta", "Miere", "Lapte", 5.00, 50.99, 5);
        Cake c2 = new Cake(2, "Ciresica", "Vanilie", "Cirese", 8.00, 57.99, 10);
        Cake c3 = new Cake(3, "Negresa", "Cioco", "Cioco", 15.00, 90.99, 8);
        Cake c4 = new Cake(4, "Piersicuta", "Rom", "Piersici", 12.00, 67.99, 2);
        Cake c5 = new Cake(5, "Alba-ca-zapada", "Lapte", "Vanilie", 7.00, 30.99, 4);

        CakeRepo Crepo = new CakeRepo();
        try {
            Crepo.addItem(c1.getId(), c1);
            Crepo.addItem(c2.getId(), c2);
            Crepo.addItem(c3.getId(), c3);
            Crepo.addItem(c4.getId(), c4);
            Crepo.addItem(c5.getId(), c5);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }

        return repoC;

    }



    public static iRepository createBaseOrderRepository() {

        iRepository repo = new OrderRepo();

        Cake c1 = new Cake(1, "Albinuta", "Miere", "Lapte", 5.00, 50.99, 5);
        Cake c2 = new Cake(2, "Ciresica", "Vanilie", "Cirese", 8.00, 57.99, 10);
        Cake c3 = new Cake(3, "Negresa", "Cioco", "Cioco", 15.00, 90.99, 8);
        Cake c4 = new Cake(4, "Piersicuta", "Rom", "Piersici", 12.00, 67.99, 2);
        Cake c5 = new Cake(5, "Alba-ca-zapada", "Lapte", "Vanilie", 7.00, 30.99, 4);

        Order o1 = new Order(c1,1, "Adriana Banana", "01/05/2022", "04/05/2022");
        Order o2 = new Order(c2,2, "Denisa Babuta", "02/06/2022", "05/06/2022");
        Order o3 = new Order(c3,3, "Vlad Bolovan", "03/07/2022", "06/07/2022");
        Order o4 = new Order(c4,4, "Sergiu Bombay", "04/08/2022", "07/08/2022");
        Order o5 = new Order(c5,5, "Razvan Bombonean", "05/09/2022", "08/09/2022");

        OrderRepo Orepo = new OrderRepo();
        try {
            Orepo.addItem(o1.getId(), o1);
            Orepo.addItem(o2.getId(), o2);
            Orepo.addItem(o3.getId(), o3);
            Orepo.addItem(o4.getId(), o4);
            Orepo.addItem(o5.getId(), o5);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }
        return repo;

    }


    public static iRepository<Integer, Cake> readPropertiesCreateCakesRepo() {
        String repoType = null;
        String filePath = null;
        Properties properties = new Properties();

        try {
            InputStream is = new FileInputStream("settings.properties");

            try {
                properties.load(is);
                repoType = properties.getProperty("Repository");
                filePath = properties.getProperty("Cakes");
            } catch (Throwable var7) {
                try {
                    is.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            is.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        assert repoType != null;

        if (repoType.equals("memory")) {
            return createBaseCakeRepository();
        } else {
            return (repoType.equals("database") ? new CakeRepoDataBase(filePath) : createBaseCakeRepository());
        }
    }

    public static iRepository<Integer, Order> readPropertiesCreateOrdersRepo(iRepository<Integer, Cake> Cakes) {
        String repoType = null;
        String OrdersFilePath = null;
        Properties properties = new Properties();

        try {
            InputStream is = new FileInputStream("settings.properties");

            try {
                properties.load(is);
                repoType = properties.getProperty("Repository");
                OrdersFilePath = properties.getProperty("Orders");
            } catch (Throwable var8) {
                try {
                    is.close();
                } catch (Throwable var7) {
                    var8.addSuppressed(var7);
                }

                throw var8;
            }

            is.close();
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        if (repoType.equals("memory")) {
            return createBaseOrderRepository();
        } else {
            return (repoType.equals("database") ? new OrderRepoDataBase(OrdersFilePath) : createBaseOrderRepository());
        }
    }


    public static void main(String[] args) throws Exception {

        iRepository<Integer, Cake> Crepo = readPropertiesCreateCakesRepo();
        iRepository<Integer, Order> Orepo = readPropertiesCreateOrdersRepo(Crepo);

        DomainTesting DT = new DomainTesting();
        DT.runTestsDomain();

        RepoTesting RT = new RepoTesting();
        RT.runTestsRepository();

        ServiceTesting ST = new ServiceTesting();
        ST.runTestsService();

        CakeService cakeservice = new CakeService(Crepo);
        OrderService orderservice = new OrderService(Orepo);

        Ui ui = new Ui(orderservice,cakeservice);
        ui.mainFunction();

    }


}