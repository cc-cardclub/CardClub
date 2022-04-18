module at.rennweg.htl.cardclubclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;

    opens at.rennweg.htl.cardclubclient to javafx.fxml;
    exports at.rennweg.htl.cardclubclient;
}