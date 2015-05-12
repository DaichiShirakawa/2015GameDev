package jp.co.tkhs.gp15.content.tokishooting.character.enemies;

import static jp.co.tkhs.gp15.system.Constants.*;

import java.awt.Color;

import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.game.utils.LR;
import jp.co.tkhs.gp15.system.graphics.Texture;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * 直進する敵
 * 
 * @author tokiha0s
 *
 */
public class TSEnemyStandard extends TSEnemyBase {
    private static final int POWER = 1;
    private static final int HP = 3;
    private static final int SIZE = 20;
    private static final Texture TEXTURE = TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING
            + "fish.png");
    private static final float ROTATE_SPEED = 0;
    private static final float FALL_SPEED = 0.3f;
    private static final int MONEY = 5;
    private static final TSEnemyProperty property = new TSEnemyProperty(POWER, HP, SIZE, TEXTURE,
            ROTATE_SPEED, FALL_SPEED, MONEY);

    public TSEnemyStandard(ShootingScene parentScene, float bornAngle, LR lr) {
        super(parentScene, bornAngle, lr, property);
    }

    @Override
    protected Color createColor() {
        return null;
    }
}
