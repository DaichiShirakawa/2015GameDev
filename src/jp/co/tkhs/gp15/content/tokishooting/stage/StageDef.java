package jp.co.tkhs.gp15.content.tokishooting.stage;

import java.lang.reflect.InvocationTargetException;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;

/**
 * ステージの順番やタイトルの定義
 * 
 * @author tokiha0s
 *
 */
enum StageDef {
    STAGE_01(Stage1.class, "VS さかな"), STAGE_02(Stage2.class, "VS いか"), STAGE_03(Stage3.class,
            "VS メカとき"), STAGE_04(Stage4.class, "だいけっしゅう"), STAGE_05(Stage5.class, "ぜったい ほうい"), STAGE_FINAL(
            Stage6.class, "VS ビッグメカとき"), CLEAR(null, null);

    private Class<? extends StageBase> stageClass;
    private String descript;

    private StageDef(Class<? extends StageBase> stageClass, String descript) {
        this.stageClass = stageClass;
        this.descript = descript;
    }

    private StageBase getInstance(TokiShootingScene scene) {
        if (stageClass == null) {
            return null;
        }

        try {
            return stageClass.getConstructor(TokiShootingScene.class).newInstance(scene);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            System.err.println("ステージの生成に失敗しました");
            e.printStackTrace();
        }
        return null;
    }

    private static StageDef currentStage = null;
    private static final StageDef FIRST_STAGE = STAGE_01;

    public static StageBase getNextStage(TokiShootingScene scene) {
        if (currentStage == null) {
            currentStage = FIRST_STAGE;
            return currentStage.getInstance(scene);
        }

        try {
            currentStage = values()[currentStage.ordinal() + 1];
            return currentStage.getInstance(scene);
        } catch (ArrayIndexOutOfBoundsException e) {
            currentStage = null;
            return null;
        }
    }

    public static String getStageName() {
        if (currentStage == null) {
            return "";
        }
        return currentStage.name();
    }

    public static String getStageDescript() {
        if (currentStage == null) {
            return "";
        }
        return currentStage.descript;
    }

    public static void reset() {
        currentStage = null;
    }

    public static boolean allClear() {
        return currentStage == CLEAR;
    }
}
