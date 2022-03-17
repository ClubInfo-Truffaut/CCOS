module CCOS {
    requires java.base;
    requires java.desktop;
    requires java.logging;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;

    requires logback.core;
    requires logback.classic;
    requires com.google.gson;
    requires org.slf4j;
    requires org.jetbrains.annotations;
    requires lombok;

    exports fr.truffaut.ccos;
}