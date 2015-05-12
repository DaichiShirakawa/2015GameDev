package jp.co.tokiha0s.game.classes.character;

/**
 * ちょっとした用途に使いたいただのキャラクター
 * 
 * @author shirakawa
 *
 */
public class SimpleCharacter extends GameCharacterImpl {
	@Override
	protected boolean canDisposeTexture() {
		return false;
	}
}
