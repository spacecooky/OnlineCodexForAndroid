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
		int id = Integer.parseInt(intent.getStringExtra("id"));
		
		ScrollView sv = new ScrollView(this);
		GridLayout gl = new GridLayout(this);
		gl.setColumnCount(2);
		sv.addView(gl);
		
		final KKontingent kontingent = KStructures.kontingente.get(id);
		String volk = kontingent.getVolk();
		
		TextView tv = new TextView(this);
		tv.setText("Online Codex for Android V"+version);
		gl.addView(tv);
		TextView tv3 = new TextView(this);
		tv3.setText(volk);
		gl.addView(tv3);
		

		if(volk.equals("Neues \nKontingent")){
			List<String> l;
			try {
				l = KParserArmies.parse(getApplicationContext());
				for(int i = 0; i < l.size(); i++) {
					Button b = new Button(this);
					String text1 = l.get(i);
					text1=text1.replace("[newline]", "\n");
					b.setText(text1);
					final String text2 = text1;
				    b.setOnClickListener(new View.OnClickListener() {
				        public void onClick(View view) {
				        	kontingent.setVolk(text2);
				        }
				    });
					gl.addView(b);
				}
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.setContentView(sv);
		}
}
