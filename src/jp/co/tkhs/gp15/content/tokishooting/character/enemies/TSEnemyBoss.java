package jp.co.tkhs.gp15.content.tokishooting.character.enemies;

import static jp.co.tkhs.gp15.system.Constants.*;

import java.awt.Color;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.main.FPSManager;
import jp.co.tkhs.gp15.system.Constants;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.game.utils.LR;
import jp.co.tkhs.gp15.system.graphics.Texture;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * ボス敵
 * 
 * @author tokiha0s
 *
 */
public class TSEnemyBoss extends TSEnemyBase {
    private static final int POWER = 1;
    private static final int HP = 120;
    private static final int SIZE = 100;
    private static final Texture TEXTURE = TextureLoader.loadTexture(Constants.IMAGE_FOLDER_STRING
            + "dotTokiIcon.png");
    private static final float ROTATE_SPEED = 0.2f;
    private static final float FALL_SPEED = 0.3f;
    private static final int MONEY = 300;
    private static final TSEnemyProperty property = new TSEnemyProperty(POWER, HP, SIZE, TEXTURE,
            ROTATE_SPEED, FALL_SPEED, MONEY);

    public TSEnemyBoss(ShootingScene parentScene, float bornAngle, LR lr) {
        super(parentScene, bornAngle, lr, property);
    }

    @Override
    protected boolean updateProcess() {
        if (getOffsetY() < WIDTH / 2) {
            setVOffsetY(getVOffsetY() * 0.95f);
        }
        // 定期的に中ボスを生み出す
        if (FPSManager.getTotalFrame() % 900 == 0) {
            ((TokiShootingScene) getParentScene()).add(new TSEnemyToki(getParentScene(),
                    getAngle(), LR.RIGHT));
        }
        return super.updateProcess();
    }

    @Override
    protected Color createColor() {
        return new Color(1f, 0.1f, 0.3f);
    }

}
