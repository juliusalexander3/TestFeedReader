package testPrograms;

import helpClasses.CAPfeed;
import helpClasses.CAPfeedParser;
import helpClasses.FeedMsg;

import java.io.Reader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class Prueba {

	public static void main(String[] args) throws XmlPullParserException, IOException {
		// TODO Auto-generated method stub
		String urlString ="https://correo1.conagua.gob.mx/feedsmn/feedalert.aspx";

		InputStream stream = null;
		CAPfeed mFeed;
		CAPfeedParser mParser=null; 
		try {
			stream = downloadUrl(urlString);        
			mParser = new CAPfeedParser(stream);
			mFeed =mParser.readFeed();
			System.out.println("Titulo Feed" + mFeed.getTitle());
			System.out.println("Updated"+  mFeed.getUpdated());
			System.out.println("Updated"+  mFeed.getRights());
			List<FeedMsg> entradas = mFeed.getEntries();
			for(FeedMsg mensaje: entradas){
				System.out.println(mensaje.getTitle());
			}
			
			// Makes sure that the InputStream is closed after the app is
			// finished using it.
		} finally {
			if (mParser.in!=null){
				mParser.CloseInputStream();
			}
			if (stream != null) {
				stream.close();
			} 
		}







	}

	// Given a string representation of a URL, sets up a connection and gets
	// an input stream.
	private static InputStream downloadUrl(String urlString) throws IOException {
		URL url = new URL(urlString);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setReadTimeout(10000 /* milliseconds */);
		conn.setConnectTimeout(15000 /* milliseconds */);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		// Starts the query
		conn.connect();
		return conn.getInputStream();
	}


}
