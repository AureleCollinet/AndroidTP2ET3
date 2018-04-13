package com.example.alegra08.tp2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class ViewImplem extends View {

    private double remplissageRatio;

    public ViewImplem(Context context) {
        super(context);
        setFillRatio(0.7);
    }


    public void setFillRatio(double ratio) {
        if (!(ratio < 0.0 || ratio > 1.0)) {
            this.remplissageRatio = ratio;
        } else {
            this.remplissageRatio=1.0;
        }
    }
    @Override
    public void onDraw(Canvas c)
    {
        super.onDraw(c);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        c.drawRect((float)(c.getHeight()*remplissageRatio),c.getWidth(),(float)(c.getHeight()*remplissageRatio),c.getWidth(), paint);
      //  Paint paint2 = new Paint();
     //   paint2.setColor(Color.WHITE);
      //  c.drawRect(c.getHeight(),c.getWidth(),c.getHeight(),c.getWidth(), paint);

    }
}
