module org.examplebr.com.petshop.petshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens org.examplebr.com.petshop.petshop to javafx.fxml;
    exports org.examplebr.com.petshop.petshop;
}