package Domain;

import java.io.Serializable;
import java.util.Objects;

public class Cake implements Entity<Integer>, Comparable<Cake>, Serializable {
    Integer id;
    private String cake_Name;
    private String cakeLayer_flv;
    private String cake_frosting;
    private double weight;
    private double price;
    private int cake_layers;

    @Override
    public String toString() {
        return "Cake{" +
                "id='" + id + '\'' +
                ", cake_Name='" + cake_Name + '\'' +
                ", cakeLayer_flv='" + cakeLayer_flv + '\'' +
                ", cake_frosting='" + cake_frosting + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", cake_layers=" + cake_layers +
                '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCake_Name(), getCakeLayer_flv(), getCake_frosting(), getWeight(), getPrice(), getCake_layers());
    }

    public Cake(Integer id, String cake_Name, String cakeLayer_flv, String cake_frosting, double weight, double price, int cake_layers) {
        this.id = id;
        this.cake_Name = cake_Name;
        this.cakeLayer_flv = cakeLayer_flv;
        this.cake_frosting = cake_frosting;
        this.weight = weight;
        this.price = price;
        this.cake_layers = cake_layers;
    }

    public Cake(Integer id) {
        this.id = id;
        this.cake_Name = "";
        this.cakeLayer_flv = "";
        this.cake_frosting = "";
        this.weight = 00.00;
        this.price = 00.00;
        this.cake_layers = 0;
    }

    public void setCake_Name(String cake_Name) {
        this.cake_Name = cake_Name;
    }

    public void setCakeLayer_flv(String cakeLayer_flv) {
        this.cakeLayer_flv = cakeLayer_flv;
    }

    public void setCake_frosting(String cake_frosting) {
        this.cake_frosting = cake_frosting;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCake_layers(int cake_layers) {
        this.cake_layers = cake_layers;
    }

    public String getCake_Name() {
        return cake_Name;
    }

    public String getCakeLayer_flv() {
        return cakeLayer_flv;
    }

    public String getCake_frosting() {
        return cake_frosting;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public int getCake_layers() {
        return cake_layers;
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id=id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o.getClass()!=Cake.class) return false;
        Cake c= (Cake) o;
        if(Objects.equals(c.id,this.id)) return true;
        return false;
    }

    @Override
    public int compareTo(Cake o) {
        return this.cake_Name.compareTo(o.cake_Name);
    }
}
