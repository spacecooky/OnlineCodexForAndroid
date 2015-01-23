package com.example.onlinecodexforandroid;

import java.io.IOException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ArmyActivity extends ActionBarActivity {

	String version = "0.0.1"; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		Intent intent = getIntent();
		String volk = intent.getStringExtra("volk");
		String id = intent.getStringExtra("id");
		
		ScrollView sv = new ScrollView(this);
		GridLayout gl = new GridLayout(this);
		gl.setColumnCount(2);
		sv.addView(gl);
		
		TextView tv = new TextView(this);
		tv.setText("Online Codex for Android V"+version);
		gl.addView(tv);
		TextView tv2 = new TextView(this);
		tv2.setText(volk);
		gl.addView(tv2);
//		TextView tv3 = new TextView(this);
//		tv3.setText(id);
//		gl.addView(tv3);
		

		List<String> l;
		try {
			l = KParserArmies.parse(getApplicationContext());
			for(int i = 0; i < l.size(); i++) {
				Button b = new Button(this);
				String text = l.get(i);
				text=text.replace("[newline]", "\n");
				b.setText(text);
				b.setId(i);
			    final int id_ = b.getId();
				gl.addView(b);
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setContentView(sv);
		}
}
