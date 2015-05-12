package jp.co.tkhs.gp15.content.tokishooting.gui.weaponcaption;

import jp.co.tkhs.gp15.content.tokishooting.character.TSCharacterController;
import jp.co.tkhs.gp15.system.game.character.impl.GameCharacterImpl;
import jp.co.tkhs.gp15.system.game.utils.LR;

/**
 * 左右両方の武器状態を表示するGUI
 *
 * @author tokiha0s
 *
 */
public class TSWeaponGUI extends GameCharacterImpl {
    TSCharacterController scene;

    private OddWeaponGUI leftGUI;
    private OddWeaponGUI rightGUI;

    public TSWeaponGUI(TSCharacterController characters) {
        this.scene = characters;
        leftGUI = new OddWeaponGUI(characters.getShip(), characters.getWeapon(LR.LEFT), LR.LEFT);
        add(leftGUI);

        rightGUI = new OddWeaponGUI(characters.getShip(), characters.getWeapon(LR.RIGHT), LR.RIGHT);
        add(rightGUI);
    }

    @Override
    public boolean updateProcess() {
        if (scene.getWeapon(LR.LEFT) != leftGUI.getWeapon()) {
            leftGUI.setWeapon(scene.getWeapon(LR.LEFT));
        }
        if (scene.getWeapon(LR.RIGHT) != rightGUI.getWeapon()) {
            rightGUI.setWeapon(scene.getWeapon(LR.RIGHT));
        }
        return true;
    }

    @Override
    protected boolean canDisposeTexture() {
        return false;
    }
}
