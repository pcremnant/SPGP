package kr.ac.kpu.game.s2016180021.dragonflight.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016180021.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016180021.dragonflight.ui.view.GameView;

public class MainGame {
    private static MainGame instance;
    private Player player;

    public static MainGame get(){
        if (instance == null)
            instance = new MainGame();
        return instance;
    }
    public static final int BALL_COUNT = 10;

    ArrayList<GameObject> objects = new ArrayList<>();
    public float frameTime = 0;
    private boolean initialized = false;

    public boolean initResources() {
        if (initialized) return false;
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        player = new Player(w/2, h - 300, 0, 0);

        objects.add(player);

        frameTime = 0;
        initialized = true;
        return true;
    }

    public void update() {
        for (GameObject object : objects)
            object.update();
    }

    public void draw(Canvas canvas) {
        for (GameObject object : objects)
            object.draw(canvas);
    }

    public boolean OnTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            player.moveTo(event.getX(), event.getY());
            return true;
        }
        return false;
    }

    public void add(GameObject gameObject) {
        objects.add(gameObject);
    }

    public void removeObject(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                objects.remove(gameObject);
            }
        });
    }
}
