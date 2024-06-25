module com.example.egyptian_league_management_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.persistence;
    requires java.desktop;
    requires mysql.connector.j;

    opens com.example.egyptian_league_management_system to javafx.fxml;
    exports com.example.egyptian_league_management_system;
    exports com.example.egyptian_league_management_system.Controllers;
    opens com.example.egyptian_league_management_system.Controllers to javafx.fxml;
}