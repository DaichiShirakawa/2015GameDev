package jp.co.tokiha0s.game.main;

import static jp.co.tokiha0s.game.common.CommonMethod.*;
import static jp.co.tokiha0s.game.common.Commons.*;

import org.lwjgl.opengl.Display;

/**
 * FPSの制御および計算を管理するシングルトン
 * 現在のフレーム周期（ex.FPS60なら0～59）や
 * ゲーム開始からの経過フレーム数も取得できる
 * 
 * @author shirakawa
 * 
 */
public class FPSManager {
	private static final long FRAME_PERIOD_NS = (long) (1.0 / FPS * 1_000_000_000);
	private static final long INTERVAL_CAP_NS = 1_000_000_000;

	private static FPSManager instance = null;

	private long fpsCalcInterval_ns = 0;
	private long prevFrameTime_ns = 0;
	private double calculatedFPS = 0.0;
	private long currentFrame = 0; // 0 <= currentFrame < FPS
	private long framesUntilStart = 0;

	private FPSManager() {
		// hide
	}

	public static FPSManager getInstance() {
		if (instance == null) {
			instance = new FPSManager();
		}
		return instance;
	}

	public void update() {
		fpsCalcInterval_ns += FRAME_PERIOD_NS;
		currentFrame++;
		framesUntilStart++;

		if (fpsCalcInterval_ns >= INTERVAL_CAP_NS) {
			long currentFrameTime_ns = System.nanoTime();
			long realElapsedTime_ns = currentFrameTime_ns - prevFrameTime_ns;

			calculatedFPS = ((double) (currentFrame) / realElapsedTime_ns) * 1_000_000_000;

			currentFrame = 0;
			fpsCalcInterval_ns = 0;
			prevFrameTime_ns = currentFrameTime_ns;
		}

		updateDisplay();
	}

	private void updateDisplay() {
		Display.update();
		Display.sync(FPS);
		Display.setTitle("FRAME:" + String.valueOf(cycleFrame()) + "FPS:"
				+ floatTo0d0(calculatedFPS));
	}

	public static long cycleFrame() {
		return getInstance().currentFrame;
	}
	
	public static long totalFrame() {
		return getInstance().framesUntilStart;
	}
}
