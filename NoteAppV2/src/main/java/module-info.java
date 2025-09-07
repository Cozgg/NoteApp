module com.nhc.noteappv2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    

    opens com.nhc.noteappv2 to javafx.fxml;
    exports com.nhc.noteappv2;
    exports com.nhc.pojo;
    exports com.nhc.services;
    exports com.nhc.utils;
}
