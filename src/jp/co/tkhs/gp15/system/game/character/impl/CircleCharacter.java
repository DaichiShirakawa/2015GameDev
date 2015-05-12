package jp.co.tkhs.gp15.system.game.character.impl;

import static jp.co.tkhs.gp15.system.Constants.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * 塗りつぶしていない円のテクスチャを持つキャラクター
 * 
 * @author tokiha0s
 *
 */
public class CircleCharacter extends GameCharacterImpl {

    public CircleCharacter(int width, int height) {
        this(width, height, 1f);
    }

    public CircleCharacter(int width, int height, float strokeWidth) {
        int texWidth = width * 2 + 2;
        int texHeight = height * 2 + 2;
        BufferedImage bufferedImage = TextureLoader.createImageData(texWidth, texHeight);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.setColor(new Color(0f, 0f, 0f, 0f));
        g2d.fillRect(0, 0, texWidth, texHeight);
        g2d.setColor(Color.white);
        g2d.drawOval(1, 1, width * 2 - 1, height * 2 - 1);

        try {
            setTexture(TextureLoader.loadTexture(bufferedImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setWidth(width);
        setHeight(height);
        setX(CENTER_X);
        setY(CENTER_Y);
    }

    @Override
    protected boolean canDisposeTexture() {
        return true;
    }
}
