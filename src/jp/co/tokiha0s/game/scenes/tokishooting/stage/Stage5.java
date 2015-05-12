package jp.co.tokiha0s.game.scenes.tokishooting.stage;

import jp.co.tokiha0s.game.common.LR;
import jp.co.tokiha0s.game.scenes.tokishooting.TokiShootingScene;

class Stage5 extends StageBase {

	public Stage5(TokiShootingScene parentScene) {
		super(parentScene);
	}

	@Override
	protected void createSpawns() {
		int frame = 0;
		float angle = 0;
		LR lr = LR.RIGHT;

		rotateSpawn(frame += 60, angle = 0, lr);
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());
		rotateSpawn(frame += 45, angle += 15, lr.toggle());

		standardSpawn(frame += 600, angle = 0);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
		standardSpawn(frame += 90, angle += 15);
	}
	
	

}
