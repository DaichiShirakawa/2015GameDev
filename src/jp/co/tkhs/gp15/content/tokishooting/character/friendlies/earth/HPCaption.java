package jp.co.tkhs.gp15.content.tokishooting.character.friendlies.earth;

import static jp.co.tkhs.gp15.system.Constants.*;

import java.awt.Color;

import jp.co.tkhs.gp15.system.game.character.impl.TextCharacter;

/**
 * 地球のHP表示
 * 
 * @author tokiha0s
 *
 */
class HPCaption extends TextCharacter {
    private TSEarth earth;

    public HPCaption(TSEarth earth) {
        super();
        this.earth = earth;
        setX(CENTER_X - 1);
        setY(CENTER_Y - 30);
        setScale(0.25f);
        setColor(new Color(1f, 0.3f, 0f));
    }

    @Override
    protected boolean updateProcess() {
        updateText(getHpText(earth.getHP()));
        return super.updateProcess();
    }

    private String getHpText(float hp) {
        return String.valueOf("♥" + (int) hp + " ");
    }
}
