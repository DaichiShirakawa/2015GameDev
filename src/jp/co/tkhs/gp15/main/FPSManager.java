package jp.co.tkhs.gp15.main;

import static jp.co.tkhs.gp15.system.Constants.*;

/**
 * FPS計算を行うクラス<br>
 * 現在のフレーム周期（ex.FPS60なら0～59）や<br>
 * ゲーム開始からの経過フレーム数も取得できる
 *
 * @author tokiha0s
 *
 */
public class FPSManager {
    private static final long FRAME_PERIOD_NS = (long) (1.0 / FPS * 1_000_000_000);
    private static final long FPS_CALC_INTERVAL_NS = 1_000_000_000;

    private static final FPSManager instance = new FPSManager();

    private long elapsed_ns = 0;
    private long frameCycle = 0; // 0 <= frameScycle < FPS
    private long totalFrame = 0;
    private long prevCalcTime_ns = System.nanoTime();
    private double calcedFPS = 0.0;

    private FPSManager() {
        // hide
    }

    public static void update() {
        instance.updateProcess();
    }

    private void updateProcess() {
        elapsed_ns += FRAME_PERIOD_NS;
        frameCycle++;
        totalFrame++;

        if (FPS_CALC_INTERVAL_NS <= elapsed_ns) {
            long currentTime_ns = System.nanoTime();
            long realElapsedTime_ns = currentTime_ns - prevCalcTime_ns;

            calcedFPS = ((double) (frameCycle) / realElapsedTime_ns) * 1_000_000_000;

            elapsed_ns = 0;
            frameCycle = 0;
            prevCalcTime_ns = currentTime_ns;
        }
    }

    /**
     * 現時点のフレームはFPSサイクルの中のどの位置か<br>
     * (ex: 60FPSなら0-59のいずれか)
     *
     * @return FPSサイクル
     */
    public static long getFrameCycle() {
        return instance.frameCycle;
    }

    /**
     * @return ゲーム起動からの経過フレーム数
     */
    public static long getTotalFrame() {
        return instance.totalFrame;
    }

    /**
     * @return 最新のFPS計算結果
     */
    public static double getCalcedFPS() {
        return instance.calcedFPS;
    }
}
