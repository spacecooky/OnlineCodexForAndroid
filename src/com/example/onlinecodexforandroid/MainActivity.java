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

public class MainActivity extends ActionBarActivity {

	String version = "0.0.1"; 
	final MainActivity man = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
			
			ScrollView sv = new ScrollView(this);
			GridLayout gl = new GridLayout(this);
			gl.setColumnCount(4);
			sv.addView(gl);
			
			TextView tv = new TextView(this);
			tv.setText("Online Codex for Android V"+version);
			gl.addView(tv);
			gl.addView(new TextView(this));
			gl.addView(new TextView(this));
			gl.addView(new TextView(this));
			for(int i = 0; i < 15; i++) {
				final Button b = new Button(this);
				b.setText("Neues \nKontingent");
				b.setId(i);
				gl.addView(b);
				gl.addView(new TextView(this));
				gl.addView(new TextView(this));
				gl.addView(new TextView(this));
				b.setOnClickListener(new View.OnClickListener() {
			        public void onClick(View view) {
			        	Intent intent = new Intent(man, ArmyActivity.class);
			    		intent.putExtra("volk", b.getText());
			    		intent.putExtra("id", b.getId());
			    		startActivity(intent);
			        }
			    });
			}
			this.setContentView(sv);
		}
}
