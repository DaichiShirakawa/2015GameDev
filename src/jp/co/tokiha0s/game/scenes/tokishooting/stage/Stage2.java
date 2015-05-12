package jp.co.tokiha0s.game.scenes.tokishooting.stage;

import jp.co.tokiha0s.game.common.LR;
import jp.co.tokiha0s.game.scenes.tokishooting.TokiShootingScene;

class Stage2 extends StageBase {

	public Stage2(TokiShootingScene parentScene) {
		super(parentScene);
	}

	@Override
	protected void createSpawns() {
		int frame = 0;
		float angle = 0;
		LR lr = LR.RIGHT;
		rotateSpawn(frame += 60, angle += 90, lr = lr.toggle());
		rotateSpawn(frame += 60, angle, lr);
		rotateSpawn(frame += 60, angle, lr);

		rotateSpawn(frame += 120, angle, lr = lr.toggle());
		rotateSpawn(frame += 60, angle, lr);
		rotateSpawn(frame += 60, angle, lr);

		rotateSpawn(frame += 120, angle += 180, lr = lr.toggle());
		rotateSpawn(frame += 60, angle, lr);
		rotateSpawn(frame += 60, angle, lr);

		rotateSpawn(frame += 120, angle, lr = lr.toggle());
		rotateSpawn(frame += 60, angle, lr);
		rotateSpawn(frame += 60, angle, lr);
	}

}
