package com.example.onlinecodexforandroid;

import android.content.Context;
import android.widget.Button;

public class KKontingent extends Button{
	
	public KKontingent(Context context){
		super(context);
	}
	
	public String getVolk(){
		return (String) getText();
	}
	
	public void setVolk(String s){
		setText(s);
	}
	
}
