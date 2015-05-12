package jp.co.tkhs.gp15.system.game.character.impl;

import java.awt.Color;

import jp.co.tkhs.gp15.system.graphics.font.FontDef;
import jp.co.tkhs.gp15.system.graphics.font.FontTextureMaker;

/**
 * テキストテクスチャのみを持つキャラクター
 *
 * @author tokiha0s
 *
 */
public class TextCharacter extends GameCharacterImpl {
    private FontDef font = FontDef.DEFAULT;
    private String currentText = "";

    /**
     * 空のテキストを持つキャラクターを生成
     *
     */
    public TextCharacter() {}

    /**
     * 指定テキストを持つキャラクターを生成
     *
     * @param text
     */
    public TextCharacter(String text) {
        updateText(text);
    }

    /**
     * フォントを指定してキャラクターを生成
     *
     * @param text
     * @param font
     */
    public TextCharacter(String text, FontDef font) {
        this.font = font;
        updateText(text);
    }

    public void updateText(String text) {
        if (currentText.equals(text)) {
            return;
        }
        currentText = text;
        if (getTexture() != null) {
            getTexture().dispose();
        }
        setTexture(FontTextureMaker.createText(text, Color.white, font));
        resetSize();
    }

    public void resetSize() {
        setWidth(getTexture().getWidth());
        setHeight(getTexture().getHeight());
    }

    @Override
    protected boolean canDisposeTexture() {
        return true;
    }

}
