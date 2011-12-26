package com.cw;


import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;

public class DrawLine extends Activity {
	private Handler timeHandler = new Handler();
	private Runnable runtask = new Runnable() {
		
		public void run() {
			// TODO Auto-generated method stub
			timeHandler.postDelayed(runtask, 500);
			Rect dirty = new Rect(10, 10, 10, 150);
			view.invalidate(dirty );
		}
	};
	private HandView view;
	private HandWriteView view2;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        OnColorChangedListener l = new OnColorChangedListener() {
//            public void colorChanged(int color) {
//            	Log.i("color", color+"");
//            }
//        };
//        int mInitialColor = 0xffdd5577;
//		setContentView(new ColorPickerView(this, l, mInitialColor ));
//        view = new MyView(this);
//        setContentView(view);
//        timeHandler.postDelayed(runtask, 100);
        view = new HandView(this);
//        view2 = new HandWriteView(this);
//        view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
//        LinearLayout main = (LinearLayout)getLayoutInflater().inflate(R.layout.main, null);
//        main.addView(view);
//        main.addView(view2);
        setContentView(view);
        
        
        
        
//        setContentView(R.layout.main);

//        Button btn = (Button)findViewById(R.id.button1);
//        btn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(DrawLine.this,SndaIMESettingActivity.class);
//				startActivity(intent);
//			}
//		});
//        Button btn2 = (Button)findViewById(R.id.button2);
//        btn2.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DrawLine.this);
//				setTitle(prefs.getString("setw", "unset1")+"&&&"+prefs.getString("bodor", "hh"));
//			}
//		});
        
        
        
//        final View view1 = (View)findViewById(R.id.view1);
//        SeekBar seek = (SeekBar)findViewById(R.id.seek);
//        seek.setMax(10);
//        seek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
//			LayoutParams ll = (LayoutParams) view1.getLayoutParams();
//			int mBaseHeight = 2;
//			@Override
//			public void onStopTrackingTouch(SeekBar seekBar) {
//				// TODO Auto-generated method stub
//				Log.i("onStopTrackingTouch", seekBar.getProgress()+"");
//			}
//			
//			@Override
//			public void onStartTrackingTouch(SeekBar seekBar) {
//				// TODO Auto-generated method stub
//				Log.i("onStartTrackingTouch", seekBar.getProgress()+"");
//				ll.height = mBaseHeight+seekBar.getProgress()*10/100;
//				view1.setLayoutParams(ll);
//
//			}
//			
//			@Override
//			public void onProgressChanged(SeekBar seekBar, int progress,
//					boolean fromUser) {
//				// TODO Auto-generated method stub
//				Log.i("onProgressChanged", seekBar.getProgress()+" "+fromUser);
//				ll.height = mBaseHeight+seekBar.getProgress()*10/100;
//				view1.setLayoutParams(ll);
//			}
//		});
    }
   
}