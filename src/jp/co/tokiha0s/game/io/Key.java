package jp.co.tokiha0s.game.io;

import org.lwjgl.input.Keyboard;

/**
 * キーボードの状態を扱いやすくするためのラッパー
 * キーごとに押した瞬間／離した瞬間および、
 * 連続で押され続けているフレーム数を取得できる。
 * キーのvalueは org.lwjgl.input.Keyboard に準拠
 * 
 * @author shirakawa
 */
public enum Key {
	ESCAPE(1),
	ONE(2),
	TWO(3),
	THREE(4),
	FOUR(5),
	FIVE(6),
	SIX(7),
	SEVEN(8),
	EIGHT(9),
	NINE(10),
	ZERO(11),
	MINUS(12),
	EQUALS(13),
	BACK(14),
	TAB(15),
	Q(16),
	W(17),
	E(18),
	R(19),
	T(20),
	Y(21),
	U(22),
	I(23),
	O(24),
	P(25),
	LBRACKET(26),
	RBRACKET(27),
	RETURN(28),
	LCONTROL(29),
	A(30),
	S(31),
	D(32),
	F(33),
	G(34),
	H(35),
	J(36),
	K(37),
	L(38),
	SEMICOLON(39),
	APOSTROPHE(40),
	GRAVE(41),
	LSHIFT(42),
	BACKSLASH(43),
	Z(44),
	X(45),
	C(46),
	V(47),
	B(48),
	N(49),
	M(50),
	COMMA(51),
	PERIOD(52),
	SLASH(53),
	RSHIFT(54),
	MULTIPLY(55),
	LMENU(56),
	SPACE(57),
	CAPITAL(58),
	F1(59),
	F2(60),
	F3(61),
	F4(62),
	F5(63),
	F6(64),
	F7(65),
	F8(66),
	F9(67),
	F10(68),
	NUMLOCK(69),
	SCROLL(70),
	NUMPAD7(71),
	NUMPAD8(72),
	NUMPAD9(73),
	SUBTRACT(74),
	NUMPAD4(75),
	NUMPAD5(76),
	NUMPAD6(77),
	ADD(78),
	NUMPAD1(79),
	NUMPAD2(80),
	NUMPAD3(81),
	NUMPAD0(82),
	DECIMAL(83),
	F11(87),
	F12(88),
	F13(100),
	F14(101),
	F15(102),
	F16(103),
	F17(104),
	F18(105),
	KANA(112),
	F19(113),
	CONVERT(121),
	NOCONVERT(123),
	YEN(125),
	NUMPADEQUALS(141),
	CIRCUMFLEX(144),
	AT(145),
	COLON(146),
	UNDERLINE(147),
	KANJI(148),
	STOP(149),
	AX(150),
	UNLABELED(151),
	NUMPADENTER(156),
	RCONTROL(157),
	SECTION(167),
	NUMPADCOMMA(179),
	DIVIDE(181),
	SYSRQ(183),
	RMENU(184),
	FUNCTION(196),
	PAUSE(197),
	HOME(199),
	UP(200),
	PRIOR(201),
	LEFT(203),
	RIGHT(205),
	END(207),
	DOWN(208),
	NEXT(209),
	INSERT(210),
	DELETE(211),
	CLEAR(218),
	LMETA(219),
	LWIN(219),
	RMETA(220),
	RWIN(220),
	APPS(221),
	POWER(222),
	SLEEP(223), 
	NULL(0);

	// HACK 整数の線形性を前提としたstate定数。もう少し賢い方法がありそうだ
	protected static final int STATE_RELEASED = -2; // 離された瞬間
	protected static final int STATE_NOTOUCH = -1; // 押されていない
	protected static final int STATE_PRESSED = 0; // 押された瞬間

	private final int intVal;
	private int state = STATE_NOTOUCH;

	private Key(int intVal) {
		this.intVal = intVal;
	}

	public static Key valueOf(int intVal) {
		for (Key key : Key.values()) {
			if (key.intVal == intVal) {
				return key;
			}
		}
		return null;
	}

	public int getPressingFrameCount() {
		return state;
	}

	public boolean isPressing() {
		return (state >= 0);
	}

	public boolean isPressed() {
		return (state == STATE_PRESSED);
	}

	public boolean isReleased() {
		return (state == STATE_RELEASED);
	}

	public static boolean anyKeyPressed() {
		for (Key key : Key.values()) {
			if (key.state == STATE_PRESSED) {
				return true;
			}
		}
		return false;
	}

	public static boolean anyKeyReleased() {
		for (Key key : Key.values()) {
			if (key.state == STATE_RELEASED) {
				return true;
			}
		}
		return false;
	}

	public static void update() {
		updatePressedFrameCount();
		updatePressOrRelease();
	}

	private static void updatePressOrRelease() {
		while (Keyboard.next()) {
			Key key = Key.valueOf(Keyboard.getEventKey());
			if(key == null) {
				continue;
			}
			if (Keyboard.getEventKeyState()) {
				// 押した瞬間
				key.state = STATE_PRESSED;
			} else {
				// 離した瞬間
				key.state = STATE_RELEASED;
			}
		}
	}

	private static void updatePressedFrameCount() {
		for (Key key : Key.values()) {
			if (key.state != STATE_NOTOUCH) {
				key.state++;
			}
		}
	}
}
