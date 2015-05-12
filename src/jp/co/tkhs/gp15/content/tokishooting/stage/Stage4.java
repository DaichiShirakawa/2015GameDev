package jp.co.tkhs.gp15.content.tokishooting.stage;

import jp.co.tkhs.gp15.content.tokishooting.TokiShootingScene;
import jp.co.tkhs.gp15.system.game.utils.LR;

class Stage4 extends StageBase {

    public Stage4(TokiShootingScene parentScene) {
        super(parentScene);
    }

    @Override
    protected void createSpawns() {
        int frame = 0;
        float angle = 0;
        LR lr = LR.RIGHT;

        standardSpawn(frame += 60, angle = 45);
        standardSpawn(frame += 60, angle += 5);
        standardSpawn(frame += 60, angle += 5);

        rotateSpawn(frame += 120, angle = 0, lr);
        rotateSpawn(frame += 60, angle, lr);
        rotateSpawn(frame += 60, angle, lr);

        standardSpawn(frame += 180, angle = 225);
        standardSpawn(frame += 60, angle += 5);
        standardSpawn(frame += 60, angle += 5);

        rotateSpawn(frame += 120, angle = 90, lr);
        rotateSpawn(frame += 60, angle, lr);
        rotateSpawn(frame += 60, angle, lr);

        standardSpawn(frame += 180, angle = 135);
        standardSpawn(frame += 60, angle += 5);
        standardSpawn(frame += 60, angle += 5);

        tokiSpawn(frame += 60, angle = 180, lr = lr.toggle());

        rotateSpawn(frame += 120, angle = 180, lr);
        rotateSpawn(frame += 60, angle, lr);
        rotateSpawn(frame += 60, angle, lr);

        standardSpawn(frame += 120, angle = 315);
        standardSpawn(frame += 60, angle += 5);
        standardSpawn(frame += 60, angle += 5);

        rotateSpawn(frame += 120, angle = 270, lr);
        rotateSpawn(frame += 60, angle, lr);
        rotateSpawn(frame += 60, angle, lr);

        rotateSpawn(frame += 180, angle = 45, lr = lr.toggle());
        rotateSpawn(frame += 60, angle, lr);
        rotateSpawn(frame += 60, angle, lr);

        tokiSpawn(frame += 60, angle = 0, lr = lr.toggle());
    }

}
