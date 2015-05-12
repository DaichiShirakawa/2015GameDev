package jp.co.tkhs.gp15.content.tokishooting.stage;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.system.game.utils.LR;

class Stage6 extends StageBase {

    public Stage6(TokiShootingScene parentScene) {
        super(parentScene);
    }

    @Override
    protected void createSpawns() {
        bossSpawn(0, 0, LR.LEFT);
    }



}
