package kr.ac.kpu.game.s2016180021.practice.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import kr.ac.kpu.game.s2016180021.practice.framework.GameObject;
import kr.ac.kpu.game.s2016180021.practice.ui.view.GameView;

public class MainGame {
    private static MainGame instance;
    public static MainGame get(){
        if (instance == null)
            instance = new MainGame();
        return instance;
    }

    ArrayList<GameObject> objects = new ArrayList<>();
    private Player player;
    public static final int BALL_COUNT = 10;
    public float frameTime = 0;
    public float lastTime;
    private boolean initialized = false;

    public void initResources() {
        if (initialized) return;
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        player = new Player((int)w/2,(int)h/2, 0, 0);
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
        initialized = true;
    }

    public void update() {
        if (!initialized) return;
        for (GameObject object : objects)
            object.update();
    }

    public void draw(Canvas canvas) {
        if (!initialized) return;
        for (GameObject object : objects)
            object.draw(canvas);
    }

    public boolean OnTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action==MotionEvent.ACTION_MOVE){
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
