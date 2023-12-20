//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Service;

import Domain.Cake;
import Domain.Order;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import Repository.iRepository;

public class OrderService {
    private iRepository<Integer, Order> repo;

    public OrderService(iRepository<Integer, Order> repo) {
        this.repo = repo;
    }

    public void addOrder(Cake cake,Integer id, String ord_placer_name, String ord_place_date, String ord_pckup_date) {
        Order O = new Order( cake,id, ord_placer_name, ord_place_date, ord_pckup_date);
        this.repo.addItem(id, O);
    }

    public void deleteOrder(Integer id) {
        this.repo.removeItem(id);
    }

    public void updateOrder(Cake cake,Integer id, String ord_placer_name, String ord_place_date, String ord_pckup_date) {
        Order O = new Order(cake,id, ord_placer_name, ord_place_date, ord_pckup_date);
        this.repo.updateItem(id, O);
    }

    public Order findOrder(Integer id) {
        return (Order)this.repo.findItemByID(id);
    }

    public Iterable<Order> filterOrdersbyPlaceDate(String date) {
        List<Order> orders = new ArrayList();
        Iterator var3 = this.repo.getAllItems().iterator();

        while(var3.hasNext()) {
            Object A = var3.next();
            orders.add((Order)A);
        }

        Stream<Order> filteredOrders = orders.stream().filter((Ax) -> {
            return Ax.getOrd_place_date().compareTo(date) == 0;
        }).sorted((A1, A2) -> {
            return A1.getOrd_place_date().compareTo(A2.getOrd_place_date());
        });
        return filteredOrders.toList();
    }

    public Iterable<Order> filterOrderByPickupDate(String date) {
        List<Order> orders = new ArrayList();
        Iterator var3 = this.repo.getAllItems().iterator();

        while(var3.hasNext()) {
            Object O = var3.next();
            orders.add((Order)O);
        }

        Stream<Order> filteredAppts = orders.stream().filter((Ax) -> {
            return Ax.getOrd_pckup_date().compareTo(date) == 0;
        }).sorted((A1, A2) -> {
            return A1.getOrd_pckup_date().compareTo(A2.getOrd_pckup_date());
        });
        return filteredAppts.toList();
    }


    public Iterable<Order> filterOrderByCake(Cake cake) {
        List<Order> orders = new ArrayList();
        Iterator var3 = this.repo.getAllItems().iterator();

        while(var3.hasNext()) {
            Object A = var3.next();
            orders.add((Order)A);
        }

        Stream<Order> filteredOrders = orders.stream().filter((Ax) -> {
            return Ax.getC().compareTo(cake) == 0;
        }).sorted((A1, A2) -> {
            return A1.getOrd_place_date().compareTo(A2.getOrd_place_date());
        });
        return filteredOrders.toList();
    }



    public Iterable<Order> getALLItems() {
        return this.repo.getAllItems();
    }

    public iRepository<Integer, Order> getRepo() {
        return this.repo;
    }

    public Iterable<Order> orderOrdersByID() {
        List<Order> orders = new ArrayList();
        Iterator var2 = this.repo.getAllItems().iterator();

        while(var2.hasNext()) {
            Object A = var2.next();
            orders.add((Order)A);
        }

        Stream<Order> filteredOrders = orders.stream().sorted((A1, A2) -> {
            return A1.getId().compareTo(A2.getId());
        });
        return filteredOrders.toList();
    }

}
