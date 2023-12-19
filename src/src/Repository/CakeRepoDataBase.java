//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Repository;

import Domain.Cake;
import Repository.FileRepo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class CakeRepoDataBase extends FileRepo<Integer, Cake> {
    private Connection conn = null;

    public CakeRepoDataBase(String filename) {
        super(filename);
    }

    private void openConnection() {
        try {
            if (this.conn == null || this.conn.isClosed()) {
                this.conn = DriverManager.getConnection(Filename);
            }
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }

    private void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }

    protected void readFromFile() {
        try {
            this.openConnection();
            PreparedStatement statement = this.conn.prepareStatement("SELECT * from cakes");
            ResultSet rs1 = statement.executeQuery();
            ArrayList<Cake> cakes = new ArrayList();

            while(rs1.next()) {
                int id = rs1.getInt("id");
                Cake cake = new Cake(id, rs1.getString("name"),
                        rs1.getString("layerFlavour"),
                        rs1.getString("frosting"),
                        rs1.getDouble("weight"),
                        rs1.getDouble("price"),
                        rs1.getInt("noLayers"));
                cakes.add(cake);
            }

            rs1.close();
            statement.close();
            Iterator var12 = cakes.iterator();

            while(var12.hasNext()) {
                Cake cake = (Cake)var12.next();
                super.addItem(cake.getId(), cake);
            }

            this.closeConnection();
        } catch (SQLException var10) {
            var10.printStackTrace();
        } finally {
            this.closeConnection();
        }

    }

    protected void writeToFile() {
        try {
            this.openConnection();

            try {
                PreparedStatement statement = this.conn.prepareStatement("DELETE FROM cakes");
                statement.executeUpdate();
                statement.close();
            } catch (SQLException var8) {
                var8.printStackTrace();
            }

            Iterator var11 = super.getAllItems().iterator();

            while(var11.hasNext()) {
                Cake cake = (Cake)var11.next();
                PreparedStatement statement = this.conn.prepareStatement("INSERT INTO cakes VALUES (?, ?, ?, ?, ?, ?, ?)");
                statement.setInt(1, cake.getId());
                statement.setString(2, cake.getCake_Name());
                statement.setString(3, cake.getCakeLayer_flv());
                statement.setString(4, cake.getCake_frosting());
                statement.setDouble(5, cake.getWeight());
                statement.setDouble(6, cake.getPrice());
                statement.setInt(7, cake.getCake_layers());
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
