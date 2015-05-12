package jp.co.tkhs.gp15.content.tokishooting.weapons.bomb;

import static jp.co.tkhs.gp15.system.Constants.*;
import static jp.co.tkhs.gp15.system.game.utils.GameUtils.*;

import java.awt.Color;

import jp.co.tkhs.gp15.system.game.character.shooting.ShootingCharacter;
import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingBasicEffect;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.graphics.Texture;
import jp.co.tkhs.gp15.system.graphics.TextureLoader;

/**
 * ボム弾やボム命中時に発生する煙エフェクト
 * 
 * @author tokiha0s
 *
 */
class SmokeEffect extends ShootingBasicEffect {
    private static final Texture SMOKETEXTURE = TextureLoader
            .loadTexture(TOKISHOOTING_FOLDER_STRING + "smoke.png");
    private boolean reverse;

    public SmokeEffect(ShootingScene parentScene, ShootingCharacter source, boolean reverse) {
        super(parentScene, source);
        this.reverse = reverse;
        float tmp = random(0.5f, 1);
        setColor(new Color(tmp, tmp, tmp));
        setWidth(20);
        setHeight(20);
        setScale(tmp = random(0.4f, 0.7f));
        setTexture(SMOKETEXTURE);
    }

    @Override
    protected void setMoveSpeeds() {
        if (reverse) {
            setVX(-getShooter().getVX() * 0.3f);
            setVY(-getShooter().getVY() * 0.3f);
        } else {
            setVX(getShooter().getVX() * 0.3f);
            setVY(getShooter().getVY() * 0.3f);
        }
    }

}
