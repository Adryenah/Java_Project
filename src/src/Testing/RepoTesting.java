package Testing;

import Domain.Cake;
import Domain.Order;
import Repository.CakeRepo;
import Repository.OrderRepo;
import org.junit.Test;
//import org.testng.annotations.Test;

public class RepoTesting {
    public RepoTesting(){}

    @Test
    public void runTestsRepository() throws Exception{
        System.out.println("Starting tests for repository............");
        this.runTestsCakeRepository();
        this.runTestsOrderRepository();
        System.out.println("Repository tests finished successfully............");
    }

    @Test
    public void runTestsCakeRepository() throws Exception{
        System.out.println("Starting tests for cake repository............");

        Cake c1=new Cake(1,"Albinuta","Miere","Lapte",5.00,50.99,5);
        Cake c2=new Cake(2,"Ciresica","Vanilie","Cirese",8.00,57.99,10);
        Cake c3=new Cake(3,"Negresa","Cioco","Cioco",15.00,90.99,8);
        Cake c4=new Cake(4,"Piersicuta","Rom","Piersici",12.00,67.99,2);
        Cake c5=new Cake(5,"Alba-ca-zapada","Lapte","Vanilie",7.00,30.99,4);

        CakeRepo Crepo = new CakeRepo();
        try{
            Crepo.addItem(c1.getId(),c1);
            Crepo.addItem(c2.getId(),c2);
            Crepo.addItem(c3.getId(),c3);
            Crepo.addItem(c4.getId(),c4);
            Crepo.addItem(c5.getId(),c5);
        }catch(RuntimeException re){
            System.out.println(re.getMessage());
        }

        try{
            Crepo.addItem(c1.getId(),c1);
        }catch (RuntimeException re)
        {
            assert re.getMessage().compareTo("Duplicate ID") == 0;
        }

        Crepo.removeItem(5);

        try{
            Crepo.removeItem(5);
        }catch (RuntimeException re)
        {
            assert re.getMessage().compareTo("Key does not exist") == 0;
        }

        try{
            Crepo.updateItem(5,c5);
        }catch (RuntimeException re)
        {
            assert re.getMessage().compareTo("Key does not exist") == 0;
        }

    }

    @Test
    public void runTestsOrderRepository() throws Exception{
        System.out.println("Starting tests for order repository............");

        Cake c1=new Cake(1,"Albinuta","Miere","Lapte",5.00,50.99,5);
        Cake c2=new Cake(2,"Ciresica","Vanilie","Cirese",8.00,57.99,10);
        Cake c3=new Cake(3,"Negresa","Cioco","Cioco",15.00,90.99,8);
        Cake c4=new Cake(4,"Piersicuta","Rom","Piersici",12.00,67.99,2);
        Cake c5=new Cake(5,"Alba-ca-zapada","Lapte","Vanilie",7.00,30.99,4);

        Order o1=new Order(c1,1,"Adriana Banana","01/05/2022","04/05/2022");
        Order o2=new Order(c2,2,"Denisa Babuta","02/06/2022","05/06/2022");
        Order o3=new Order(c3,3,"Vlad Bolovan","03/07/2022","06/07/2022");
        Order o4=new Order(c4,4,"Sergiu Bombay","04/08/2022","07/08/2022");
        Order o5=new Order(c5,5,"Razvan Bombonean","05/09/2022","08/09/2022");

        OrderRepo Orepo = new OrderRepo();

        try{
            Orepo.addItem(o1.getId(),o1);
            Orepo.addItem(o2.getId(),o2);
            Orepo.addItem(o3.getId(),o3);
            Orepo.addItem(o4.getId(),o4);
            Orepo.addItem(o5.getId(),o5);
        }catch (RuntimeException re){
            System.out.println(re.getMessage());
        }

        try{
            Orepo.addItem(o1.getId(),o1);
        }catch (RuntimeException re)
        {
            assert re.getMessage().compareTo("Duplicate ID") == 0;
        }

        Orepo.removeItem(5);

        try{
            Orepo.removeItem(5);
        }catch (RuntimeException re)
        {
            assert re.getMessage().compareTo("Key does not exist") == 0;
        }

        try{
            Orepo.updateItem(5,o5);
        }catch (RuntimeException re)
        {
            assert re.getMessage().compareTo("Key does not exist") == 0;
        }

        Orepo.updateItem(4,o5);
        assert Orepo.findItemByID(4).getOrd_place_date().compareTo("04/08/2022")==0;
        Order O3 = Orepo.findItemByID(1);
        assert O3.getId().equals(o1.getId());
    }

}
