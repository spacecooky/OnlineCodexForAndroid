package com.example.onlinecodexforandroid;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.text.GetChars;
import android.util.Xml;

public class KParserArmies {

    private static final String ns = null;
	
	public static List<String> parse(Context context) throws XmlPullParserException, IOException {
		//FileInputStream fin = new FileInputStream(new File("xml/armies.xml"));
		//FileInputStream fin = new FileInputStream(new File("res/raw/armies.xml"));
		InputStream fin = context.getResources().getAssets().open("armies.xml");
		//getResources().openRawResource(R.raw.yourfile)
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(fin, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            fin.close();
        }
    }

	private static List<String> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
	    List<String> entries = new ArrayList<String>();

	    parser.require(XmlPullParser.START_TAG, ns, "armies");
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        // Starts by looking for the entry tag
	        if (name.equals("army")) {
	            entries.add(readEntry(parser));
	        } else {
	            skip(parser);
	        }
	    }  
	    return entries;
	}
	
	private static String readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "army");
	    String army = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "army");
	    
	    return army;
	}

	// For the tags title and summary, extracts their text values.
	private static String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
	    String result = "";
	    if (parser.next() == XmlPullParser.TEXT) {
	        result = parser.getText();
	        parser.nextTag();
	    }
	    return result;
	}

	private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
	    if (parser.getEventType() != XmlPullParser.START_TAG) {
	        throw new IllegalStateException();
	    }
	    int depth = 1;
	    while (depth != 0) {
	        switch (parser.next()) {
	        case XmlPullParser.END_TAG:
	            depth--;
	            break;
	        case XmlPullParser.START_TAG:
	            depth++;
	            break;
	        }
	    }
	 }
}
