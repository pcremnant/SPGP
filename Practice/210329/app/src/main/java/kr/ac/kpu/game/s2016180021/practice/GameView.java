package kr.ac.kpu.game.s2016180021.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    public static final int BALL_COUNT = 10;
    ArrayList<GameObject> objects = new ArrayList<>();
    private Player player;

    private float lastTime;
    public static float frameTime;
    public static GameView view;

    public GameView(Context context, @Nullable AttributeSet attribute) {
        super(context, attribute);
        GameView.view = this;
        initResources();
        startUpdating();
    }

    private void startUpdating() {
        doGameFrame();
    }

    private void doGameFrame() {
        for (GameObject object : objects)
            object.update();
        invalidate();

        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long time) {
                frameTime = (float) (time-lastTime) / (1000 * 1000 * 1000);
                if (lastTime == 0) {
                    frameTime = 0;
                }
                doGameFrame();
                lastTime = time;
            }
        });

    }

    private void initResources() {
        player = new Player(100,100, 0, 0);
        objects.add(player);
        Random rand = new Random();
        for (int i = 0; i< BALL_COUNT; ++i){
            float x = rand.nextInt(800);
            float y = rand.nextInt(800);
            float dx = rand.nextInt(200) + 50;
            float dy = rand.nextInt(200) + 50;
            Ball b = new Ball(x, y, dx, dy);
            objects.add(b);
        }

        frameTime = 0;
        lastTime = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (GameObject object : objects)
            object.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action==MotionEvent.ACTION_MOVE){
            player.moveTo(event.getX(), event.getY());
            return true;
        }
        return super.onTouchEvent(event);
    }
}
