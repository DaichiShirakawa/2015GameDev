package jp.co.tokiha0s.game.classes.character;

import jp.co.tokiha0s.game.texture.text.FontDef;
import jp.co.tokiha0s.game.texture.text.TextTextureMaker;

/**
 * テキストテクスチャのみを持つキャラクター
 * 
 * @author shirakawa
 * 
 */
public class TextCharacter extends GameCharacterImpl {
	private FontDef fontDef = FontDef.DEFAULT;
	private String currentText = "";
	
	public TextCharacter() {
	}

	public TextCharacter(String text) {
		updateText(text);
	}

	public TextCharacter(String text, FontDef fontDef) {
		this.fontDef = fontDef;
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
		setTexture(TextTextureMaker.createText(text, fontDef));
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
