package jp.co.tkhs.gp15.content.tokishooting.character.friendlies.earth;

import static jp.co.tkhs.gp15.system.Constants.*;

import java.awt.Color;

import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingCharacterImpl;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.graphics.Texture;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * 画面中央の地球
 * 
 * @author tokiha0s
 *
 */
public class TSEarth extends ShootingCharacterImpl {
    private static final int SIZE = 35;
    private static final float JITEN = 0.1f;
    private static final Texture TEXTURE = TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING
            + "earth.png");

    public TSEarth(ShootingScene scene) {
        super(scene, Float.MAX_VALUE, 10);
        setTexture(TEXTURE);
        setTeam(ShootingTeam.FRIEND_TEAM);
        setX(CENTER_X);
        setY(CENTER_Y);
        setWidth(SIZE);
        setHeight(SIZE);
        setVAngle(JITEN);

        add(new HPCaption(this));
        add(new NormalBasion(getParentScene(), this));
        add(new SnipeBasion(getParentScene(), this));
        add(new BombBasion(getParentScene(), this));
    }

    @Override
    protected void destroyProcess() {
        setColor(Color.orange);
    }
}
