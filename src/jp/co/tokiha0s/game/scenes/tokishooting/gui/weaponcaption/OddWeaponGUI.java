package jp.co.tokiha0s.game.scenes.tokishooting.gui.weaponcaption;

import java.awt.Color;

import jp.co.tokiha0s.game.classes.character.GameCharacterImpl;
import jp.co.tokiha0s.game.classes.character.SimpleCharacter;
import jp.co.tokiha0s.game.classes.character.TextCharacter;
import jp.co.tokiha0s.game.common.Commons;
import jp.co.tokiha0s.game.common.LR;
import jp.co.tokiha0s.game.scenes.tokishooting.characters.friendlies.earth.TSBasionBase;
import jp.co.tokiha0s.game.scenes.tokishooting.characters.friendlies.ship.TSShip;
import jp.co.tokiha0s.game.scenes.tokishooting.weapons.TSWeaponBase;

/**
 * 武器単体GUI
 * 
 * @author shirakawa
 * 
 */
class OddWeaponGUI extends GameCharacterImpl {
	private TSShip ship;
	private TSWeaponBase weapon;
	private SimpleCharacter weaponView;
	private TextCharacter bulletCountText;
	private TextCharacter costText;

	public OddWeaponGUI(TSShip ship, TSWeaponBase weapon, LR lr) {
		setX(Commons.CENTER_X + (165 * lr.signum()));
		setY(40);

		this.ship = ship;

		weaponView = add(new SimpleCharacter());
		weaponView.setX(getX())
				.setY(getY() + 5)
				.setWidth(weapon.getWidth())
				.setHeight(weapon.getHeight())
				.setScale(2)
				.setColor(weapon.getColor());
		bulletCountText = add(new TextCharacter());
		bulletCountText.setX(getX())
				.setY(getY() - 25)
				.setScale(0.3f)
				.setColor(Color.white);

		costText = add(new TextCharacter());
		costText.setX(getX() - 10 * lr.signum())
				.setY(getY() + 30)
				.setScale(0.3f)
				.setColor(Color.yellow)
				.hide();

		add(new DelayBar(this));

		setWeapon(weapon);
	}

	public TSWeaponBase getWeapon() {
		return weapon;
	}

	public void setWeapon(TSWeaponBase weapon) {
		this.weapon = weapon;
		weaponView.setTexture(weapon.getTexture());
		bulletCountText.updateText(getBulletCountText(weapon));
	}

	private String getBulletCountText(TSWeaponBase weapon) {
		return weapon.getRemainBullet() + " / " + weapon.getMaxBullet();
	}

	@Override
	public boolean updateProcess() {
		bulletCountText.updateText(getBulletCountText(weapon));
		costCheckProcess();
		return true;
	}

	private void costCheckProcess() {
		TSBasionBase basion = ship.getBackingBasion();
		if (basion == null) {
			costText.hide();
			return;
		}
		costText.show();

		// 現在持っている武器と着陸した基地が同じなら、弾丸購入コストを表示する。
		// 武器が違うなら、交換（弾丸売却）で得られる金額を表示する。
		if (weapon.getClass() == basion.getWeaponClass()) {
			costText.updateText("装填費:" + weapon.getReloadCost());
		} else {
			costText.updateText("売却額:" + weapon.getSellValue());
		}
	}

	@Override
	protected boolean canDisposeTexture() {
		return false;
	}
}