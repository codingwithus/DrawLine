/**
 * 
 */
package com.cw;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * @author chenwei10
 * 
 */
public class HandWriteView extends FrameLayout {

	private static final int STEP_FADE = 20;

	private float mX;
	private float mY;
	private Path mPath = new Path();
	private int mInvalidateExtraBorder = 10;
	private final Rect mInvalidRect = new Rect();
	private float mCurveEndX;
	private float mCurveEndY;
	
	ArrayList<Path> mPathArr = new ArrayList<Path>();
	private Paint mBorderPaint;


	/**
	 * @param context
	 */
	public HandWriteView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public HandWriteView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public HandWriteView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	public void init(){
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
		canvas.drawColor(0xff0000ff);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if (canvas != null) {
			canvas.drawColor(0xffff00ff);
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
			canvas.drawPath(mPath,mBorderPaint);
		}
	}
	private void processEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchDown(event);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			Rect rect = touchMove(event);
			if (rect != null) {
				invalidate(rect);
			}
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

		final int border = mInvalidateExtraBorder;
		mInvalidRect.set((int) x - border, (int) y - border, (int) x + border,
				(int) y + border);

		mCurveEndX = x;
		mCurveEndY = y;

	}

	private Rect touchMove(MotionEvent event) {
		Rect areaToRefresh = null;

		final float x = event.getX();
		final float y = event.getY();

		final float previousX = mX;
		final float previousY = mY;

//		final float dx = Math.abs(x - previousX);
//		final float dy = Math.abs(y - previousY);

		areaToRefresh = mInvalidRect;

		// start with the curve end
		final int border = mInvalidateExtraBorder;
		areaToRefresh.set((int) mCurveEndX - border, (int) mCurveEndY - border,
				(int) mCurveEndX + border, (int) mCurveEndY + border);

		float cX = mCurveEndX = (x + previousX) / 2;
		float cY = mCurveEndY = (y + previousY) / 2;

		mPath.quadTo(previousX, previousY, cX, cY);

		// union with the control point of the new curve
		areaToRefresh.union((int) previousX - border, (int) previousY - border,
				(int) previousX + border, (int) previousY + border);

		// union with the end point of the new curve
		areaToRefresh.union((int) cX - border, (int) cY - border, (int) cX
				+ border, (int) cY + border);

		mX = x;
		mY = y;

		return areaToRefresh;
	}

	private void touchUp(MotionEvent event, boolean cancel) {
		Path path = new Path();
		path.set(mPath);
		mPathArr.add(path);
		mPath.reset();
	}
}
