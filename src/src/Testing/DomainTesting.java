package Testing;

import Domain.Cake;
import Domain.Order;
import org.junit.Test;
//import org.testng.annotations.Test;

public class DomainTesting {
    public DomainTesting(){}

    @Test
    public void runTestsDomain(){

        runTestsCakes();
        runTestsOrders();
    }

    @Test
    public void runTestsCakes(){
        Cake C=new Cake(1);
        assert C.getId()==1;
        assert C.getCake_Name().compareTo("")==0;
        assert C.getCakeLayer_flv().compareTo("")==0;
        assert C.getCake_frosting().compareTo("")==0;
        assert C.getWeight()==0;
        assert C.getPrice()==0;
        assert C.getCake_layers()==0;

        Cake C1=new Cake(2,"Albinuta","banane","lapte",20.50,200.99,6);
        assert C1.getId()==2;
        assert C1.getCake_Name().compareTo("Albinuta")==0;
        assert C1.getCakeLayer_flv().compareTo("banane")==0;
        assert C1.getCake_frosting().compareTo("lapte")==0;
        assert C1.getWeight()==20.50;
        assert C1.getPrice()==200.99;
        assert C1.getCake_layers()==6;

        C.setCake_Name("Albinuta");
        assert C.getCake_Name().compareTo("Albinuta")==0;

        C.setCakeLayer_flv("banane");
        assert C.getCakeLayer_flv().compareTo("banane")==0;

        C.setCake_frosting("lapte");
        assert C.getCake_frosting().compareTo("lapte")==0;

        C.setWeight(20.50);
        assert C.getWeight()==20.50;

        C.setPrice(200.99);
        assert C.getPrice()==200.99;

        C.setCake_layers(6);
        assert C.getCake_layers()==6;

        assert !C.equals(C1);
        C.setId(2);

        C.setId(C1.getId());
        C.setCake_Name(C1.getCake_Name());
        C.setCakeLayer_flv(C1.getCakeLayer_flv());
        C.setCake_frosting(C1.getCake_frosting());
        C.setWeight(C1.getWeight());
        C.setPrice(C1.getPrice());
        C.setCake_layers(C1.getCake_layers());

        int v1=C.compareTo(C1);
        assert v1==0;
        boolean v2=C.equals(C1);
        assert v2;

        int v3=C1.hashCode();
        String V4=C1.toString();
        assert V4.compareTo("Cake{id='2', cake_Name='Albinuta', cakeLayer_flv='banane', cake_frosting='lapte', weight=20.50, price=200.99, cake_layers=6}")==0;

    }

    @Test
    public void runTestsOrders(){

        Order O =new Order(1);
        assert O.getId()==1;
        assert O.getOrd_placer_name().compareTo("")==0;
        assert O.getOrd_place_date().compareTo("")==0;
        assert O.getOrd_pckup_date().compareTo("")==0;

        Cake C1=new Cake(2,"Albinuta","banane","lapte",20.50,200.99,6);
        Order O1=new Order(C1,2,"Maricica","01/05/2023","02/06/2023");
        assert O1.getId()==2;
        assert O1.getOrd_placer_name().compareTo("Maricica")==0;
        assert O1.getOrd_place_date().compareTo("01/05/2023")==0;
        assert O1.getOrd_pckup_date().compareTo("02/06/2023")==0;

        O.setOrd_placer_name(O1.getOrd_placer_name());
        O.setOrd_place_date(O1.getOrd_place_date());
        O.setOrd_pckup_date(O1.getOrd_pckup_date());

        boolean o=O.equals(O1);
        assert !o;
        O.setId(O1.getId());
        o=O.equals(O1);
        assert o;

        String V1=O1.toString();
        assert V1.compareTo("Order{c=Cake{id='2', cake_Name='Albinuta', cakeLayer_flv='banane', cake_frosting='lapte', weight=20.50, price=200.99, cake_layers=65}, id=2, ord_placer_name='Maricica', ord_place_date='01/05/2023', ord_pckup_date='02/06/2023'}")==0;

    }

}
