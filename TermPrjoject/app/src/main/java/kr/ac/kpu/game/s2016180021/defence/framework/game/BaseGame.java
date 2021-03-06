package kr.ac.kpu.game.s2016180021.defence.framework.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ac.kpu.game.s2016180021.defence.BuildConfig;
import kr.ac.kpu.game.s2016180021.defence.framework.iface.BoxCollidable;
import kr.ac.kpu.game.s2016180021.defence.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180021.defence.framework.iface.Recyclable;
import kr.ac.kpu.game.s2016180021.defence.framework.view.GameView;

public class BaseGame {
    private static final String TAG = BaseGame.class.getSimpleName();
    // singleton
    protected static BaseGame instance;
    private RectF collisionRect;
    private Paint collisionPaint;

    public static BaseGame get() {
//        if (instance == null) {
//            instance = new BaseGame();
//        }
        return instance;
    }
    public float frameTime;

    protected BaseGame() {
        instance = this;

        // if (BuildConfig.showsCollisionBox) {
        //     collisionRect = new RectF();
        //     collisionPaint = new Paint();
        //     collisionPaint.setStyle(Paint.Style.STROKE);
        //     collisionPaint.setColor(Color.RED);
        // }
    }
//    Player player;
    ArrayList<ArrayList<GameObject>> layers;
    private static HashMap<Class, ArrayList<GameObject>> recycleBin = new HashMap<>();

    public void recycle(GameObject object) {
        Class clazz = object.getClass();
        ArrayList<GameObject> array = recycleBin.get(clazz);
        if (array == null) {
            array = new ArrayList<>();
            recycleBin.put(clazz, array);
        }
        array.add(object);
    }
    public GameObject get(Class clazz) {
        ArrayList<GameObject> array = recycleBin.get(clazz);
        if (array == null || array.isEmpty()) return null;
        return array.remove(0);
    }

    public boolean initResources() {
        // prints this is error
        return false;
    }

    protected void initLayers(int layerCount) {
        layers = new ArrayList<>();
        for (int i = 0; i < layerCount; i++) {
            layers.add(new ArrayList<>());
        }
    }

    public void update() {
        //if (!initialized) return;
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                o.update();
            }
        }
    }

    public void draw(Canvas canvas) {
        //if (!initialized) return;
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                o.draw(canvas);
            }
        }
        // if (BuildConfig.showsCollisionBox) {
        //     for (ArrayList<GameObject> objects: layers) {
        //         for (GameObject o : objects) {
        //             if (!(o instanceof BoxCollidable)) {
        //                 continue;
        //             }
        //             ((BoxCollidable) o).getBoundingRect(collisionRect);
        //             canvas.drawRect(collisionRect, collisionPaint);
        //         }
        //     }
        // }
    }

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public void add(int layerIndex, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layerIndex);
                objects.add(gameObject);
            }
        });
//        Log.d(TAG, "<A> object count = " + objects.size());
    }

    public void removeAll(){
        for (ArrayList<GameObject> layer : layers){
            for (GameObject o : layer){
                remove(o);
            }
        }
    }

    public void remove(GameObject gameObject) {
        remove(gameObject, true);
    }
    public void remove(GameObject gameObject, boolean delayed) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (ArrayList<GameObject> objects: layers) {
                    boolean removed = objects.remove(gameObject);
                    if (removed) {
                        if (gameObject instanceof Recyclable) {
                            ((Recyclable) gameObject).recycle();
                            recycle(gameObject);
                        }
                        //Log.d(TAG, "Removed: " + gameObject);
                        break;
                    }
                }
            }
        };
        if (delayed) {
            GameView.view.post(runnable);
        } else {
            runnable.run();
        }
    }

    public ArrayList<GameObject> objectsAt(int layerIndex) {
        return layers.get(layerIndex);
    }
}
