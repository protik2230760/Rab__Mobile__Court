module com.example.rab__mobile__court {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.dynalink;
    requires java.logging;


    opens com.example.rab__mobile__court to javafx.fxml;
    exports com.example.rab__mobile__court;
}