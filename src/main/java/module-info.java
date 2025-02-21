module com.cs145group {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.cs145group to javafx.fxml;
    exports com.cs145group;
}
