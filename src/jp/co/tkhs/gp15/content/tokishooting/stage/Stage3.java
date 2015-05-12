package jp.co.tkhs.gp15.content.tokishooting.stage;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.system.game.utils.LR;

class Stage3 extends StageBase {

    public Stage3(TokiShootingScene parentScene) {
        super(parentScene);
    }

    @Override
    protected void createSpawns() {
        int frame = 0;
        float angle = 0;
        LR lr = LR.RIGHT;

        tokiSpawn(frame += 60, angle, lr = lr.toggle());
        tokiSpawn(frame += 600, angle, lr = lr.toggle());
    }

}
