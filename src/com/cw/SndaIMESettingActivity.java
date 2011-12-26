/**
 * 
 */
package com.cw;

import android.os.Bundle;
import android.preference.PreferenceActivity;


/**
 * @author chenwei10
 *
 */
public class SndaIMESettingActivity extends PreferenceActivity {
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	addPreferencesFromResource(R.xml.preferences);
}
}
