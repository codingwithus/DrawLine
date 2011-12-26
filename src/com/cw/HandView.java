/**
 * 
 */
package com.cw;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author chenwei10
 * 
 */
public class HandView extends View {
	private static final int STEP_FADE = 20;

	private float mX;
	private float mY;
	private Path mPath = new Path();


	ArrayList<Path> mPathArr = new ArrayList<Path>();
	private Paint mBorderPaint;

	/**
	 * @param context
	 */
	public HandView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public HandView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public HandView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {
		mBorderPaint = new Paint();
		mBorderPaint.setColor(0x99ff0000);
		mBorderPaint.setAntiAlias(true);
		mBorderPaint.setStrokeWidth(15);
		mBorderPaint.setDither(true);
		mBorderPaint.setStyle(Paint.Style.STROKE);
		mBorderPaint.setStrokeJoin(Paint.Join.ROUND);
		mBorderPaint.setStrokeCap(Paint.Cap.ROUND);
		setBackgroundColor(0xff00ffff);
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
		mBorderPaint.setAlpha(255);
		canvas.drawPath(mPath, mBorderPaint);
		int j = 1;
		for (int i = mPathArr.size() - 1; i >= 0; i--) {
			if (255 - j * STEP_FADE < 0) {
				mBorderPaint.setAlpha(255);
				return;
			} else {
				mBorderPaint.setAlpha(255 - j * STEP_FADE);
			}
			j++;
			canvas.drawPath(mPathArr.get(i), mBorderPaint);
		}
		mBorderPaint.setAlpha(255);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i("chewei", event.getAction() + "");
		processEvent(event);
		return true;
	}

	private void processEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchDown(event);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			touchMove(event);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			touchUp(event, false);
			invalidate();
			break;

		}
	}

	private void touchDown(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		mX = x;
		mY = y;
		mPath.moveTo(x, y);
	}

	private void touchMove(MotionEvent event) {
		final float x = event.getX();
		final float y = event.getY();

		final float previousX = mX;
		final float previousY = mY;

		float cX = (x + previousX) / 2;
		float cY =(y + previousY) / 2;

		mPath.quadTo(previousX, previousY, cX, cY);

		mX = x;
		mY = y;
	}

	private void touchUp(MotionEvent event, boolean cancel) {
		Path path = new Path();
		path.set(mPath);
		mPathArr.add(path);
		mPath.rewind();
	}

}
