module gui {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires junit;
    requires org.junit.jupiter.api;

    opens gui to javafx.fxml;
    exports gui;
}