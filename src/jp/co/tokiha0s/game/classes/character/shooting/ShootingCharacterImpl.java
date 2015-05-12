package jp.co.tokiha0s.game.classes.character.shooting;

import java.util.HashSet;
import java.util.Set;

import jp.co.tokiha0s.game.classes.character.GameCharacter;
import jp.co.tokiha0s.game.classes.character.GameCharacterImpl;
import jp.co.tokiha0s.game.classes.scene.ShootingScene;

/**
 * シューティングキャラクターの実装クラス
 * 
 * @author shirakawa
 *
 */
public abstract class ShootingCharacterImpl extends GameCharacterImpl implements
		ShootingCharacter {
	private ShootingScene parentScene;
	private ShootingTeam team;
	private float power;
	private float hp;
	private boolean undead = false;
	private Set<ShootingCharacter> hittedObjects = new HashSet<>();

	public ShootingCharacterImpl(ShootingScene scene, float power) {
		this.parentScene = scene;
		this.power = power;
		this.undead = true;
	}

	public ShootingCharacterImpl(ShootingScene scene, float power, float hp) {
		this.parentScene = scene;
		this.power = power;
		this.hp = hp;
	}

	@Override
	public ShootingTeam getTeam() {
		return team;
	}

	@Override
	public void setTeam(ShootingTeam division) {
		this.team = division;
	}

	@Override
	public boolean isEnemyTeam(GameCharacter target) {
		if (!(target instanceof ShootingCharacterImpl)) {
			return false;
		}
		switch (getTeam()) {
		case FRIEND_TEAM:
			return ((ShootingCharacterImpl) target).getTeam() == ShootingTeam.ENEMY_TEAM;
		case ENEMY_TEAM:
			return ((ShootingCharacterImpl) target).getTeam() == ShootingTeam.FRIEND_TEAM;
		default:
			return false;
		}
	}

	@Override
	public ShootingScene getParentScene() {
		return parentScene;
	}

	protected void setParentScene(ShootingScene parentScene) {
		this.parentScene = parentScene;
	}

	@Override
	public float getPower() {
		return power;
	}

	@Override
	public boolean zeroHP() {
		return !undead && hp == 0;
	}

	@Override
	public float getHP() {
		return hp;
	}

	@Override
	public float damage(float damage) {
		if (undead) {
			return Float.MAX_VALUE;
		}
		hp -= damage;
		if (hp < 0) {
			hp = 0;
		}
		if (zeroHP()) {
			destroy();
		}
		return getHP();
	}

	@Override
	protected boolean canDisposeTexture() {
		return false;
	}

	@Override
	public ShootingBulletCharacter shoot(ShootingBulletCharacter bullet) {
		bullet.setParentScene(parentScene);
		return parentScene.add(bullet);
	}

	@Override
	public boolean checkHit(GameCharacter target) {
		boolean notShootingCharacter = !(target instanceof ShootingCharacter);
		boolean notEnemyForces = !isEnemyTeam(target);
		if (notShootingCharacter || notEnemyForces
				|| hittedObjects.contains(target)) {
			return false;
		}
		boolean noHit = !super.checkHit(target);
		if (noHit) {
			return false;
		}
		return true;
	}

	@Override
	public void hitEffectTo(ShootingCharacter target) {
		hittedObjects.add(target);
		target.damage(getPower());
	}

	public enum ShootingTeam {
		FRIEND_TEAM,
		ENEMY_TEAM,
		NO_TEAM,
	}
}
