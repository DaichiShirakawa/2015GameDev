package jp.co.tkhs.gp15.content.tokishooting.character;

import static jp.co.tkhs.gp15.system.game.utils.GameUtils.*;

import java.util.Iterator;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.content.tokishooting.character.friendlies.earth.TSBasionBase;
import jp.co.tkhs.gp15.content.tokishooting.character.friendlies.earth.TSEarth;
import jp.co.tkhs.gp15.content.tokishooting.character.friendlies.ship.TSShip;
import jp.co.tkhs.gp15.content.tokishooting.weapons.TSWeaponBase;
import jp.co.tkhs.gp15.system.game.GameObject;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.game.utils.LR;

/**
 * 自軍、敵軍、弾丸はこのクラスで管理される。
 *
 * @author tokiha0s
 *
 */
public class TSCharacterController extends ShootingScene {
    TokiShootingScene scene;
    TSEarth earth;
    TSShip ship;

    public TSCharacterController(TokiShootingScene scene) {
        this.scene = scene;

        this.earth = new TSEarth(scene);
        add(earth);

        this.ship = new TSShip(scene, this);
        add(ship);
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

            // 船と基地の角度の差が45°以内なら帰還できる
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
