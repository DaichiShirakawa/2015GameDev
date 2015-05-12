package jp.co.tkhs.gp15.system.game.character.impl;

import java.awt.Color;

import jp.co.tkhs.gp15.system.game.character.GameCharacter;

/**
 * ダメージポップアップ用キャラクター
 * 
 * @author tokiha0s
 *
 */
public class DamagePopupCharacter extends PopupTextCharacter {
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
