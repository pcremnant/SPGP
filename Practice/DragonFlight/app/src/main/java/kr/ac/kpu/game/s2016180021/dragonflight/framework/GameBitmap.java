package kr.ac.kpu.game.s2016180021.dragonflight.framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;

import kr.ac.kpu.game.s2016180021.dragonflight.ui.view.GameView;

public class GameBitmap {
    private static HashMap<Integer, Bitmap> bitmaps = new HashMap<Integer, Bitmap>();

    public static Bitmap loadBitmap(int resId) {
        Bitmap bit = bitmaps.get(resId);
        if (bit == null) {
            Resources res = GameView.view.getResources();
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inScaled = false;
            bit = BitmapFactory.decodeResource(res, resId);
            bitmaps.put(resId, bit);
        }
        return bit;
    }
}
