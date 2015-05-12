package jp.co.tkhs.gp15.content.tokishooting.character.enemies;

import static jp.co.tkhs.gp15.system.Constants.*;
import static jp.co.tkhs.gp15.system.game.utils.GameUtils.*;

import java.awt.Color;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.content.tokishooting.weapons.enemies.TSNormalEnemyBullet;
import jp.co.tkhs.gp15.main.FPSManager;
import jp.co.tkhs.gp15.system.Constants;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.game.utils.LR;
import jp.co.tkhs.gp15.system.graphics.Texture;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * 中ボス敵
 * 
 * @author tokiha0s
 *
 */
public class TSEnemyToki extends TSEnemyBase {
    private static final int POWER = 1;
    private static final int HP = 10;
    private static final int SIZE = 40;
    private static final Texture TEXTURE = TextureLoader.loadTexture(Constants.IMAGE_FOLDER_STRING
            + "dotTokiIcon.png");
    private static final float ROTATE_SPEED = 0.4f;
    private static final float FALL_SPEED = 0.3f;
    private static final int MONEY = 70;
    private static final TSEnemyProperty property = new TSEnemyProperty(POWER, HP, SIZE, TEXTURE,
            ROTATE_SPEED, FALL_SPEED, MONEY);

    public TSEnemyToki(ShootingScene parentScene, float bornAngle, LR lr) {
        super(parentScene, bornAngle, lr, property);
    }

    @Override
    protected boolean updateProcess() {
        if (getOffsetY() < (WIDTH / 2) - SIZE) {
            setVOffsetY(getVOffsetY() * 0.95f);
        }
        // ランダムで射撃
        if ((FPSManager.getTotalFrame() % 60 == 0) && persentOf(15)) {
            ((TokiShootingScene) getParentScene()).add(new TSNormalEnemyBullet(getParentScene(),
                    this));
        }
        return super.updateProcess();
    }

    @Override
    protected Color createColor() {
        return new Color(random(0.4f, 0.6f), random(0.6f, 0.9f), random(0.5f, 0.7f));
    }

}
