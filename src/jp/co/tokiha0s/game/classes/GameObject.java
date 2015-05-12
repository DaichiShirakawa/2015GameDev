package jp.co.tokiha0s.game.classes;

import java.util.Iterator;
import java.util.List;

/**
 * ゲームオブジェクトの基底インターフェース。
 * ゲームに登場するすべてのオブジェクトはこのインターフェースを利用する。
 * 
 * @author shirakawa
 * 
 */
public interface GameObject {

	/**
	 * 自分および子オブジェクトの計算処理を呼ぶ
	 */
	void update();

	/**
	 * 自分および子オブジェクトの描写処理を呼ぶ
	 */
	void render();
	
	/**
	 * trueなら画面に描写される
	 */
	boolean isVisible();
	
	/**
	 * オブジェクトの明示的な破棄を行う。
	 * 破棄したオブジェクトはその後いっさい処理しない。
	 */
	void destroy();

	/**
	 *tureなら既に破棄されているオブジェクト 
	 */
	boolean isDestroyed();


	/**
	 * 子オブジェクトを追加する
	 */
	<T extends GameObject> T add(T go);
	
	/**
	 * bookingFrameが経過するまで正式に子オブジェクトとしないよう予約する。
	 */
	GameObject booking(int bookingFrame);

	/**
	 * 子オブジェクトのイテレータを返す。
	 * オブジェクト自身は含まない。
	 */
	Iterator<GameObject> getIterator();
	
	/**
	 * ネストfor文を回したい場合などに、子リストのコピーを返す。
	 * Iteratorだけではネストfor文を回せないため。
	 * (方法を知らないだけかも)
	 */
	List<GameObject> getChildrenCopy();
	
	/**
	 * 可視状態にする
	 */
	GameObject show();

	/**
	 * 不可視状態にする
	 */
	GameObject hide();

	/**
	 * 可視状態を切り替える
	 */
	GameObject toggleVisible();

}
