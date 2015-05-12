package jp.co.tkhs.gp15.system.game.character.impl;


/**
 * ちょっとした用途に使いたいただのキャラクター
 * 
 * @author tokiha0s
 *
 */
public class SimpleCharacter extends GameCharacterImpl {
    @Override
    protected boolean canDisposeTexture() {
        return false;
    }
}
