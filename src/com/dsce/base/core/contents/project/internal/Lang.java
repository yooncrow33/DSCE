package com.dsce.base.core.contents.project.internal;

public class Lang {
    public enum type {
        c     (0.1f,0.9f,0.1f,0.2f,0.9f,0.8f),
        cpp   (0.3f,0.8f,0.2f,0.3f,0.7f,0.9f),
        java  (0.6f,0.7f,0.6f,0.7f,0.5f,0.5f),
        js    (0.9f,0.1f,0.2f,0.1f,0.1f,0.2f),
        py    (1.0f,0.1f,0.3f,0.1f,0.1f,0.2f),
        cs    (0.6f,0.6f,0.5f,0.7f,0.4f,0.7f),
        rust  (0.3f,0.8f,1.0f,0.8f,0.8f,0.8f),
        kotlin(0.7f,0.7f,0.4f,0.6f,0.4f,0.7f);

        float speed;
        float optimization;
        float stability;
        float maintenance;
        float setupCost;
        float scalability;

        type(float speed, float optimization,float stability, float maintenance, float setupCost, float scalability) {
            this.speed = speed;
            this.optimization = optimization;
            this.stability = stability;
            this.maintenance = maintenance;
            this.setupCost = setupCost;
            this.scalability = scalability;
        }

        public float getSpeed() {
            return speed;
        }

        public float getOptimization() {
            return optimization;
        }

        public float getStability() {
            return stability;
        }

        public float getMaintenance() {
            return maintenance;
        }

        public float getSetupCost() {
            return setupCost;
        }

        public float getScalability() {
            return scalability;
        }
    }
}
