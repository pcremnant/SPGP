package kr.ac.kpu.game.s2016180021.defence.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016180021.defence.R;
import kr.ac.kpu.game.s2016180021.defence.framework.game.BaseGame;
import kr.ac.kpu.game.s2016180021.defence.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180021.defence.framework.object.BackgroundObject;
import kr.ac.kpu.game.s2016180021.defence.framework.object.ImageObject;
import kr.ac.kpu.game.s2016180021.defence.framework.object.UnitObject;
import kr.ac.kpu.game.s2016180021.defence.framework.view.GameView;
import kr.ac.kpu.game.s2016180021.defence.game.unit.Castle;
import kr.ac.kpu.game.s2016180021.defence.game.unit.EnemyCastle;
import kr.ac.kpu.game.s2016180021.defence.game.unit.EnemyGenerator;
import kr.ac.kpu.game.s2016180021.defence.game.unit.GunMan;
import kr.ac.kpu.game.s2016180021.defence.game.unit.Icon;
import kr.ac.kpu.game.s2016180021.defence.game.unit.Resource;
import kr.ac.kpu.game.s2016180021.defence.game.unit.SpearMan;

public class MainGame extends BaseGame {
    enum GAME_STATE{
        playing, pause
    }

    private boolean initialized;
    private Resource resource;
    private GAME_STATE state;
    private ImageObject lose = null;
    private ImageObject win = null;
    private Castle myCastle;
    private EnemyCastle enemyCastle;
    private int winner = 0;

    public static MainGame get() {
        return (MainGame) instance;
    }
    public enum Layer {
        bg, unit, ui, icon, pause, controller, LAYER_COUNT
    }

    public void add(Layer layer, GameObject obj) {
        add(layer.ordinal(), obj);
    }

    public ArrayList<GameObject> objectsAt(Layer layer) {
        return objectsAt(layer.ordinal());
    }

    @Override
    public boolean initResources() {
        if (initialized) {
            return false;
        }
        state = GAME_STATE.playing;
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.LAYER_COUNT.ordinal());

        add(Layer.bg, new BackgroundObject(R.mipmap.bg_ground));

        add(Layer.controller, new EnemyGenerator(w - 200, h - 350));

        myCastle = new Castle(200, h - 500);
        add(Layer.unit, myCastle);
        enemyCastle = new EnemyCastle(w - 200, h - 500);
        add(Layer.unit, enemyCastle);

        add(Layer.icon, new Icon(R.mipmap.icon_gunman, w - 600, h - 100));
        add(Layer.icon, new Icon(R.mipmap.icon_spearman, w - 400, h - 100));

        resource = new Resource(600, 50);
        add(Layer.ui, resource);

        if (lose == null)
            lose = new ImageObject(R.mipmap.ui_lose, 0, 0, w, h);
        if (win == null)
            win = new ImageObject(R.mipmap.ui_stageclear, 0, 0, w, h);

        initialized = true;
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        switch (state){
            case playing:
                super.draw(canvas);
                break;
            case pause:
                // super.draw(canvas);
                if (winner == 1){
                    win.draw(canvas);
                }
                else if (winner == 2){
                    lose.draw(canvas);
                }
                break;
        }
    }

    @Override
    public void update() {
        if (state == GAME_STATE.playing) {
            super.update();
            for (GameObject o1 : objectsAt(Layer.unit)) {
                for (GameObject o2 : objectsAt(Layer.unit)) {
                    UnitObject unit1 = (UnitObject) o1;
                    UnitObject unit2 = (UnitObject) o2;
                    if (unit1.checkInAttackRange(unit2)) {
                        if (enemyCastle != null && enemyCastle.getHp() <= 0) {
                            winner = 1;
                            state = GAME_STATE.pause;
                            break;
                        }
                        if (myCastle != null && myCastle.getHp() <= 0) {
                            winner = 2;
                            state = GAME_STATE.pause;
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            if (state == GAME_STATE.playing) {
                for (Object o : objectsAt(Layer.icon)) {
                    Icon i = (Icon) o;

                    if (i.isClicked(event.getX(), event.getY())) {
                        int h = GameView.view.getHeight();
                        if (i.getResId() == R.mipmap.icon_gunman) {
                            if (resource.getResource() >= 200) {
                                resource.setResource(resource.getResource() - 200);
                                add(Layer.unit, new GunMan(300, h - 300));
                            }
                        } else if (i.getResId() == R.mipmap.icon_spearman) {
                            if (resource.getResource() >= 100) {
                                resource.setResource(resource.getResource() - 100);
                                add(Layer.unit, new SpearMan(300, h - 300));
                            }
                        }
                    }
                }
                return true;
            }
            else{
                state = GAME_STATE.playing;
                removeAll();
                initialized = false;
                initResources();
            }
        }
        return false;
    }
}
