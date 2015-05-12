package jp.co.tkhs.gp15.content.tokishooting.character.friendlies.earth;

import java.lang.reflect.InvocationTargetException;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.content.tokishooting.character.friendlies.ship.TSShip;
import jp.co.tkhs.gp15.content.tokishooting.weapons.TSWeaponBase;
import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingRotateCharacter;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.game.utils.LR;
import jp.co.tkhs.gp15.system.io.Key;

/**
 * 砦の基底クラス
 * 
 * @author tokiha0s
 * 
 */
public abstract class TSBasionBase extends ShootingRotateCharacter {

    private TSEarth earth;
    private TSShip ship;
    private Class<? extends TSWeaponBase> weaponClass;

    public TSBasionBase(ShootingScene scene, TSEarth earth,
            Class<? extends TSWeaponBase> weaponClass) {
        super(scene, 0);
        this.earth = earth;
        this.weaponClass = weaponClass;
    }

    @Override
    protected boolean updateProcess() {
        setAngle(earth.getAngle() + getPlaceAngle());
        setVAngle(earth.getVAngle());
        return super.updateProcess();
    }

    @Override
    protected boolean inputProcess() {
        if (ship != null) {
            if (Key.F.isPressing()) {
                changeWeapon(ship, LR.LEFT);
                ship.getWeapon(LR.LEFT).reloadUpdate();
            }
            if (Key.J.isPressing()) {
                changeWeapon(ship, LR.RIGHT);
                ship.getWeapon(LR.RIGHT).reloadUpdate();
            }
        }
        return super.inputProcess();
    }

    @Override
    public TokiShootingScene getParentScene() {
        return (TokiShootingScene) (super.getParentScene());
    }

    protected final void changeWeapon(TSShip ship, LR lr) {
        if (ship.getWeapon(lr).getClass() == getWeaponClass()) {
            return;
        }
        try {
            TSWeaponBase weapon =
                    getWeaponClass().getConstructor(TokiShootingScene.class,
                            ShootingRotateCharacter.class, LR.class).newInstance(getParentScene(),
                            ship, lr);
            getParentScene().addMoney(ship.getWeapon(lr).getSellValue());
            ship.equip(weapon, lr);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            return;
        }
    }

    protected abstract float getPlaceAngle();

    public void landingShip(TSShip ship) {
        this.ship = ship;
    }

    public void takeOffShip() {
        ship = null;
    }

    protected TSShip getShip() {
        return ship;
    }

    public final Class<? extends TSWeaponBase> getWeaponClass() {
        return weaponClass;
    }
}
