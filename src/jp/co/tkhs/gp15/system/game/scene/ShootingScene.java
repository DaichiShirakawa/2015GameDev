package jp.co.tkhs.gp15.system.game.scene;

import java.util.List;

import jp.co.tkhs.gp15.system.game.GameObject;
import jp.co.tkhs.gp15.system.game.character.shooting.ShootingCharacter;

/**
 * シューティング用基底シーン
 * 
 * @author tokiha0s
 *
 */
abstract public class ShootingScene extends GameScene {
    @Override
    public boolean updateProcess() {
        checkHit();
        return true;
    }

    private void checkHit() {
        List<GameObject> childrenList = getChildrenCopy();

        for (int i = 0; i < childrenList.size() - 1; i++) {
            for (int j = i + 1; j < childrenList.size(); j++) {
                try {
                    ShootingCharacter so1 = (ShootingCharacter) childrenList.get(i);
                    ShootingCharacter so2 = (ShootingCharacter) childrenList.get(j);
                    if (so1.checkHit(so2)) {
                        so1.hitEffectTo(so2);
                        so2.hitEffectTo(so1);
                    }
                } catch (ClassCastException e) {
                    continue;
                }
            }
        }
    }
}
