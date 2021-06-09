package kr.ac.kpu.game.s2016180021.defence.framework.object;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016180021.defence.framework.bitmap.AnimationStateGameBitmap;
import kr.ac.kpu.game.s2016180021.defence.framework.iface.GameObject;

public class UnitObject implements GameObject {
    protected int hp;
    protected int maxHp;
    protected float x;
    protected float y;
    protected int state;
    protected float moveSpeed;
    protected int moveDirection;
    protected float attackRange;
    protected float attackDamage;
    protected float attackDelay;

    protected ArrayList<AnimationStateGameBitmap> animations = new ArrayList<>();

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        animations.get(state).draw(canvas, x,  y);
    }
}
