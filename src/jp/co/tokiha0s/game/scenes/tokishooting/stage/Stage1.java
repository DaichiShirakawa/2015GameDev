package jp.co.tokiha0s.game.scenes.tokishooting.stage;

import jp.co.tokiha0s.game.scenes.tokishooting.TokiShootingScene;

class Stage1 extends StageBase {

	public Stage1(TokiShootingScene parentScene) {
		super(parentScene);
	}

	@Override
	protected void createSpawns() {
		int frame = 0;
		float angle = 0;
		standardSpawn(frame, angle);
		standardSpawn(frame += 120, angle);
		standardSpawn(frame += 60, angle += 90);
		standardSpawn(frame += 120, angle);
		standardSpawn(frame += 60, angle += 90);
		standardSpawn(frame += 120, angle);
		standardSpawn(frame += 60, angle += 90);
		standardSpawn(frame += 120, angle);
		standardSpawn(frame += 60, angle = 45);
		standardSpawn(frame += 120, angle);
		standardSpawn(frame += 60, angle += 90);
		standardSpawn(frame += 120, angle);
		standardSpawn(frame += 60, angle += 90);
		standardSpawn(frame += 120, angle);
		standardSpawn(frame += 60, angle += 90);
		standardSpawn(frame += 120, angle);
	}

}
