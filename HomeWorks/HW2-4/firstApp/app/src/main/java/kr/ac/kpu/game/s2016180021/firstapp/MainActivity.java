package kr.ac.kpu.game.s2016180021.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected TextView mainTextView;
    protected ImageView mainImageView;
    protected int curPage;
    protected int maxPage;
    protected String text;
    int imageId;

    protected ImageButton buttonPrev;
    protected ImageButton buttonNext;

    protected  void setText(){
        text = String.valueOf(curPage) + '/' + String.valueOf(maxPage);
        mainTextView.setText(text);
    }

    protected  void selectImage(){
        switch (curPage){
            case 1:
                imageId = R.mipmap.cat1;
                break;
            case 2:
                imageId = R.mipmap.cat2;
                break;
            case 3:
                imageId = R.mipmap.cat3;
                break;
            case 4:
                imageId = R.mipmap.cat4;
                break;
            case 5:
                imageId = R.mipmap.cat5;
                break;
        }
    }

    protected void applyPage(){
        setText();
        selectImage();
        mainTextView.setText(text);
        mainImageView.setImageResource(imageId);
        buttonPrev.setEnabled(curPage != 1);
        buttonNext.setEnabled(curPage != maxPage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTextView = findViewById(R.id.mainTextView);
        mainImageView = findViewById(R.id.mainImageView);
        buttonPrev = findViewById(R.id.prevButton);
        buttonNext = findViewById(R.id.nextButton);

        curPage = 1;
        maxPage = 5;

        applyPage();
    }

    public void onBtnPrev(View view) {
        if (curPage > 1)
            curPage -= 1;
        applyPage();
    }

    public void onBtnNext(View view) {
        if (curPage < maxPage)
            curPage += 1;
        applyPage();
    }
}