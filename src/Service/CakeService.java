//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Service;

import Domain.Cake;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import Repository.iRepository;

public class CakeService {
    private iRepository<Integer, Cake> repo;

    public CakeService(iRepository<Integer, Cake> repo) {
        this.repo = repo;
    }

    public void addCake(Integer id, String cake_Name, String cakeLayer_flv, String cake_frosting, double weight, double price, int cake_layers) {
        Cake cake = new Cake(id,cake_Name,cakeLayer_flv,cake_frosting,weight,price,cake_layers);
        this.repo.addItem(id, cake);
    }

    public void deleteCake(Integer id) {
        this.repo.removeItem(id);
    }

    public void updateCake(Integer id, String cake_Name, String cakeLayer_flv, String cake_frosting, double weight, double price, int cake_layers) {
        Cake cake = new Cake(id,cake_Name,cakeLayer_flv,cake_frosting,weight,price,cake_layers);
        this.repo.updateItem(id, cake);
    }

    public Cake findCake(Integer id) {
        return (Cake)this.repo.findItemByID(id);
    }

    public Iterable<Cake> getAllItems() {
        return this.repo.getAllItems();
    }

    public Iterable<Cake> filterCakeName(String name) {
        List<Cake> cak = new ArrayList();
        Iterator var3 = this.repo.getAllItems().iterator();

        while(var3.hasNext()) {
            Cake cake = (Cake)var3.next();
            cak.add(cake);
        }

        Stream<Cake> filteredPat = cak.stream().filter((A) -> {
            return A.getCake_Name().compareTo(name) == 0;
        }).sorted((A1, A2) -> {
            return A1.getId().compareTo(A2.getId());
        });
        return filteredPat.toList();
    }

    public Iterable<Cake> filterCakeByFlavour(String flavour) {

        List<Cake> cakelist = new ArrayList<>();
        for (Cake cake : repo.getAllItems()) {
            cakelist.add(cake);
        }
        Stream<Cake> filteredPat = cakelist.stream().filter(
                (A) -> A.getCakeLayer_flv().compareTo(flavour) == 0
        ).sorted(
                (A1, A2) -> (int) A1.getId().compareTo(A2.getId())
        );
        return filteredPat.toList();
    }

//        List<Cake> cak = new ArrayList();
//        Iterator var2 = this.repo.getAllItems().iterator();
//
//        while(var2.hasNext()) {
//            Cake cake = (Cake)var2.next();
//            cak.add(cake);
//        }
//
//        Stream<Cake> filteredCakes = cak.stream().sorted((A1, A2) -> {
//            return A1.getCakeLayer_flv().compareTo(A2.getCakeLayer_flv());
//        });
//        return filteredCakes.toList();

    public iRepository<Integer, Cake> getRepo() {
        return this.repo;
    }
}

