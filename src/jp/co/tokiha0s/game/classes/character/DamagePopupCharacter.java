package jp.co.tokiha0s.game.classes.character;

import java.awt.Color;

/**
 * ダメージポップアップ用キャラクター
 * 
 * @author shirakawa
 *
 */
public class DamagePopupCharacter extends PopupTextCharacter{
	private static final int CHANGE_COLOR_SPAN = 10;

	private int changeColorFrame = CHANGE_COLOR_SPAN;

	public DamagePopupCharacter(GameCharacter target, int damage) {
		super(target, String.valueOf(-damage));
		setColor(Color.red);
	}

	@Override
	protected boolean updateProcess() {
		if (changeColorFrame == 0) {
			setColor(getColor() == Color.red ? Color.orange : Color.red);
			changeColorFrame = CHANGE_COLOR_SPAN;
		}
		changeColorFrame--;
		return super.updateProcess();
	}
}
