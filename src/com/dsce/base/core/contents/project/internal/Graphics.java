package com.dsce.base.core.contents.project.internal;

public class Graphics {
    public enum type {
        vulkan(0.3f,1.0f,0.9f),
        open(0.8f,0.5f,0.4f),
        directX(0.5f,0.7f,0.7f);

        float speed;
        float visual;
        float optimization;

        public float getSpeed() {
            return speed;
        }

        public float getVisual() {
            return visual;
        }

        public float getOptimization() {
            return optimization;
        }

        type(float speed, float visual, float optimization) {
            this.speed = speed;
            this.visual = visual;
            this.optimization = optimization;
        }
    }
}
