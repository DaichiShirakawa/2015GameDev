package jp.co.tokiha0s.game.classes.character.shooting;

import jp.co.tokiha0s.game.classes.character.GameCharacter;
import jp.co.tokiha0s.game.classes.character.shooting.ShootingCharacterImpl.ShootingTeam;
import jp.co.tokiha0s.game.classes.scene.ShootingScene;

/**
 * シューティングキャラクターの基底クラス
 * 
 * @author shirakawa
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

	ShootingBulletCharacter shoot(ShootingBulletCharacter bullet);

	void hitEffectTo(ShootingCharacter target);
}