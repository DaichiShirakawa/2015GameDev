package jp.co.tkhs.gp15.content.tokishooting.character.friendlies.ship;

import static jp.co.tkhs.gp15.system.Constants.*;
import static jp.co.tkhs.gp15.system.game.utils.GameUtils.*;

import java.awt.Color;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.content.tokishooting.character.TSCharacterController;
import jp.co.tkhs.gp15.content.tokishooting.character.friendlies.earth.TSBasionBase;
import jp.co.tkhs.gp15.content.tokishooting.weapons.TSWeaponBase;
import jp.co.tkhs.gp15.content.tokishooting.weapons.normal.TSNormalWeapon;
import jp.co.tkhs.gp15.main.FPSManager;
import jp.co.tkhs.gp15.system.game.character.impl.CircleCharacter;
import jp.co.tkhs.gp15.system.game.character.impl.PopupTextCharacter;
import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingRotateCharacter;
import jp.co.tkhs.gp15.system.game.utils.LR;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;
import jp.co.tkhs.gp15.system.io.Key;

/**
 * 自機キャラクター
 *
 * @author tokiha0s
 *
 */
public class TSShip extends ShootingRotateCharacter {
    // 自機テクスチャ
    private static final String TEXTURE_PATH = IMAGE_FOLDER_STRING + "tokiIcon.png";

    // 基礎パラメータ
    private static final int OFFSET_Y = 40; // 画面中心点から戦艦までの半径
    private static final float ROTATE_SPEED = 0.75f;
    private static final int SIZE = 16;

    // 入力制御
    private static final Key LEFT_MOVE = Key.D;
    private static final Key RIGHT_MOVE = Key.K;
    private static final Key LEFT_WEAPON = Key.F;
    private static final Key RIGHT_WEAPON = Key.J;
    private static final Key DASH = Key.SPACE;
    private static final Key BACK_TO_EARTH = Key.B;

    // ダッシュ移動パラメータ
    private static final float DASH_START_SPEED = 5;
    private static final long DASH_DELAY_FRAME = 18;
    private float dashSpeed = 0;
    private long dashStartFrame;

    // 帰還パラメータ
    private static final float BACK_START_SPEED = 2;
    private BackState backState = BackState.NONE;
    private TSBasionBase backingBasion = null;

    // 武器
    public static final float WEAPON_DISTANCE = 8; // 本体から武器までの距離
    private TSWeaponBase leftWeapon;
    private TSWeaponBase rightWeapon;

    TSCharacterController characterController;

    public TSShip(TokiShootingScene scene, TSCharacterController characterController) {
        super(scene, 0);
        this.characterController = characterController;
        setTexture(TextureLoader.loadTexture(TEXTURE_PATH));
        setColor(new Color(0f, 0.8f, 1f));
        setY(OFFSET_Y);
        setWidth(SIZE);
        setHeight(SIZE);
        setTeam(ShootingTeam.FRIEND_TEAM);
        setOffsetY(OFFSET_Y);
        setAngle(0);

        equip(new TSNormalWeapon(scene, this, LR.LEFT), LR.LEFT);
        equip(new TSNormalWeapon(scene, this, LR.RIGHT), LR.RIGHT);
        getWeapon(LR.LEFT).addBullet(30);
        getWeapon(LR.RIGHT).addBullet(30);

        CircleCharacter circle = new CircleCharacter(OFFSET_Y * 2, OFFSET_Y * 2);
        circle.setAlpha(0.5f).setColor(new Color(0f, 1f, 0.8f));
        add(circle);
    }

    @Override
    protected boolean updateProcess() {
        dashProcess();
        backToEarthProcess();
        return super.updateProcess();
    }

    private void dashProcess() {
        dashSpeed -= (dashSpeed + Math.signum(dashSpeed)) * 0.1;
        if (Math.abs(dashSpeed) < 0.1) {
            dashSpeed = 0;
            return;
        }
        setAngle(getAngle() + dashSpeed);
    }

    private void backToEarthProcess() {
        switch (backState) {
            case BACK_COMPLETE:
                setVAngle(backingBasion.getVAngle());
                break;
            case BACK_STARTING:
                setVAngle((toAbsoluteAngle(backingBasion.getAngle()) - toAbsoluteAngle(getAngle())) / 10);
                setVOffsetY(getVOffsetY() * 0.85f);
                if (-0.1 < getVOffsetY()) {
                    setVOffsetY(0);
                    backState = BackState.BACK_COMPLETE;
                    backingBasion.landingShip(this);
                }
                break;
            case BACK_ENDING:
                setVAngle(0);
                setVOffsetY(getVOffsetY() * 0.85f);
                if (getVOffsetY() < 0.1) {
                    setVOffsetY(0);
                    backState = BackState.NONE;
                }
                break;
            case NONE:
                break;
            default:
                break;
        }
    }

    @Override
    public boolean inputProcess() {
        switch (backState) {
            case NONE:
                leftMoveInput();
                rightMoveInput();
                shootInput();
                break;
            default:
                break;
        }
        backToEarthInput();
        return super.inputProcess();
    }

    private void leftMoveInput() {
        if (!LEFT_MOVE.isPressing()) {
            return;
        }
        if (DASH.isPressing() && canDash()) {
            dashStart(DASH_START_SPEED);
            return;
        }
        setAngle(getAngle() + ROTATE_SPEED);
    }

    private void rightMoveInput() {
        if (!RIGHT_MOVE.isPressing()) {
            return;
        }
        if (DASH.isPressing() && canDash()) {
            dashStart(-DASH_START_SPEED);
            return;
        }
        setAngle(getAngle() - ROTATE_SPEED);
    }

    private void backToEarthInput() {

        switch (backState) {
            case NONE:
                if (!BACK_TO_EARTH.isPressed()) {
                    return;
                }
                backingBasion = characterController.getBackBase();
                if (backingBasion == null) {
                    PopupTextCharacter popup = new PopupTextCharacter(this, "×");
                    popup.setColor(Color.red);
                    getParentScene().add(popup);
                    return;
                }
                backState = BackState.BACK_STARTING;
                setVOffsetY(-BACK_START_SPEED);
                return;
            case BACK_COMPLETE:
                if (!(BACK_TO_EARTH.isPressed() || DASH.isPressed())) {
                    return;
                }
                backState = BackState.BACK_ENDING;
                setVOffsetY(BACK_START_SPEED);
                backingBasion.takeOffShip();
                backingBasion = null;
                return;
            default:
                return;
        }
    }

    private void shootInput() {
        if (((TokiShootingScene) getParentScene()).isBreakTime()) {
            return;
        }
        if (LEFT_WEAPON.isPressing() && leftWeapon != null) {
            leftWeapon.shoot();
        }
        if (RIGHT_WEAPON.isPressing() && rightWeapon != null) {
            rightWeapon.shoot();
        }
    }

    private boolean canDash() {
        return dashStartFrame + DASH_DELAY_FRAME <= FPSManager.getTotalFrame();
    }

    private void dashStart(float dashSpeed) {
        this.dashSpeed = dashSpeed;
        dashStartFrame = FPSManager.getTotalFrame();
    }

    public void equip(TSWeaponBase weapon, LR lr) {
        if (lr == LR.LEFT) {
            if (leftWeapon != null) {
                leftWeapon.destroy();
            }
            leftWeapon = weapon;
            add(leftWeapon);
        } else {
            if (rightWeapon != null) {
                rightWeapon.destroy();
            }
            rightWeapon = weapon;
            add(rightWeapon);
        }
    }

    public TSWeaponBase getWeapon(LR lr) {
        if (lr == LR.LEFT) {
            return leftWeapon;
        } else {
            return rightWeapon;
        }
    }

    private enum BackState {
        NONE,
        BACK_STARTING,
        BACK_COMPLETE,
        BACK_ENDING;
    }

    public TSBasionBase getBackingBasion() {
        return backingBasion;
    }
}
