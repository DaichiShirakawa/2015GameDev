package jp.co.tokiha0s.game.scenes.tokishooting.gui.weaponcaption;

import jp.co.tokiha0s.game.classes.character.GameCharacterImpl;
import jp.co.tokiha0s.game.common.LR;
import jp.co.tokiha0s.game.scenes.tokishooting.characters.TSCharacterController;

/**
 * 左右両方の武器状態を表示するGUI
 * 
 * @author shirakawa
 * 
 */
public class TSWeaponGUI extends GameCharacterImpl {
	TSCharacterController scene;

	private OddWeaponGUI leftGUI;
	private OddWeaponGUI rightGUI;

	public TSWeaponGUI(TSCharacterController characters) {
		this.scene = characters;
		leftGUI = add(new OddWeaponGUI(characters.getShip(),
				characters.getWeapon(LR.LEFT), LR.LEFT));
		rightGUI = add(new OddWeaponGUI(characters.getShip(),
				characters.getWeapon(LR.RIGHT), LR.RIGHT));
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
