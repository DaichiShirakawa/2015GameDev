package jp.co.tokiha0s.game.scenes.tokishooting.characters.enemies;

import jp.co.tokiha0s.game.texture.Texture;

/**
 * 敵の状態を集約する構造体クラス
 * 
 * @author shirakawa
 *
 */
public class TSEnemyProperty {
	public final int power;
	public final int hp;
	public final int size;
	public final Texture texture;
	public final float rotateSpeed;
	public final float fallSpeed;
	public final int money;

	public TSEnemyProperty(int power, int hp, int size, Texture texture,
			float rotateSpeed, float fallSpeed, int money) {
		super();
		this.power = power;
		this.hp = hp;
		this.size = size;
		this.texture = texture;
		this.rotateSpeed = rotateSpeed;
		this.fallSpeed = fallSpeed;
		this.money = money;
	}
}
