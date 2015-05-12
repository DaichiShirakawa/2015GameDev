package jp.co.tkhs.gp15.system.graphics.font;

import static jp.co.tkhs.gp15.system.Constants.*;

public enum FontDef {
    DEFAULT(FONT_RICTY_FILEPATH, 48), RICTY_24(FONT_RICTY_FILEPATH, 24), RICTY_32(
            FONT_RICTY_FILEPATH, 32), RICTY_40(FONT_RICTY_FILEPATH, 40), RICTY_48(
            FONT_RICTY_FILEPATH, 48), RICTY_56(FONT_RICTY_FILEPATH, 56), RICTY_64(
            FONT_RICTY_FILEPATH, 64);

    public final String filePath;
    public final float size;

    private FontDef(String filePath, float size) {
        this.filePath = filePath;
        this.size = size;
    }
}
