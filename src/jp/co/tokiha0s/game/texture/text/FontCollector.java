package jp.co.tokiha0s.game.texture.text;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FontCollector {
	private static Map<FontDef, Font> fonts = new HashMap<>();
	
	static {
		for (FontDef fontDef : FontDef.values()) {
			fonts.put(fontDef, createFont(fontDef));
		}
	}

	public FontCollector() {
		// hide
	}

	public static Font getFont(FontDef fontDef) {
		return fonts.get(fontDef);
	}

	private static Font createFont(FontDef fontDef) {
		try {
			InputStream is = new FileInputStream(fontDef.filePath);
			return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(
					fontDef.size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
