package jp.co.tkhs.gp15.system.graphics;

import static org.lwjgl.opengl.GL11.*;

public enum AlphaBlend {
    AlphaBlend {
        @Override
        public void config() {
            // アルファ合成
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        }
    },
    Add {
        @Override
        public void config() {
            // 加算合成
            glBlendFunc(GL_SRC_ALPHA, GL_ONE);
        }
    },
    Multiple {
        @Override
        public void config() {
            // 乗算合成
            glBlendFunc(GL_ZERO, GL_SRC_COLOR);
        }
    },
    Screen {
        @Override
        public void config() {
            // スクリーン合成
            glBlendFunc(GL_ONE_MINUS_DST_COLOR, GL_ONE);
        }
    },
    Reverse {
        @Override
        public void config() {
            // 反転合成
            glBlendFunc(GL_ONE_MINUS_DST_COLOR, GL_ZERO);
        }
    };

    public abstract void config();
}
