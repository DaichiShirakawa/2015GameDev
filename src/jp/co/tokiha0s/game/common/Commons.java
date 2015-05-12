package jp.co.tokiha0s.game.common;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * ゲーム全体に関わる各種定数の定義
 * およびRandom等ゲーム内利用の汎用オブジェクトを保持
 *
 * @author shirakawa
 *
 */
public final class Commons {
	private Commons() {
		// hide
	}

	public static final String PRODUCT_TITLE = "2015GamePrj";
	public static final String LWJGL_NATIVE_PROPERTY_NAME = "org.lwjgl.librarypath";

	/**
	 * ウィンドウ・描写領域
	 */
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 800;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	public static final int DEPTH = 200;

	/**
	 * 汎用座標
	 */
	public static final int CENTER_X = WIDTH / 2;
	public static final int CENTER_Y = HEIGHT / 2;

	/**
	 * リソースへのパス
	 */
	public static final String RESOURCE_FOLDER_STRING = "./resources/";
	public static final String IMAGE_FOLDER_STRING = RESOURCE_FOLDER_STRING
			+ "image/";
	public static final String TOKISHOOTING_FOLDER_STRING = IMAGE_FOLDER_STRING
			+ "tokishooting/";
	public static final String WINDOWS_NATIVE_FOLDER_STRING = "./lib/lwjgl/native/windows";
	public static final String FONT_MSGOTHIC_FILEPATH = RESOURCE_FOLDER_STRING
			+ "font/MSGOTHIC.TTC";
	public static final String FONT_RICTY_FILEPATH = RESOURCE_FOLDER_STRING
			+ "font/Ricty.ttf";

	/**
	 * ゲーム中枢
	 */
	public static final int FPS = 60;
	public static final Random RANDOM = new Random(System.nanoTime());

	/**
	 * テキスト処理関連
	 */
	public static final DecimalFormat FORMAT_FLOAT_0D0 = new DecimalFormat(
			"0.0");

}
