package jp.co.tokiha0s.game.common;


/**
 * 左右を扱いたいときのための定義
 * 
 * @author shirakawa
 *
 */
public enum LR {
	LEFT(-1),
	RIGHT(1);
	private int signum;

	private LR(int signum) {
		this.signum = signum;
	}

	public LR toggle() {
		return (this == LEFT) ? RIGHT : LEFT;
	}

	public int signum() {
		return signum;
	}
}