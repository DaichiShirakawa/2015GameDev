package jp.co.tokiha0s.game.scenes.tokishooting.stage;

import jp.co.tokiha0s.game.common.LR;
import jp.co.tokiha0s.game.scenes.tokishooting.TokiShootingScene;

class Stage6 extends StageBase {

	public Stage6(TokiShootingScene parentScene) {
		super(parentScene);
	}

	@Override
	protected void createSpawns() {
		bossSpawn(0, 0, LR.LEFT);
	}
	
	

}
