package kr.ac.kpu.game.s2016180021.defence.framework.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180021.defence.framework.bitmap.GameBitmap;
import kr.ac.kpu.game.s2016180021.defence.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180021.defence.framework.view.GameView;

public class ImageObject implements GameObject {
    private final Bitmap bitmap;

    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();
    public ImageObject(int resId, float x, float y) {
        bitmap = GameBitmap.load(resId);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        srcRect.set(0, 0, w, h);
        float l = x - w / 2 * GameView.MULTIPLIER;
        float t = y - h / 2 * GameView.MULTIPLIER;
        float r = x + w / 2 * GameView.MULTIPLIER;
        float b = y + h / 2 * GameView.MULTIPLIER;
        dstRect.set(l, t, r, b);
    }
    public ImageObject(int resId, float l, float t, float r, float b){
        bitmap = GameBitmap.load(resId);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        srcRect.set(0, 0, w, h);
        dstRect.set(l, t, r, b);
    }

    public void setDstRect(RectF dstRect) {
        this.dstRect = dstRect;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}
