package jp.co.tkhs.gp15.content.tokishooting.gui.weaponcaption;

import java.util.ArrayList;
import java.util.List;

import jp.co.tkhs.gp15.system.game.character.enums.GameCharacterBasePoint;
import jp.co.tkhs.gp15.system.game.character.impl.TextCharacter;
import jp.co.tkhs.gp15.system.graphics.Texture;
import jp.co.tkhs.gp15.system.graphics.font.FontTextureMaker;

/**
 * 武器の射撃ディレイを可視化するGUI
 * 
 * @author tokiha0s
 * 
 */
class DelayBar extends TextCharacter {
    // HACK テクスチャをいっぱい用意するイケてない方法。
    private static final List<Texture> textureList = new ArrayList<>();
    static {
        textureList.add(null);
        textureList.add(FontTextureMaker.createText("■"));
        textureList.add(FontTextureMaker.createText("■■"));
        textureList.add(FontTextureMaker.createText("■■■"));
        textureList.add(FontTextureMaker.createText("■■■■"));
        textureList.add(FontTextureMaker.createText("■■■■■"));
        textureList.add(FontTextureMaker.createText("■■■■■■"));
        textureList.add(FontTextureMaker.createText("■■■■■■■"));
        textureList.add(FontTextureMaker.createText("■■■■■■■■"));
        textureList.add(FontTextureMaker.createText("■■■■■■■■■"));
        textureList.add(FontTextureMaker.createText("■■■■■■■■■■"));
    }

    private OddWeaponGUI parent;

    public DelayBar(OddWeaponGUI parent) {
        super();
        this.parent = parent;
        setBasePont(GameCharacterBasePoint.LEFTBOTTOM);
        setX(parent.getX() - 24);
        setY(parent.getY() - 20);
        setHeight(10);
    }

    @Override
    protected boolean updateProcess() {
        int remain =
                (int) ((float) parent.getWeapon().getRemainDelayFrame()
                        / (float) parent.getWeapon().getMaxDelayFrame() * 10);
        if (remain <= 0 || textureList.size() <= remain) {
            hide();
        } else {
            show();
            setTexture(textureList.get(remain));
            setWidth(remain * 5);
        }
        return super.updateProcess();
    }
}
