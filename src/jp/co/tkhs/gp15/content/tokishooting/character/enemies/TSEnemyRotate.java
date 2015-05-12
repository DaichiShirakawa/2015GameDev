package jp.co.tkhs.gp15.content.tokishooting.character.enemies;

import static jp.co.tkhs.gp15.system.Constants.*;

import java.awt.Color;

import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.game.utils.LR;
import jp.co.tkhs.gp15.system.graphics.Texture;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * 回転軌道でせまってくる敵
 * 
 * @author tokiha0s
 *
 */
public class TSEnemyRotate extends TSEnemyBase {
    private static final int POWER = 1;
    private static final int HP = 1;
    private static final int SIZE = 25;
    private static final Texture TEXTURE = TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING
            + "ika.png");
    private static final float ROTATE_SPEED = 0.2f;
    private static final float FALL_SPEED = 0.2f;
    private static final int MONEY = 10;
    private static final TSEnemyProperty property = new TSEnemyProperty(POWER, HP, SIZE, TEXTURE,
            ROTATE_SPEED, FALL_SPEED, MONEY);

    public TSEnemyRotate(ShootingScene parentScene, float bornAngle, LR lr) {
        super(parentScene, bornAngle, lr, property);
    }

    @Override
    protected Color createColor() {
        return null;
    }

}
