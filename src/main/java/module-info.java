module CCOS {

    requires javafx.controls;
    requires javafx.fxml;

    requires java.base;
    requires java.desktop;
    requires java.logging;

    requires javafx.base;
    requires javafx.graphics;

    requires logback.core;
    requires logback.classic;
    requires com.google.gson;
    requires org.slf4j;
    requires org.jetbrains.annotations;
    requires lombok;

    exports fr.truffaut.ccos;
    exports fr.truffaut.ccos.ui;
    exports fr.truffaut.ccos.utils;
}