package fr.truffaut.ccos.utils;

import javafx.animation.Interpolator;

public abstract class InterpolatorCustom extends Interpolator {

    public static final Interpolator EASE_05 = new Interpolator() {
        @Override
        protected double curve(double t) {
            // See the SMIL 3.1 specification for details on this calculation
            // acceleration = 0.2, deceleration = 0.2
            return clamp((t < 0.5) ? 3.125 * t * t
                    : (t > 0.5) ? -3.125 * t * t + 6.25 * t - 2.125
                    : 1.25 * t - 0.125);
        }

        @Override
        public String toString() {
            return "Interpolator.EASE_BOTH";
        }
    };
    public static final Interpolator EASE_IN_05 = new Interpolator() {
        private static final double S1 = 25.0 / 9.0;
        private static final double S3 = 10.0 / 9.0;
        private static final double S4 = 1.0 / 9.0;

        @Override
        protected double curve(double t) {
            // See the SMIL 3.1 specification for details on this calculation
            // acceleration = 0.2, deceleration = 0.0
            return clamp((t < 0.5) ? S1 * t * t : S3 * t - S4);
        }

        @Override
        public String toString() {
            return "Interpolator.EASE_IN";
        }
    };

    private static double clamp(double t) {
        return (t < 0.0) ? 0.0 : (t > 1.0) ? 1.0 : t;
    }

}
