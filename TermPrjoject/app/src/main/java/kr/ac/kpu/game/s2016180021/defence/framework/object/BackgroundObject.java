package kr.ac.kpu.game.s2016180021.defence.framework.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180021.defence.framework.bitmap.GameBitmap;
import kr.ac.kpu.game.s2016180021.defence.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180021.defence.framework.view.GameView;

public class BackgroundObject implements GameObject {
    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    protected Bitmap bitmap;

    protected Rect srcRect = new Rect();
    protected RectF dstRect = new RectF();
    protected BackgroundObject() {}
    public BackgroundObject(int resId) {
        init(resId);
    }
    protected void init(int resId) {
        bitmap = GameBitmap.load(resId);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        srcRect.set(0, 0, w, h);
        // float l = x - w / 2 * GameView.MULTIPLIER;
        // float t = y - h / 2 * GameView.MULTIPLIER;
        // float r = x + w / 2 * GameView.MULTIPLIER;
        // float b = y + h / 2 * GameView.MULTIPLIER;
        // dstRect.set(l, t, r, b);
        dstRect.set(0, 0, GameView.view.getWidth(), GameView.view.getHeight());
    }

    public float getRight() {
        return dstRect.right;
    }

    public RectF getBoundingRect() {
        return dstRect;
    }
}
