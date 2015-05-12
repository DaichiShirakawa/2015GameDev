package jp.co.tokiha0s.game.classes.character;

/**
 * キャラクターの移動モードの定義
 * 
 * @author shirakawa
 * 
 */
public enum GameCharacterMoveMode {
	/**
	 * 自由移動
	 */
	UNLIMITED {
		@Override
		public float move(int displayBorder, int characterSize, float p,
				float vp) {
			return p + vp;
		}
	},
	/**
	 * テクスチャ中心点が画面枠内に収まる
	 */
	CENTER_IN_DISPLAY {
		@Override
		public float move(int displayBorder, int characterSize, float p,
				float vp) {
			p += vp;
			if (p < 0) {
				p = 0;
			}
			if (p < displayBorder) {
				p = displayBorder;
			}
			return p;
		}
	},
	/**
	 * テクスチャが画面枠内に収まる
	 */
	TEXTURE_IN_DISPLAY {
		@Override
		public float move(int displayBorder, int characterSize, float p,
				float vp) {
			p += vp;
			if (p - characterSize / 2 < 0) {
				p = characterSize / 2;
			}
			if (displayBorder < p + characterSize / 2) {
				p = displayBorder - characterSize / 2;
			}
			return p;
		}
	},
	/**
	 * テクスチャ中心点が画面枠で反射する
	 */
	REFRECTION_CENTER {
		@Override
		public float move(int displayBorder, int characterSize, float p,
				final float vp) {
			p += vp;
			if (p < 0) {
				p = -p;
			}
			if (displayBorder < p) {
				p = displayBorder - p + displayBorder;
			}
			return p;
		}
	},
	/**
	 * 上下もしくは左右がリンクし、ループする。
	 */
	LOOP {
		@Override
		public float move(int displayBorder, int characterSize, float p,
				float vp) {
			p += vp;
			if (p + characterSize / 2 < 0) {
				p = displayBorder - p;
			} else if (displayBorder < p - characterSize / 2) {
				p = -p + displayBorder;
			}
			return p;
		}
	},
	/**
	 * テクスチャが画面外に出た時点でキャラクターをdisposeする。
	 * ※disposeはキャラクター実装任せ。
	 */
	DESTROY_WITH_FADEOUT {
		@Override
		public float move(int displayBorder, int characterSize, float p,
				float vp) {
			return p + vp;
		}
	};
	/**
	 * 基本的な移動計算を担う
	 * 
	 * @param displayBorder
	 *            画面下境界座標 or 右境界座標
	 * @param characterSize
	 *            キャラクター縦幅 or横幅
	 * @param p
	 *            x座標 or y座標
	 * @param vp
	 *            x増加量 or y増加量
	 * @return 移動後の x座標 or y座標
	 */
	public abstract float move(int displayBorder, int characterSize, float p,
			float vp);
}