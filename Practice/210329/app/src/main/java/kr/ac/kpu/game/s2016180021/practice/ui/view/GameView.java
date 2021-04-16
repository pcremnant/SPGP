package kr.ac.kpu.game.s2016180021.practice.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import kr.ac.kpu.game.s2016180021.practice.framework.Sound;
import kr.ac.kpu.game.s2016180021.practice.game.MainGame;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    public static GameView view;
    private long lastFrame;
    public GameView(Context context, @Nullable AttributeSet attribute) {
        super(context, attribute);
        GameView.view = this;
        Sound.Init(context);
        // startUpdating();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        MainGame game = MainGame.get();
        boolean justInitialized = game.initResources();
        if (justInitialized)
            requestCallback();
    }

    private void update() {
        MainGame game = MainGame.get();
        game.update();

        invalidate();
    }

    private void requestCallback() {
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long time) {
                if (lastFrame == 0) {
                    lastFrame = time;
                }
                MainGame game = MainGame.get();
                game.frameTime = (float) (time - lastFrame) / 1_000_000_000;
                update();
                lastFrame = time;
                requestCallback();
            }
        });
    }

    // private void startUpdating() {
    //     doGameFrame();
    // }

    // private void doGameFrame() {
    //     MainGame.get().update();
    //     invalidate();
//
    //     Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
    //         @Override
    //         public void doFrame(long time) {
    //             MainGame game = MainGame.get();
    //             if (game.lastTime == 0) {
    //                 game.lastTime = time;
    //             }
    //             game.frameTime = (float) (time - game.lastTime) / (1000 * 1000 * 1000);
    //             doGameFrame();
//
    //             game.lastTime = time;
    //         }
    //     });
    // }

    @Override
    protected void onDraw(Canvas canvas) {
        MainGame.get().draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return MainGame.get().OnTouchEvent(event);
    }
}
