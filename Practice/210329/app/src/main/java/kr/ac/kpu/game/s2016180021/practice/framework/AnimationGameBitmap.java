package kr.ac.kpu.game.s2016180021.practice.framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180021.practice.R;
import kr.ac.kpu.game.s2016180021.practice.ui.view.GameView;

public class AnimationGameBitmap extends GameBitmap {
    private final Bitmap bitmap;
    private final int imageWidth;
    private final int imageHeight;
    private final int frameWidth;
    private final long createOn;
    private int frameIndex;
    private int frameCount;
    private float framePerSecond;
    private int PIXEL_MULTIPLIER = 4;

    public AnimationGameBitmap(int resId, float framesPerSecond, int frameCount){
        Resources res = GameView.view.getResources();

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inScaled = false;

        bitmap = BitmapFactory.decodeResource(res, resId);

        imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();
        if (frameCount == 0)
            this.frameCount = imageWidth / imageHeight;
        else
            this.frameCount = frameCount;
        frameWidth = imageWidth / this.frameCount;
        createOn = System.currentTimeMillis();
        frameIndex = 0;
        framePerSecond = framesPerSecond;
    }

    // public void update(){
    //     int elapsed = (int) (System.currentTimeMillis() - createOn);
    //     frameIndex = Math.round(elapsed * 0.001f * framePerSecond) % frameCount;
    // }

    public void draw(Canvas canvas, float x, float y) {
        int elapsed = (int) (System.currentTimeMillis() - createOn);
        frameIndex = Math.round(elapsed * 0.001f * framePerSecond) % frameCount;
        Rect src = new Rect(frameIndex * frameWidth, 0, (frameIndex + 1) * frameWidth, imageHeight);
        RectF dst = new RectF(x - frameWidth / 2, y - imageHeight / 2, x + frameWidth / 2, y + imageHeight / 2);
        canvas.drawBitmap(bitmap, src, dst, null);
    }

    public int getWidth(){
        return frameWidth * PIXEL_MULTIPLIER;
    }

    public int getHeight(){
        return imageHeight * PIXEL_MULTIPLIER;
    }
}
