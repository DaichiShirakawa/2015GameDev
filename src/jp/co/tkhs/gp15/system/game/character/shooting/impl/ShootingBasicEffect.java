package jp.co.tkhs.gp15.system.game.character.shooting.impl;

import static java.lang.Math.*;
import static jp.co.tkhs.gp15.system.Constants.*;
import static jp.co.tkhs.gp15.system.game.utils.GameUtils.*;
import jp.co.tkhs.gp15.system.game.character.shooting.ShootingCharacter;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;
import jp.co.tkhs.gp15.system.graphics.Texture;

/**
 * sourceの座標から、sourceのテクスチャのエフェクトがランダムな大きさで飛び散る。
 * 
 * @author tokiha0s
 * 
 */
public class ShootingBasicEffect extends ShootingEffectCharacter {
    public ShootingBasicEffect(ShootingScene parentScene, ShootingCharacter source) {
        super(parentScene, source);
        setScale(random(0.5f, 2f));
        setColor(source.getColor());

        setAngle(RANDOM.nextInt(360));
        setMoveSpeeds();
    }

    protected void setMoveSpeeds() {
        float tmp = RANDOM.nextInt(360);
        setVX(3 * (float) sin(tmp) * random(0.2f, 1f));
        setVY(3 * (float) cos(tmp) * random(0.2f, 1f));
    }

    @Override
    public int getBulletSize() {
        return (int) (getShooter().getWidth() * random(0.2f, 0.4f));
    }

    @Override
    public Texture getBulletTexture() {
        return getShooter().getTexture();
    }

    @Override
    protected float getLifeTime() {
        return 0.8f * random(0.5f, 1.5f);
    }

    @Override
    public boolean updateProcess() {
        setVX(getVX() * 0.95f);
        setVY(getVY() * 0.95f);
        return super.updateProcess();
    }

}
