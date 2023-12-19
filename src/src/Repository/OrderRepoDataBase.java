//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Repository;

import Domain.Cake;
import Domain.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import org.sqlite.SQLiteDataSource;


public class OrderRepoDataBase extends FileRepo<Integer, Order> {
    private Connection conn1 = null;

    public OrderRepoDataBase(String orderFileName) {
        super(orderFileName);
    }

    private void openConnection() {
        try {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(this.Filename);
            if (this.conn1 == null || this.conn1.isClosed()) {
                this.conn1 = ds.getConnection();
            }
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }

    private void closeConnection() {
        try {
            this.conn1.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }

    protected void readFromFile() {
        try {
            CakeRepoDataBase Crepo = new CakeRepoDataBase(this.Filename);
            this.openConnection();
            PreparedStatement statement = this.conn1.prepareStatement("SELECT * from orders");
            ResultSet rs1 = statement.executeQuery();
            ArrayList<Order> orders = new ArrayList();

            while(rs1.next()) {
                int id = rs1.getInt("id");
                Order A = new Order(Crepo.findItemByID(rs1.getInt("idCake")),id,rs1.getString("placerName"),rs1.getString("placeDate"),rs1.getString("pickupDate"));

                orders.add(A);
            }

            rs1.close();
            statement.close();
            Iterator var13 = orders.iterator();

            while(var13.hasNext()) {
                Order A = (Order)var13.next();
                super.addItem(A.getId(), A);
            }

            this.closeConnection();
        } catch (SQLException var11) {
            var11.printStackTrace();
        } finally {
            this.closeConnection();
        }

    }

    protected void writeToFile() {
        try {
            this.openConnection();

            try {
                PreparedStatement statement = this.conn1.prepareStatement("DELETE FROM orders");
                statement.executeUpdate();
                statement.close();
            } catch (SQLException var8) {
                var8.printStackTrace();
            }

            Iterator var11 = super.getAllItems().iterator();

            while(var11.hasNext()) {
                Order A = (Order)var11.next();
                PreparedStatement statement = this.conn1.prepareStatement("INSERT INTO orders VALUES (?, ?, ?, ?, ?)");
                statement.setInt(1,A.getC().getId());
                statement.setInt(2, A.getId());
                statement.setString(3, A.getOrd_placer_name());
                statement.setString(4, A.getOrd_place_date());
                statement.setString(5, A.getOrd_pckup_date());
                statement.executeUpdate();
                statement.close();
            }

            this.closeConnection();
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            this.closeConnection();
        }

    }
}
