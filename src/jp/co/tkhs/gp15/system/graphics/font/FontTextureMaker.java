package jp.co.tkhs.gp15.system.graphics.font;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import jp.co.tkhs.gp15.system.graphics.Texture;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * 受け取った文字列をテクスチャ化して返す
 *
 * @author tokiha0s
 *
 */
public class FontTextureMaker {
    private FontTextureMaker() {
        // hide
    }

    /**
     * 白色のデフォルトフォントで文字テクスチャを生成
     *
     * @param str
     * @return 文字テクスチャ
     */
    public static Texture createText(String str) {
        return create(str, Color.white, FontDef.DEFAULT);
    }

    /**
     * 指定色のデフォルトフォントで文字テクスチャを生成
     *
     * @param str
     * @param color
     * @return 文字テクスチャ
     */
    public static Texture createText(String str, Color color) {
        return create(str, color, FontDef.DEFAULT);
    }

    /**
     * 指定色の指定フォントで文字テクスチャを生成
     *
     * @param str
     * @param color
     * @param font
     * @return 文字テクスチャ
     */
    public static Texture createText(String str, Color color, FontDef font) {
        return create(str, color, font);
    }

    public static Texture create(String str, Color color, FontDef fontDef) {
        Font font = FontCollector.getFont(fontDef);
        String[] lines = str.split("\n");
        float width = 0;
        for (String line : lines) {
            // 縦横比が半角は2:1、全角は1:1の前提
            width = Math.max((fontDef.size / 2) * getByteLength(line), width);
        }
        float lineHeight = fontDef.size;
        return createText(lines, color, font, width, lineHeight);
    }

    private static float getByteLength(String str) {
        try {
            return str.getBytes("Shift-JIS").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static Texture createText(String[] lines, Color color, Font font, float width,
            float lineHeight) {
        BufferedImage image = null;
        Graphics2D g = null;
        try {
            image = TextureLoader.createImageData((int) width, (int) lineHeight * lines.length);

            g = image.createGraphics();
            g.setFont(font);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(color);

            int y = 0;
            for (String line : lines) {
                y += lineHeight - 4;
                float x = width - ((lineHeight / 2) * getByteLength(line));
                x /= 2;
                g.drawString(line, x, y);
            }

            return TextureLoader.loadTexture(image);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (g != null) {
                g.dispose();
            }
            if (image != null) {
                image.flush();
            }
        }
        return null;
    }
}
