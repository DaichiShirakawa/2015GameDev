package jp.co.tkhs.gp15.system.game.character.impl;

import jp.co.tkhs.gp15.system.game.character.GameCharacter;


/**
 * 渡された文字をtargetの地点からポップアップする。ダメージ表現などに。
 * 
 * @author tokiha0s
 *
 */
public class PopupTextCharacter extends TextCharacter {
    private static final int AGE_SECONDS = 1;

    public PopupTextCharacter(GameCharacter target, String text) {
        super(text);
        setX(target.getX());
        setY(target.getY() + target.getHeight() / 2);
        setScale(0.25f);
        setVY(1);
        destroyAfter(getAgeSeconds());
    }

    protected int getAgeSeconds() {
        return AGE_SECONDS;
    }

    @Override
    protected boolean updateProcess() {
        setVY(getVY() * 0.9f);
        return super.updateProcess();
    }

}
