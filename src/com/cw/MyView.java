/**
 * 
 */
package com.cw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author chenwei10
 *
 */
public class MyView extends View{

	private Paint paint;
	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		paint = new Paint();
		paint.setColor(0x00FF77);
		paint.setAlpha(255);
		Log.i("", "");
	}
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
		if (paint.getAlpha()>200) {
			paint.setAlpha(10);
		}else {
			paint.setAlpha(255);
		}
		canvas.drawLine(10, 10, 10, 150, paint);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i("Eureka", event.getAction()+"");
		return super.onTouchEvent(event);
	}
	
}
