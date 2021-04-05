package kr.ac.kpu.game.s2016180021.practice.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import kr.ac.kpu.game.s2016180021.practice.framework.GameObject;

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
    public static float frameTime = 0;
    public static float lastTime;

    public void initResources() {
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
        if (action == MotionEvent.ACTION_DOWN || action==MotionEvent.ACTION_MOVE){
            player.moveTo(event.getX(), event.getY());
            return true;
        }
        return false;
    }
}
