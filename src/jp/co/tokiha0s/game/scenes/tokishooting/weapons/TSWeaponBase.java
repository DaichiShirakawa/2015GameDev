package jp.co.tokiha0s.game.scenes.tokishooting.weapons;

import jp.co.tokiha0s.game.classes.character.shooting.ShootingRotateCharacter;
import jp.co.tokiha0s.game.common.LR;
import jp.co.tokiha0s.game.main.FPSManager;
import jp.co.tokiha0s.game.scenes.tokishooting.TokiShootingScene;

/**
 * 武器の基底クラス
 * 
 * @author shirakawa
 *
 */
public abstract class TSWeaponBase extends ShootingRotateCharacter {
	private ShootingRotateCharacter owner;
	private int remainShootDelayFrame;
	private int remainReloadDelayFrame;
	private long lastReloadFrame;
	private int remainBullet;
	// 元値に対する弾丸の売却レート
	private static final float SELL_RATE = (2f / 3f);

	public TSWeaponBase(TokiShootingScene scene, ShootingRotateCharacter owner,
			LR equipLR) {
		super(scene, 0f);
		this.owner = owner;
		this.remainBullet = 0;
		setTeam(owner.getTeam());
		setOffsetY(owner.getOffsetY());
		setOffsetX(getDistanceFromOwner() * equipLR.signum());
		setAngle(owner.getAngle());
	}

	@Override
	public TokiShootingScene getParentScene() {
		return (TokiShootingScene) super.getParentScene();
	}

	@Override
	public boolean updateProcess() {
		setAngle(owner.getAngle());
		setOffsetY(owner.getOffsetY());
		remainShootDelayFrame += (remainShootDelayFrame == 0) ? 0 : -1;
		return super.updateProcess();
	}

	public void shoot() {
		if (remainBullet <= 0) {
			getParentScene().showCaution("RELOAD!");
			return;
		}
		if (0 < remainShootDelayFrame) {
			return;
		}
		remainBullet--;
		remainShootDelayFrame = getShootDelayFrame();
		getParentScene().add(getBulletInstance());
	}

	/**
	 * 発射弾丸を生成する
	 */
	protected abstract TSBulletBase getBulletInstance();

	public void reloadUpdate() {
		if (!reloadContinue() || getMaxBullet() <= remainBullet) {
			remainReloadDelayFrame = getReloadDelayFrame();
		}
		if (0 < remainReloadDelayFrame) {
			lastReloadFrame = FPSManager.totalFrame();
			remainReloadDelayFrame--;
			return;
		}
		if (getReloadCost() <= getParentScene().getMoney()) {
			getParentScene().addMoney(-getReloadCost());
			remainBullet++;
		} else {
			
			getParentScene().showCaution("お金が足りません");
		}
		remainReloadDelayFrame = getReloadDelayFrame();
	}

	private boolean reloadContinue() {
		return lastReloadFrame == FPSManager.totalFrame() - 1;
	}

	abstract protected float getDistanceFromOwner();

	public int getMaxDelayFrame() {
		if (lastReloadFrame == FPSManager.totalFrame()) {
			return getReloadDelayFrame();
		} else {
			return getShootDelayFrame();
		}
	}

	abstract protected int getShootDelayFrame();

	protected int getReloadDelayFrame() {
		return (int) (getShootDelayFrame() * 0.3f);
	}

	abstract public int getMaxBullet();

	public int getRemainBullet() {
		return remainBullet;
	}

	public int getRemainDelayFrame() {
		if (lastReloadFrame == FPSManager.totalFrame()) {
			return getReloadDelayFrame() - remainReloadDelayFrame;
		} else {
			return remainShootDelayFrame;
		}
	}

	/**
	 * 弾丸一発のリロードにかかる金額
	 */
	public abstract int getReloadCost();

	/**
	 * 武器を交換(現在持っている弾丸を売却)した時に得られる金額
	 */
	public int getSellValue() {
		return (int) (getReloadCost() * getRemainBullet() * SELL_RATE);
	}

	public void addBullet(int count) {
		remainBullet = Math.min(remainBullet + count, getMaxBullet());
	}
}