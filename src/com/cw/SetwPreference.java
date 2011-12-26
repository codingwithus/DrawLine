/**
 * 
 */
package com.cw;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * @author chenwei10
 *
 */
public class SetwPreference extends DialogPreference{

	private int mProgress = 50;
	public SetwPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        if (view != null) {
        	final View view1 = (View)view.findViewById(R.id.seek_view);
            SeekBar seek = (SeekBar)view.findViewById(R.id.seek);
            String init = getPersistedString(null);
            if (init != null) {
            	mProgress = Integer.parseInt(init);
			}
            seek.setProgress(mProgress);
            seek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
    			LayoutParams ll = (LayoutParams) view1.getLayoutParams();
    			int mBaseHeight = 1;
    			public void onStopTrackingTouch(SeekBar seekBar) {
    				// TODO Auto-generated method stub
    				Log.i("onStopTrackingTouch", seekBar.getProgress()+"");
    				mProgress = seekBar.getProgress();
    				ll.height = mBaseHeight+mProgress*10/100;
    				view1.setLayoutParams(ll);
    			}
    			
    			public void onStartTrackingTouch(SeekBar seekBar) {
    				// TODO Auto-generated method stub
    				Log.i("onStartTrackingTouch", seekBar.getProgress()+"");
    				mProgress = seekBar.getProgress();
    				ll.height = mBaseHeight+mProgress*10/100;
    				view1.setLayoutParams(ll);

    			}
    			
    			public void onProgressChanged(SeekBar seekBar, int progress,
    					boolean fromUser) {
    				// TODO Auto-generated method stub
    				mProgress = seekBar.getProgress();
    				ll.height = mBaseHeight+mProgress*10/100;
    				view1.setLayoutParams(ll);
    			}
    		});
		}

    }
    @Override
    protected void onDialogClosed(boolean positiveResult) {
    	// TODO Auto-generated method stub
    	super.onDialogClosed(positiveResult);
        if (positiveResult) {
            if (callChangeListener(mProgress)) {
                setText(String.valueOf(mProgress));
            }
        }
    }
    /**
     * Saves the text to the {@link SharedPreferences}.
     * 
     * @param text The text to save
     */
    public void setText(String text) {
        final boolean wasBlocking = shouldDisableDependents();
        
        persistString(text);
        
        final boolean isBlocking = shouldDisableDependents(); 
        if (isBlocking != wasBlocking) {
            notifyDependencyChange(isBlocking);
        }
    }
}
