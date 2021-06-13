package kr.ac.kpu.game.s2016180021.defence.game;

import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016180021.defence.R;
import kr.ac.kpu.game.s2016180021.defence.framework.game.BaseGame;
import kr.ac.kpu.game.s2016180021.defence.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180021.defence.framework.object.BackgroundObject;
import kr.ac.kpu.game.s2016180021.defence.framework.object.UnitObject;
import kr.ac.kpu.game.s2016180021.defence.framework.view.GameView;
import kr.ac.kpu.game.s2016180021.defence.game.unit.EnemySpearMan;
import kr.ac.kpu.game.s2016180021.defence.game.unit.GunMan;
import kr.ac.kpu.game.s2016180021.defence.game.unit.SpearMan;

public class MainGame extends BaseGame {
    private boolean initialized;

    //  private Player player;
    // private Score score;

    public static MainGame get() {
        return (MainGame) instance;
    }
    public enum Layer {
        bg, platform, item, unit, ui, controller, LAYER_COUNT
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
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.LAYER_COUNT.ordinal());

        add(Layer.bg, new BackgroundObject(R.mipmap.bg_ground));

        add(Layer.unit, new GunMan(300,400));
        add(Layer.unit, new SpearMan(300,400));
        add(Layer.unit, new EnemySpearMan(1000,400));

        // add(Layer.controller, new StageMap("stage_01.txt"));

       //  player = new Player(Platform.Type.T_2x2.width(), h / 2);
        //layers.get(Layer.player.ordinal()).add(player);
        // add(Layer.player, player);
//        add(Layer.controller, new EnemyGenerator());

        // add(Layer.controller, new CollisionChecker(player));

        // int margin = (int) (20 * GameView.MULTIPLIER);
        // score = new Score(w - margin, margin);
        // score.setScore(0);
        // add(Layer.ui, score);

        // add(Layer.bg, new HorizontalScrollBackground(R.mipmap.cookie_run_bg_1, -10));
        // add(Layer.bg, new HorizontalScrollBackground(R.mipmap.cookie_run_bg_2, -20));
        // add(Layer.bg, new HorizontalScrollBackground(R.mipmap.cookie_run_bg_3, -30));
//
//        float tx = 0, ty = h - Platform.Type.T_2x2.height();
//        while (tx < w) {
//            Platform platform = new Platform(Platform.Type.RANDOM, tx, ty);
//            add(Layer.platform, platform);
//            tx += platform.getDstWidth();
////        VerticalScrollBackground clouds = new VerticalScrollBackground(R.mipmap.clouds, 20);
////        add(Layer.bg2, clouds);
//        }

        initialized = true;
        return true;

    }

    @Override
    public void update() {
        super.update();

        // collision check
        for (GameObject o1 : objectsAt(Layer.unit)){
            for (GameObject o2 : objectsAt(Layer.unit)) {
                UnitObject unit1 = (UnitObject)o1;
                UnitObject unit2 = (UnitObject)o2;
                if (unit1.checkInAttackRange(unit2))
                    break;
                // unit2.checkInAttackRange(unit1);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // int action = event.getAction();
//        if (action == MotionEvent.ACTION_DOWN) {
        // if (action == MotionEvent.ACTION_DOWN) {
//      //       player.moveTo(event.getX(), event.getY());
        //     if (event.getX() < GameView.view.getWidth() / 2) {
        //         player.jump();
        //     } else {
        //         player.startSliding();
        //     }
        //     return true;
        // } else if (action == MotionEvent.ACTION_UP) {
        //     player.endSliding();
        // }
        return false;
    }
}
