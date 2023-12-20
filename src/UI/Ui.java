package UI;

import Domain.Cake;
import Domain.Order;
import Service.CakeService;
import Service.OrderService;

import java.util.Iterator;
import java.util.Scanner;

public class Ui {

    private OrderService Oservice;
    private CakeService Cservice;

    public Ui(OrderService Oservice, CakeService Cservice) {
        this.Oservice = Oservice;
        this.Cservice = Cservice;
    }

    private void validateId(Integer id) {
        if (id < 0)
            throw new RuntimeException("ID not available!");
    }

    private void validateDate(String date) {
        String[] parts = date.split("/");
        if (parts.length < 3)
            throw new RuntimeException("Invalid Year");

        int day = Integer.parseInt(parts[0]);
        String month = parts[1];
        int year = Integer.parseInt(parts[2]);

        if (year < 2021 || year > 2100) throw new RuntimeException("Invalid Year");

        int ok = 0;

        if (month.compareTo("01") == 0) ok = 1;
        if (month.compareTo("02") == 0) ok = 1;
        if (month.compareTo("03") == 0) ok = 1;
        if (month.compareTo("04") == 0) ok = 1;
        if (month.compareTo("05") == 0) ok = 1;
        if (month.compareTo("06") == 0) ok = 1;
        if (month.compareTo("07") == 0) ok = 1;
        if (month.compareTo("08") == 0) ok = 1;
        if (month.compareTo("09") == 0) ok = 1;
        if (month.compareTo("10") == 0) ok = 1;
        if (month.compareTo("11") == 0) ok = 1;
        if (month.compareTo("12") == 0) ok = 1;

        if (ok == 0) throw new RuntimeException("Invalid Month");

        if (day < 1) throw new RuntimeException("Invalid Day Number");

        if (day > 29) throw new RuntimeException("Invalid Day");


    }

    public void menu() {
        System.out.println("---------------------");
        System.out.println("1.Add Cake.\n2.Delete Cake.\n3.Update a Cake.\n4.See all Cakes.");
        System.out.println("---------------------");
        System.out.println("5.Place an order.\n6.Cancel an order.\n7.Update an order.\n8.See all orders.");
        System.out.println("---------------------");
        System.out.println("9.Filter by Cakes in Orders.\n10.Filter by Cake's Layers Flavour.\n11.Filter by Cakes name.\n12.Filter by Place Date.\n13.Filter by Pick-up Date.");

        System.out.println("20.Exit\n");
    }

    public void showAllCakes() {

        Iterable<Cake> iterd = Cservice.getAllItems();
        Iterator<Cake> i = iterd.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }

    public void showAllOrders() {
        Iterable<Order> iterd = Oservice.getALLItems();
        Iterator<Order> i = iterd.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }

    public void addCake() {

        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Cake's id: ");
        Integer id = sc.nextInt();
        try {
            this.validateId(id);
            System.out.print("New cake name: ");
            String NWCake_name = sc.next();
            System.out.print("The flavour of the layers: ");
            String NWckLayer_flv = sc.next();
            System.out.print("The frosting: ");
            String NWck_frost = sc.next();
            System.out.print("Cake's new Weight:");
            double NWweight = sc.nextDouble();
            System.out.print("Cake's new Price:");
            double NWprice = sc.nextDouble();
            System.out.print("Cake's number of layers:");
            int NWck_layers = sc.nextInt();

            Cservice.addCake(id, NWCake_name, NWckLayer_flv, NWck_frost, NWweight, NWprice, NWck_layers);

            System.out.println("Cake added successfully!");

        } catch (RuntimeException re) {
            if (re.getMessage().compareTo("This") == 0)
                System.out.println("Use a new ID for this cake, or update an existing one");
            if (re.getMessage().compareTo("ID not available!") == 0)
                System.out.println("Please use an id greater then 0");
        }

    }

    public void createOrder() {

        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {

            System.out.print("Order's id: ");
            Integer id = sc.nextInt();
            this.validateId(id);

            System.out.print("Cake's id: ");
            Integer idc = sc.nextInt();
            this.validateId(idc);

            Cake cake= Cservice.findCake(idc);

            System.out.print("Order placed by: ");
            String NWorderPLname = sc.next();

            System.out.print("Date the order was placed: ");
            String datePlaced = sc.next();
            this.validateDate(datePlaced);

            System.out.print("Date the order is due to be picked-up: ");
            String datePckUP = sc.next();
            this.validateDate(datePckUP);

            Oservice.addOrder(cake,id, NWorderPLname, datePlaced, datePckUP);

            System.out.println("Order placed successfully!");

        } catch (RuntimeException re) {
            if (re.getMessage().compareTo("This ID already exists!") == 0)
                System.out.println("Use a new ID for this order, or update an existing one");
            if (re.getMessage().compareTo("ID not available!") == 0)
                System.out.println("Please use an id greater then 0");
            if (re.getMessage().compareTo("Invalid Year") == 0 || re.getMessage().compareTo("Invalid Month") == 0 || re.getMessage().compareTo("Invalid Day Number") == 0)
                System.out.println("Please introduce a date of the format : day/month/year");
            if (re.getMessage().compareTo("ID does not exist") == 0)
                System.out.println("Please add the Cake first and then create the order");
        }


    }

    public void deleteCake() {

        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.print("Id of the cake to delete: ");
            Integer id = sc.nextInt();
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

    public void cancelOrder() {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.print("Id of the order to delete: ");
            Integer id = sc.nextInt();
            this.validateId(id);

            Oservice.deleteOrder(id);
            System.out.println("Order canceled successfully!");
        } catch (RuntimeException re) {
            if (re.getMessage().compareTo("ID does not exist") == 0)
                System.out.println("This order does not exist, consider adding it");
            if (re.getMessage().compareTo("ID not available!") == 0)
                System.out.println("Please use an id greater then 0");
        }

    }

    public void updateCake() {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");

        try {
            System.out.print("cake's id: ");
            Integer id = sc.nextInt();
            this.validateId(id);

            Cake C = Cservice.findCake(id);
            System.out.print("New cake's name: ");
            String name = sc.next();
            System.out.print("New flavour for the layers: ");
            String NWLayer_flv = sc.next();
            System.out.print("New frosting: ");
            String NWfrost = sc.next();
            System.out.print("Cake's new Weight:");
            double NW_weight = sc.nextDouble();
            System.out.print("Cake's new Price:");
            double NW_price = sc.nextDouble();
            System.out.print("Cake's number of layers:");
            int NWlayers = sc.nextInt();

            Cservice.updateCake(id, name, NWLayer_flv, NWfrost, NW_weight, NW_price, NWlayers);
            System.out.println("The cake was updated successfully!");

        } catch (RuntimeException re) {
            if (re.getMessage().compareTo("ID does not exist") == 0)
                System.out.println("This cake does not exist, consider adding it");
            if (re.getMessage().compareTo("ID not available!") == 0)
                System.out.println("Please use an id greater then 0");
        }

    }

    public void updateOrder() {

        Scanner sc = new Scanner(System.in).useDelimiter("\n");

        try {

            System.out.print("The ID of the cake you want: ");
            Integer idc = sc.nextInt();
            this.validateId(idc);

            System.out.print("Order's id: ");
            Integer id = sc.nextInt();
            this.validateId(id);

            System.out.print("Cake's id: ");
            Integer idcake = sc.nextInt();

            Cake C = Cservice.findCake(idcake);

            System.out.print("Order placed by: ");
            String NWorderPLname = sc.next();

            System.out.print("Date the order was placed: ");
            String datePlaced = sc.next();
            this.validateDate(datePlaced);

            System.out.print("Date the order is due to be picked-up: ");
            String datePckUP = sc.next();
            this.validateDate(datePckUP);

            Oservice.addOrder(C,id, NWorderPLname, datePlaced, datePckUP);

            System.out.println("Order updated successfully!");
        } catch (RuntimeException re) {
            if (re.getMessage().compareTo("Key does not exist") == 0)
                System.out.println("The appointment or the patient needed do not exist, consider adding them first");
            if (re.getMessage().compareTo("ID not available!") == 0)
                System.out.println("Please use an id bigger then 0");
            if (re.getMessage().compareTo("Invalid Year") == 0 || re.getMessage().compareTo("Invalid Month") == 0 || re.getMessage().compareTo("Invalid Day Number") == 0)
                System.out.println("Please introduce a date of the format : day month year");
        }

    }

    public void filterByCake() {
        Scanner sc = (new Scanner(System.in)).useDelimiter("\n");
        System.out.print("Input Cake Id: ");
        Integer idC = sc.nextInt();

        try {
            Cake cake = this.Cservice.findCake(idC);
            System.out.println("Ordered by date, all the Orders with " + cake.getCake_Name());
            int ok = 0;


            for (Iterator var5 = this.Oservice.filterOrderByCake(cake).iterator(); var5.hasNext(); ok = 1) {
                Order A = (Order) var5.next();
                System.out.println(A);
            }



            if (ok == 0) {
                System.out.println("No Orders to show!");
            }
        } catch (RuntimeException var7) {
            if (var7.getMessage().compareTo("Key does not exist") == 0) {
                System.out.println("Please add the Cake first");
            } else {
                System.out.println(var7.getMessage());
            }
        }

    }

    private void filterByCakeName() {
        Scanner sc = (new Scanner(System.in)).useDelimiter("\n");
        System.out.print("Input cake name: ");
        String nameCK = sc.next();
        int ok = 0;

        for (Iterator var4 = this.Cservice.filterCakeName(nameCK).iterator(); var4.hasNext(); ok = 1) {
            Cake C = (Cake) var4.next();
            System.out.println(C);
        }

        if (ok == 0) {
            System.out.println("No Cakes to show!");
        }
    }


    private void filterCakeByLyrFlavour() {

       Scanner sc=new Scanner(System.in).useDelimiter("\n");
       System.out.print("Input layers flavour: ");
       String flvLyr=sc.next();

       int ok=0;
       for(Cake C: Cservice.filterCakeByFlavour(flvLyr)){
            System.out.println(C);
            ok=1;
       }
       if(ok==0){
           System.out.println("No cakes to display");
       }

    }

    public void filterByPlaceDate() {
        Scanner sc = (new Scanner(System.in)).useDelimiter("\n");

        try {
            System.out.print("Input date: ");
            String date = sc.next();
            this.validateDate(date);
            int ok = 0;
            System.out.println("All the Orders which are placed on " + date);

            for(Iterator var4 = this.Oservice.filterOrdersbyPlaceDate(date).iterator(); var4.hasNext(); ok = 1) {
                Order O = (Order) var4.next();
                System.out.println(O);
            }

            if (ok==0) {
                System.out.println("No orders to show!");
            }
        } catch (RuntimeException var6) {
            if (var6.getMessage().compareTo("Invalid Year") == 0 || var6.getMessage().compareTo("Invalid Month") == 0 || var6.getMessage().compareTo("Invalid Day Number") == 0|| var6.getMessage().compareTo("Invalid Day") == 0) {
                System.out.println("Please introduce a date of the format :day/month/year");
            }
        }

    }

    public void filterByPickupDate() {
        Scanner sc = (new Scanner(System.in)).useDelimiter("\n");

        try {
            System.out.print("Input date: ");
            String date = sc.next();
            this.validateDate(date);
            int ok = 0;
            System.out.println("All the Orders which are due to " + date);

            for(Iterator var4 = this.Oservice.filterOrderByPickupDate(date).iterator(); var4.hasNext(); ok = 1) {
                Order O = (Order) var4.next();
                System.out.println(O);
            }

            if (ok==0) {
                System.out.println("No orders to show!");
            }
        } catch (RuntimeException var6) {
            if (var6.getMessage().compareTo("Invalid Year") == 0 || var6.getMessage().compareTo("Invalid Month") == 0 || var6.getMessage().compareTo("Invalid Day Number") == 0|| var6.getMessage().compareTo("Invalid Day") == 0) {
                System.out.println("Please introduce a date of the format :day/month/year");
            }
        }

    }


        public void mainFunction(){

        while(true) {
            menu();
            Scanner sc = new Scanner(System.in);
            System.out.println("Input your option: ");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    addCake();
                    break;
                case 2:
                    deleteCake();
                    break;
                case 3:
                    updateCake();
                    break;
                case 4:
                    showAllCakes();
                    break;
                case 5:
                    createOrder();
                    break;
                case 6:
                    cancelOrder();
                    break;
                case 7:
                    updateOrder();
                    break;
                case 8:
                    showAllOrders();
                    break;
                case 9:
                    filterByCake();
                    break;
                case 10:
                    filterCakeByLyrFlavour();
                    break;
                case 11:
                    filterByCakeName();
                    break;
                case 12:
                    filterByPlaceDate();
                    break;
                case 13:
                    filterByPickupDate();
                    break;
                case 20:
                    break;
            }
            if(option==21)
                break;
        }


    }

}
