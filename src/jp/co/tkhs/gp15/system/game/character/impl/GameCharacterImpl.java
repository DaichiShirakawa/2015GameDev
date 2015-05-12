package jp.co.tkhs.gp15.system.game.character.impl;

import static jp.co.tkhs.gp15.system.Constants.*;
import static jp.co.tkhs.gp15.system.game.utils.GameUtils.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.geom.Point2D;

import jp.co.tkhs.gp15.system.game.GameObjectImpl;
import jp.co.tkhs.gp15.system.game.character.GameCharacter;
import jp.co.tkhs.gp15.system.game.character.enums.GameCharacterBasePoint;
import jp.co.tkhs.gp15.system.game.character.enums.GameCharacterMoveMode;
import jp.co.tkhs.gp15.system.graphics.Texture;

/**
 * ゲームキャラクターの実装クラス
 *
 * @author tokiha0s
 *
 */
public abstract class GameCharacterImpl extends GameObjectImpl implements GameCharacter {
    private int destroyTimerFrame = -1;

    private GameCharacterBasePoint basePoint = GameCharacterBasePoint.CENTER;
    private GameCharacterMoveMode xMoveMode = GameCharacterMoveMode.UNLIMITED;
    private GameCharacterMoveMode yMoveMode = GameCharacterMoveMode.UNLIMITED;
    private float x;
    private float vx;
    private float y;
    private float vy;

    private int width;
    private int height;

    private float scale = 1;
    private float vScale = 0;
    private float angle = 0;
    private float vAngle = 0;
    private float alpha = 1;
    private float vAlpha = 0;

    private Color color;
    private Texture texture;

    protected GameCharacterImpl() {
        super();
    }

    @Override
    protected boolean updateProcess() {
        if (isDestroyed()) {
            return false;
        }
        if (destroyTimerFrame == 0) {
            destroy();
            return false;
        }
        if (destroyTimerFrame > 0) {
            destroyTimerFrame--;
        }

        setScale(getScale() + getVScale());
        setAngle(getAngle() + getVAngle());
        setAlpha(getAlpha() + getVAlpha());
        if (getAlpha() > 1) {
            setAlpha(1);
            setVAlpha(0);
        }
        if (getAlpha() < 0) {
            setAlpha(0);
            setVAlpha(0);
        }
        move();
        return true;
    }

    /**
     * MoveModeで定義された移動処理を行う。 (x, y)を中心点として計算するロジックになっている。 TODO BasePointがCenter以外の場合にも対応する
     */
    private final void move() {
        x = xMoveMode.move(WIDTH, width, x, vx);
        if (xMoveMode == GameCharacterMoveMode.DESTROY_WITH_FADEOUT
                && (x + width / 2 < 0 || x - width / 2 > WIDTH)) {
            destroy();
        }

        y = yMoveMode.move(HEIGHT, height, y, vy);
        if (yMoveMode == GameCharacterMoveMode.DESTROY_WITH_FADEOUT
                && (y + height / 2 < 0 || y - height / 2 > HEIGHT)) {
            destroy();
        }
    }

    @Override
    protected void renderProcess() {
        if (!isVisible()) {
            return;
        }
        float x = getBasePoint().getX(getX(), getWidth());
        float y = getBasePoint().getY(getY(), getHeight());

        glLoadIdentity();
        glTranslatef(x, y, 0);
        glRotatef(getAngle(), 0, 0, 1);
        setGlColor4f(getColor(), getAlpha());

        if (getTexture() != null) {
            drawTexture(getTexture(), getWidth(), getHeight());
        }
    }

    @Override
    protected void destroyProcess() {
        super.destroyProcess();
        if (!canDisposeTexture()) {
            return;
        }
        if (getTexture() != null) {
            getTexture().dispose();
            setTexture(null);
        }
    }

    /**
     * TODO テクスチャをキャッシュできるようになれば不要な仕組み
     *
     * trueを返すことによって、destroy時にテクスチャも同時に破棄する。<br>
     * オブジェクトとテクスチャが１：１の場合などに利用。
     */
    protected abstract boolean canDisposeTexture();

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public GameCharacter setTexture(Texture texture) {
        this.texture = texture;
        return this;
    }

    @Override
    public GameCharacterBasePoint getBasePoint() {
        return basePoint;
    }

    @Override
    public GameCharacter setBasePont(GameCharacterBasePoint basePoint) {
        this.basePoint = basePoint;
        return this;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public GameCharacter setX(float x) {
        this.x = x;
        return this;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public GameCharacter setY(float y) {
        this.y = y;
        return this;
    }

    @Override
    public float getVX() {
        return vx;
    }

    @Override
    public GameCharacter setVX(float vx) {
        this.vx = vx;
        return this;
    }

    @Override
    public float getVY() {
        return vy;
    }

    @Override
    public GameCharacter setVY(float vy) {
        this.vy = vy;
        return this;
    }

    @Override
    public int getWidth() {
        return (int) (width * scale);
    }

    @Override
    public GameCharacter setWidth(int width) {
        assert (width % 2 == 0) : "widthは偶数でなくてはならない";
        this.width = width;
        return this;
    }

    @Override
    public int getHeight() {
        return (int) (height * scale);
    }

    @Override
    public GameCharacter setHeight(int height) {
        assert (height % 2 == 0) : "heightは偶数でなくてはならない";
        this.height = height;
        return this;
    }

    @Override
    public float getScale() {
        return scale;
    }

    @Override
    public GameCharacter setScale(float scale) {
        this.scale = scale;
        return this;
    }

    @Override
    public float getAngle() {
        return angle;
    }

    @Override
    public GameCharacter setAngle(float angle) {
        this.angle = angle;
        return this;
    }

    @Override
    public float getAlpha() {
        return alpha;
    }

    @Override
    public GameCharacter setAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    @Override
    public float getVScale() {
        return vScale;
    }

    @Override
    public GameCharacter setVScale(float vScale) {
        this.vScale = vScale;
        return this;
    }

    @Override
    public float getVAngle() {
        return vAngle;
    }

    @Override
    public GameCharacter setVAngle(float vAngle) {
        this.vAngle = vAngle;
        return this;
    }

    @Override
    public float getVAlpha() {
        return vAlpha;
    }

    @Override
    public GameCharacter setVAlpha(float vAlpha) {
        if (vAlpha > 1f) {
            vAlpha = 1f;
        }
        if (vAlpha < 0f) {
            vAlpha = 0f;
        }
        this.vAlpha = vAlpha;
        return this;
    }

    @Override
    public GameCharacter setMoveModeX(GameCharacterMoveMode moveMode) {
        this.xMoveMode = moveMode;
        return this;
    }

    @Override
    public GameCharacter setMoveModeY(GameCharacterMoveMode moveMode) {
        this.yMoveMode = moveMode;
        return this;
    }

    /**
     * seconds秒経過後に破棄するタイマーをセットする。
     */
    @Override
    public void destroyAfter(float seconds) {
        // 現状のロジックでは1/FPS秒程度の誤差がある。
        destroyTimerFrame = (int) (FPS * seconds);
    }

    @Override
    public int getDestroyTimerFrame() {
        return destroyTimerFrame;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public GameCharacter setColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public boolean checkHit(GameCharacter target) {
        if (isDestroyed() || target.isDestroyed()) {
            return false;
        }
        Point2D selfP1 = new Point2D.Float(getX() - getWidth() / 2, getY() + getHeight() / 2);
        Point2D selfP2 =
                new Point2D.Float((float) selfP1.getX() + getWidth(), (float) selfP1.getY());
        Point2D selfP3 =
                new Point2D.Float((float) selfP1.getX(), (float) selfP1.getY() - getHeight());

        Point2D targP1 =
                new Point2D.Float(target.getX() - target.getWidth() / 2, target.getY()
                        + target.getHeight() / 2);
        Point2D targP2 =
                new Point2D.Float((float) targP1.getX() + target.getWidth(), (float) targP1.getY());
        Point2D targP3 =
                new Point2D.Float((float) targP1.getX(), (float) targP1.getY() - target.getHeight());

        if (selfP2.getX() >= targP1.getX() && selfP1.getX() <= targP2.getX()) {
            if (selfP3.getY() <= targP1.getY() && selfP1.getY() >= targP3.getY()) {
                return true;
            }
        }
        return false;
    }
}
