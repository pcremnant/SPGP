package kr.ac.kpu.game.s2016180021.defence.framework.bitmap;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016180021.defence.framework.view.GameView;
import kr.ac.kpu.game.s2016180021.defence.game.MainGame;

public class AnimationStateGameBitmap {
    protected float timer;
    protected long createdOn;
    protected int frameIndex;
    protected float framesPerSecond;
    protected int frameCount;
    protected float delayTime;
    protected boolean isDelay;
    protected float delayTimer;
    protected boolean endDelay;

    protected Rect srcRect = new Rect();
    protected RectF dstRect = new RectF();
    protected ArrayList<GameBitmap> bitmaps = new ArrayList<>();

    public AnimationStateGameBitmap(float framesPerSecond, int frameCount) {
        this.framesPerSecond = framesPerSecond;
        this.frameCount = frameCount;
        createdOn = System.currentTimeMillis();
        frameIndex = 0;
        timer = 0;
        isDelay = false;
        delayTime = 0;
        delayTimer = 0;
        endDelay = false;
    }

    public void addBitmap(int resId){
        bitmaps.add(new GameBitmap(resId));
    }

    public float getWidth(){
        return bitmaps.get(frameIndex).getWidth();
    }

    public float getHeight(){
        return bitmaps.get(frameIndex).getHeight();
    }

    // public boolean isDelayTime(){return isDelay;}

    public boolean isEndDelay() {return endDelay;}

    public void advanceFrame() {
        if (isDelay) {
            delayTimer += MainGame.get().frameTime;
            if (delayTimer >= delayTime) {
                timer = 0;
                frameIndex = 0;
                isDelay = false;
                endDelay = true;
            }
        } else {
            timer += MainGame.get().frameTime;
            while (timer > 1 / framesPerSecond) {
                timer -= 1 / framesPerSecond;
                frameIndex++;
                endDelay = false;
                if (frameIndex == frameCount) {
                    frameIndex = frameCount - 1;
                    isDelay = true;
                    delayTimer = 0;
                    break;
                }
            }
        }
    }

    public void setDelayTime(float d){
        delayTime = d;
    }

    public void resetTimer(){
        this.timer = 0;
        this.frameIndex = 0;
        this.endDelay = false;
        this.isDelay = false;
        this.delayTimer = 0;
    }

    public void draw(Canvas canvas, float x, float y) {
        int w = bitmaps.get(frameIndex).getWidth();
        int h = bitmaps.get(frameIndex).getHeight();
        float hw = w / 2 * GameView.MULTIPLIER;
        float hh = h / 2 * GameView.MULTIPLIER;
        srcRect.set(0, 0, w, h);
        dstRect.set(x - hw, y - hh, x + hw, y + hh);
        canvas.drawBitmap(bitmaps.get(frameIndex).bitmap, srcRect, dstRect, null);
    }
}












