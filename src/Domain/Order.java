package Domain;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Entity<Integer>,Comparable<Order>, Serializable {

    private Cake c;
    Integer id;
    private String ord_placer_name;
    private String ord_place_date;
    private String ord_pckup_date;


    public Cake getC() {
        return c;
    }

    public void setC(Cake c) {
        this.c = c;
    }

    public String getOrd_placer_name() {
        return ord_placer_name;
    }

    public void setOrd_placer_name(String ord_placer_name) {
        this.ord_placer_name = ord_placer_name;
    }

    public String getOrd_place_date() {
        return ord_place_date;
    }

    public void setOrd_place_date(String ord_place_date) {
        this.ord_place_date = ord_place_date;
    }

    public String getOrd_pckup_date() {
        return ord_pckup_date;
    }

    public void setOrd_pckup_date(String ord_pckup_date) {
        this.ord_pckup_date = ord_pckup_date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(c, order.c) && Objects.equals(getId(), order.getId()) && Objects.equals(getOrd_placer_name(), order.getOrd_placer_name()) && Objects.equals(getOrd_place_date(), order.getOrd_place_date()) && Objects.equals(getOrd_pckup_date(), order.getOrd_pckup_date());
    }

    @Override
    public int hashCode() {
        return Objects.hash(c, getId(), getOrd_placer_name(), getOrd_place_date(), getOrd_pckup_date());
    }



    @Override
    public String toString() {
        return "Order{" +
                "c=" + c +
                ", id=" + id +
                ", ord_placer_name='" + ord_placer_name + '\'' +
                ", ord_place_date='" + ord_place_date + '\'' +
                ", ord_pckup_date='" + ord_pckup_date + '\'' +
                '}';
    }

    public Order(Cake c, Integer id, String ord_placer_name, String ord_place_date, String ord_pckup_date) {
        this.c = c;
        this.id = id;
        this.ord_placer_name = ord_placer_name;
        this.ord_place_date = ord_place_date;
        this.ord_pckup_date = ord_pckup_date;
    }

    public Order(Integer id){
        this.id=id;
        this.ord_placer_name="";
        this.ord_place_date="";
        this.ord_pckup_date="";
        this.c=new Cake(0,"","","",00.00,00.00,0);
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
    public int compareTo(Order o) {
        if(this.ord_place_date.compareTo(o.ord_place_date)>0){
            if (this.ord_pckup_date.compareTo(o.ord_pckup_date)>0)
                return 1;
            else if(this.ord_place_date.compareTo(o.ord_place_date)==0)
                return this.ord_pckup_date.compareTo(o.ord_pckup_date);
        }
        return -1;
    }
}