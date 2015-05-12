package jp.co.tokiha0s.game.scenes.title;

import static jp.co.tokiha0s.game.common.Commons.*;
import jp.co.tokiha0s.game.classes.character.GameCharacterImpl;
import jp.co.tokiha0s.game.texture.TextureLoader;

/**
 * タイトルの背景
 * 
 * @author shirakawa
 *
 */
class BackGround extends GameCharacterImpl {
	public BackGround() {
		setTexture(TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING
				+ "title.png"));
		setX(CENTER_X);
		setY(CENTER_Y);
		setWidth(WIDTH);
		setHeight(HEIGHT);
	}

	@Override
	protected boolean canDisposeTexture() {
		return true;
	}

}
