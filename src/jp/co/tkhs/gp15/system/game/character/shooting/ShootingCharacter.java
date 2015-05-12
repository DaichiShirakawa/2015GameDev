package jp.co.tkhs.gp15.system.game.character.shooting;

import jp.co.tkhs.gp15.system.game.character.GameCharacter;
import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingBulletCharacter;
import jp.co.tkhs.gp15.system.game.character.shooting.impl.ShootingCharacterImpl.ShootingTeam;
import jp.co.tkhs.gp15.system.game.scene.ShootingScene;

/**
 * シューティングキャラクターの基底クラス
 * 
 * @author tokiha0s
 *
 */
public interface ShootingCharacter extends GameCharacter {

    ShootingTeam getTeam();

    void setTeam(ShootingTeam team);

    boolean isEnemyTeam(GameCharacter target);

    ShootingScene getParentScene();

    float getPower();

    float getHP();

    boolean zeroHP();

    float damage(float damage);

    boolean shoot(ShootingBulletCharacter bullet);

    void hitEffectTo(ShootingCharacter target);
}
