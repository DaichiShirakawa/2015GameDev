package jp.co.tkhs.gp15.system.game;

import java.util.Iterator;
import java.util.List;

/**
 * ゲームオブジェクトの基底インターフェース。<br>
 * ゲームに登場するすべてのオブジェクトはGameObjectを継承する。
 *
 * @author tokiha0s
 *
 */
public interface GameObject {

    /**
     * 自身および子オブジェクトの計算処理を呼ぶ
     */
    void update();

    /**
     * 自身および子オブジェクトの描写処理を呼ぶ
     */
    void render();

    /**
     * @return trueなら画面に描写される
     */
    boolean isVisible();

    /**
     * オブジェクトの明示的な破棄を行う。<br>
     * 以後、破棄されたオブジェクトは処理されない。
     */
    void destroy();

    /**
     * @return trueなら破棄されたオブジェクト
     */
    boolean isDestroyed();


    /**
     * 子オブジェクトを追加する
     *
     * @return 追加できなければfalse
     */
    <G extends GameObject> boolean add(G gobj);

    /**
     * TODO わかりにくい仕組み
     *
     * いずれかの親にaddされた後でも、<br>
     * bookingFrameが経過するまで、<br>
     * 自身が正式に子オブジェクトとされないよう予約する。
     */
    void booking(int bookingFrame);

    /**
     * 子オブジェクトのイテレータを返す。<br>
     * オブジェクト自身は含まない。
     */
    Iterator<GameObject> getIterator();

    /**
     * ネストfor文を回したい場合などに、子リストのコピーを返す。<br>
     * Iteratorだけではネストfor文を回せないため。 (方法を知らないだけかも)
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
