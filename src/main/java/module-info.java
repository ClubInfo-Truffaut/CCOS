module CCOS {
    requires java.base;
    requires java.desktop;
    requires java.logging;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;

    requires logback.core;
    requires logback.classic;

    exports fr.truffaut.ccos;
}