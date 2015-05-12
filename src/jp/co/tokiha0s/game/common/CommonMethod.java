package jp.co.tokiha0s.game.common;

import static jp.co.tokiha0s.game.common.Commons.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import jp.co.tokiha0s.game.texture.Texture;

/**
 * 汎用関数集約クラス
 * 
 * @author shirakawa
 * 
 */
public final class CommonMethod {
	private CommonMethod() {
		// hide
	}

	/**
	 * min <= r <= max の範囲で乱数rを生成
	 */
	public static float random(final float min, final float max) {
		float dist = max - min;
		return (float) (Math.random() * dist + min);
	}

	/**
	 * 百分率でtrueになる関数
	 * 
	 * @param odds
	 * @return odds%の確率でtrue
	 */
	public static boolean persentOf(float odds) {
		return random(0, 100) <= odds;
	}

	/**
	 * 背景デフォルト色を指定
	 */
	public enum BackGroundColor {
		WHITE(1f, 1f, 1f, 1f),
		BLACK(0f, 0f, 0f, 1f),
		DARK_BLUE(0f, 0f, 0.2f, 1f),;

		private final float red;
		private final float green;
		private final float blue;
		private final float alpha;

		private BackGroundColor(float red, float green, float blue, float alpha) {
			this.red = red;
			this.green = green;
			this.blue = blue;
			this.alpha = alpha;
		}

		public void set() {
			glClearColor(red, green, blue, alpha);
		}
	}

	/**
	 * テクスチャつきポリゴンを生成
	 */
	public static void drawTexture(final Texture texture, final int width,
			final int height) {
		texture.bind();

		// 四角のポリゴン
		glBegin(GL_QUADS);

		texture.point(texture.getWidth(), 0);
		glVertex3f(width / 2, height / 2, 0);
		texture.point(0, 0);
		glVertex3f(-width / 2, height / 2, 0);
		texture.point(0, texture.getHeight());
		glVertex3f(-width / 2, -height / 2, 0);
		texture.point(texture.getWidth(), texture.getHeight());
		glVertex3f(width / 2, -height / 2, 0);

		glEnd();
	}

	/**
	 * 浮動小数点を"0.0"形式にフォーマットする
	 */
	public static String floatTo0d0(final double val) {
		return FORMAT_FLOAT_0D0.format(val);
	}

	/**
	 * Colorクラスを使用してテクスチャ色をセット
	 */
	public static void setGlColor4f(final Color color, final float alpha) {
		if (color == null) {
			glColor4f(1, 1, 1, alpha);
			return;
		}
		glColor4f(color.getRed() / 255f, color.getGreen() / 255f,
				color.getBlue() / 255f, alpha);
	}

	/**
	 * 赤紫～白のきれいなコスモス色を生成
	 */
	public static Color generateCosmosColor() {
		float rand = random(0f, 1.4f);
		float r = 1f;
		float g = (1f < rand) ? rand - 1f : 0f;
		float b = (rand <= 1f) ? rand : 0f;

		// ランダムで色を白に近づける
		rand = random(0.3f, 1f);
		r += ((1f - r) * rand);
		g += ((1f - g) * rand);
		b += ((1f - b) * rand);

		// ランダムで色を黒に近づける
		rand = random(0.95f, 1f);
		r *= rand;
		g *= rand;
		b *= rand;

		return new Color(r, g, b);
	}

	/**
	 * 受け取ったangleを 0 <= angle < 360 に正規化して返す<br>
	* (ex: in>out) 361>1, 360>0, -1>359
	 */
	public static float toAbsoluteAngle(float angle) {
		angle %= 360;
		if (angle < 0) {
			angle += 360;
		}
		return angle;
	}
}
