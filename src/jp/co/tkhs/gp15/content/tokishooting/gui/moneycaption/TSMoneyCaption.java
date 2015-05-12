package jp.co.tkhs.gp15.content.tokishooting.gui.moneycaption;

import static jp.co.tkhs.gp15.system.Constants.*;

import java.awt.Color;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.system.game.character.impl.TextCharacter;

/**
 * 所持金を表示
 * 
 * @author tokiha0s
 *
 */
public class TSMoneyCaption extends TextCharacter {
    TokiShootingScene scene;

    public TSMoneyCaption(TokiShootingScene edfScene) {
        super(getCaption(edfScene.getMoney()));
        this.scene = edfScene;
        setScale(0.4f);
        setX(CENTER_X);
        setY(30);
        setColor(Color.orange.brighter());
    }

    private static String getCaption(int money) {
        return "$ " + money;
    }

    @Override
    protected boolean updateProcess() {
        updateText(getCaption(scene.getMoney()));
        return super.updateProcess();
    }

}
