package jp.co.tokiha0s.game.scenes.tokishooting.characters;

import static jp.co.tokiha0s.game.common.CommonMethod.*;

import java.util.Iterator;

import jp.co.tokiha0s.game.classes.GameObject;
import jp.co.tokiha0s.game.classes.scene.ShootingScene;
import jp.co.tokiha0s.game.common.LR;
import jp.co.tokiha0s.game.scenes.tokishooting.TokiShootingScene;
import jp.co.tokiha0s.game.scenes.tokishooting.characters.friendlies.earth.TSBasionBase;
import jp.co.tokiha0s.game.scenes.tokishooting.characters.friendlies.earth.TSEarth;
import jp.co.tokiha0s.game.scenes.tokishooting.characters.friendlies.ship.TSShip;
import jp.co.tokiha0s.game.scenes.tokishooting.weapons.TSWeaponBase;

/**
 * 自軍、敵軍、弾丸はこのクラスで管理される。
 * 
 * @author shirakawa
 *
 */
public class TSCharacterController extends ShootingScene {
	TokiShootingScene scene;
	TSEarth earth;
	TSShip ship;

	public TSCharacterController(TokiShootingScene scene) {
		this.scene = scene;
		this.earth = add(new TSEarth(scene));
		this.ship = add(new TSShip(scene, this));
	}

	@Override
	public boolean updateProcess() {
		return super.updateProcess();
	}
	
	public TSWeaponBase getWeapon(LR lr) {
		return ship.getWeapon(lr);
	}

	public boolean earthArrive() {
		return !earth.zeroHP();
	}

	public TSBasionBase getBackBase() {
		for (Iterator<GameObject> ite = earth.getIterator(); ite.hasNext();) {
			GameObject go = ite.next();
			if (!(go instanceof TSBasionBase)) {
				continue;
			}

			//船と基地の角度の差が45°以内なら帰還できる
			float basionAngle = toAbsoluteAngle(((TSBasionBase) go).getAngle());
			float shipAngle = toAbsoluteAngle(ship.getAngle());

			if (Math.abs(shipAngle - basionAngle) <= 45) {
				return (TSBasionBase) go;
			}
		}
		return null;
	}

	public TSShip getShip() {
		return ship;
	}
}
