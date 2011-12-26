/**
 * 
 */
package com.cw;


import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.view.MotionEvent;
import android.view.View;

import com.cw.IColorChanged.OnColorChangedListener;

/**
 * @author chenwei10
 *
 */
public class ColorPickerView extends View {
    private Paint mPaint;
    private Paint mCenterPaint;
    private final int[] mColors;
    private OnColorChangedListener mListener;
	private LinearGradient mLinearGradient;
	private Paint red;
	private Random random;
    
    ColorPickerView(Context c, OnColorChangedListener l, int color) {
        super(c);
        mListener = l;
        mColors = new int[] {
            0xFFFF0000, 0xFFFF00FF, 0xFF0000FF, 0xFF00FFFF, 0xFF00FF00,
            0xFFFFFF00, 0xFFFF0000
        };
        Shader s = new SweepGradient(0, 0, mColors, null);
        
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        
		//线性渐变渲染
		mLinearGradient = new LinearGradient(0, 0, 200, 0,
				mColors , null, 
				Shader.TileMode.REPEAT);
        mPaint.setShader(mLinearGradient);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(32);
        
        mCenterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCenterPaint.setColor(0xff55ffff);
        mCenterPaint.setStrokeWidth(5);
        red = new Paint();
        red.setColor(android.graphics.Color.RED);
        red.setStyle(Paint.Style.FILL);
        random = new Random(System.currentTimeMillis());  

    }
    
    private boolean mTrackingCenter;
    private boolean mHighlightCenter;
	private int mX;
	private int mY;
	private int mColor = 0xffffff;

    @Override 
    protected void onDraw(Canvas canvas) {
        //绘制一个渐变的矩形
    	canvas.drawColor(mColor);

		canvas.drawRect(100,100,getWidth()-100,100, mPaint);
		
    	Path mPath = new Path();
        mPath.moveTo(mX, 100);
        mPath.lineTo(mX+20, 100+30);
        mPath.lineTo(mX-20, 100+30);
        mPath.close();
		canvas.drawPath(mPath, red); 
    }   
    private int ave(int s, int d, float p) {
        return s + java.lang.Math.round(p * (d - s));
    }
    
    private int interpColor(int colors[], float unit) {
        if (unit <= 0) {
            return colors[0];
        }
        if (unit >= 1) {
            return colors[colors.length - 1];
        }
        
        float p = unit * (colors.length - 1);
        int i = (int)p;
        p -= i;

        // now p is just the fractional part [0...1) and i is the index
        int c0 = colors[i];
        int c1 = colors[i+1];
        int a = ave(Color.alpha(c0), Color.alpha(c1), p);
        int r = ave(Color.red(c0), Color.red(c1), p);
        int g = ave(Color.green(c0), Color.green(c1), p);
        int b = ave(Color.blue(c0), Color.blue(c1), p);
        
        return Color.argb(a, r, g, b);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mX = (int)event.getX();
        mY = (int)event.getY();
        if (event.getX()>100 && event.getX()<getWidth()-100) {
        	float unit = (event.getX()-100)/(getWidth()-200);
        	mColor = interpColor(mColors,unit);
		}else {
			mColor = 0xffffffff;
		}
        invalidate();     
        return true;
    }
}

