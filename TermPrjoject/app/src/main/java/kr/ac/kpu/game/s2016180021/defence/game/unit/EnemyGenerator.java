package kr.ac.kpu.game.s2016180021.defence.game.unit;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.kpu.game.s2016180021.defence.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180021.defence.game.MainGame;

public class EnemyGenerator implements GameObject {
    float timer = 0;
    float genTime = 5;
    float x, y;
    public EnemyGenerator(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        timer += game.frameTime;
        if (timer >= genTime){
            timer -= genTime;
            Random r = new Random();
            genTime = r.nextInt(3) + 3;
            generateEnemies();
        }
    }

    void generateEnemies(){
        Random r = new Random();
        int numEnemies = r.nextInt(4);
        MainGame game = MainGame.get();
        for (int i=0;i<numEnemies;++i){
            if (r.nextInt(2) == 0){
                game.add(MainGame.Layer.unit, new EnemySkell(x, y));
            }
            else{
                game.add(MainGame.Layer.unit, new EnemySpearMan(x, y + 50));

            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
