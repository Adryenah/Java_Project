module a5.Adryenah {
    requires javafx.graphics;
    requires javafx.fxml;
    requires junit;
    requires org.junit.jupiter.api;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;
    requires javafx.controls;

    opens UI to javafx.fxml;
    opens Main to javafx.fxml;

    exports UI;
    exports Main;
}