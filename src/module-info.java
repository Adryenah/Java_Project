module gui {
    //requires javafx.graphics;
    requires javafx.fxml;
    requires junit;
    requires org.junit.jupiter.api;
    requires java.sql;
    //requires org.xerial.sqlitejdbc;
    //requires java.desktop;
    requires javafx.controls;
    opens gui to javafx.fxml;

    exports gui;
}