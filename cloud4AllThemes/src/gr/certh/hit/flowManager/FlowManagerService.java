package gr.certh.hit.flowManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;

public class FlowManagerService {
	
	public static Hashtable parseJsonNeedsAndPreferencesResponse(InputStream input) throws IOException {
		Log.p("parseJsonNeedsAndPreferencesResponse-0");
		InputStreamReader dataReader = new InputStreamReader(input);
		Log.p("parseJsonNeedsAndPreferencesResponse-1");
		JSONParser parser = new JSONParser();
		Hashtable myHashtable = parser.parse(dataReader);
		Log.p("parseJsonNeedsAndPreferencesResponse-2");
		
		return myHashtable;
	}
	
//	public InputStream requestNeedsAndPreferences() { // Currently hardcoded for user os_jme  - later on to be retrieved by the textArea in the Login form
//		String url = "http://160.40.60.183:8081/os_jme/settings/%7B%22OS%22%3A%7B%22id%22%3A%22web%22%7D%2C%22solutions%22%3A%5B%7B%22id%22%3A%22info.cloud4all.JME%22%7D%5D%7D";
//
//		ConnectionRequest con = new ConnectionRequest();
//
//		con.setUrl(url);
//		con.setPost(false);
//		con.setContentType("application/json");
//		con.setFailSilently(true);
////		con.setReadResponseForErrors(false);
//
//		InfiniteProgress prog = new InfiniteProgress();
//		Dialog dlg = prog.showInifiniteBlocking();
//		con.setDisposeOnCompletion(dlg);
//
//		NetworkManager.getInstance().addToQueueAndWait(con);
//		byte[] data = con.getResponseData();
//		System.out.println(data);
//		String response = openFileToString(data);
//		System.out.println("response: " + response);
//
//		InputStream is = new ByteArrayInputStream(response.getBytes());
//
//		return is;
//	}
	
	public InputStream requestNeedsAndPreferences2(String serverIp, String username) { // Currently hardcoded for user os_jme  - later on to be retrieved by the textArea in the Login form
		String url = "http://" + serverIp + "/" + username + "/settings/%7B%22OS%22%3A%7B%22id%22%3A%22web%22%7D%2C%22solutions%22%3A%5B%7B%22id%22%3A%22info.cloud4all.JME%22%7D%5D%7D";
		Log.p("requestNeedsAndPreferences2-0");
		ConnectionRequest con = new ConnectionRequest(){
		      @Override
		      protected void handleErrorResponseCode(int code, String message) {
		        Log.p("*******Error: " + code + " Message: " + message);
//		        Dialog.show("Error!", "Error in username!", "OK", null);
		      }

		      @Override
		      protected void handleException(Exception e) {
		        Log.p("------Error: " + e.getMessage());
		      }
		};
		Log.p("requestNeedsAndPreferences2-1");
		
		con.setUrl(url);
		con.setPost(false);
		con.setContentType("application/json");
		con.setFailSilently(true);
		con.setReadResponseForErrors(true);
		Log.p("requestNeedsAndPreferences2-2");

		InfiniteProgress prog = new InfiniteProgress();
		Dialog dlg = prog.showInifiniteBlocking();
		con.setDisposeOnCompletion(dlg);
		Log.p("requestNeedsAndPreferences2-3");

		NetworkManager.getInstance().addToQueueAndWait(con);
		byte[] data = con.getResponseData();
		if(data != null) {
			System.out.println(data);
			String response = openFileToString(data);
			Log.p("requestNeedsAndPreferences2-4");
	
			InputStream is = new ByteArrayInputStream(response.getBytes());
			return is;
		} else {
			return null;
		}
	}
	
	public String openFileToString(byte[] _bytes) {
		String file_string = "";
		if(_bytes != null) { // _bytes = null WHEN 1. server is down OR 2. username does not exist SO default values for UI are set
			for (int i = 0; i < _bytes.length; i++) {
				file_string += (char) _bytes[i];
			}
		}
		return file_string;
	}

}
