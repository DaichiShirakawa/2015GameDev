package jp.co.tkhs.gp15.main;

import static jp.co.tkhs.gp15.system.Constants.*;
import static org.lwjgl.opengl.GL11.*;

import java.io.File;

import jp.co.tkhs.gp15.system.graphics.AlphaBlend;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * プログラムの起動点
 *
 * @author tokiha0s
 * @since 2014/05/26
 *
 */
public final class Main {

    public static void main(final String[] args) {
        initialize();

        GameSceneMaster sceneMaster = GameSceneMaster.getInstance();
        try {
            new GameLoop(sceneMaster).run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sceneMaster.destroy();
            finalizee();
        }
    }

    private static void initialize() {
        setLwjglNativeLibrary();
        createDisplay();
        setPreSettingsFor2DGame();
    }

    private static void setLwjglNativeLibrary() {
        System.setProperty(LWJGL_NATIVE_PROPERTY_NAME,
                new File(WINDOWS_NATIVE_FOLDER_STRING).getAbsolutePath());
    }

    private static void createDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
            Display.setTitle(PRODUCT_TITLE);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    private static void setPreSettingsFor2DGame() {
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WIDTH, 0, HEIGHT, -DEPTH / 2, DEPTH / 2);
        glMatrixMode(GL_MODELVIEW);
        AlphaBlend.AlphaBlend.config();
    }

    private static void finalizee() {
        Display.destroy();
        System.exit(0);
    }
}
