package task301221;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import com.google.gson.Gson;

class JsonObject{
	Menu menu;

	@Override
	public String toString() {
		return "JsonObject [menu=" + menu + "]";
	}
}
class Menu {
	
	//{"menu":{"id":"file","value":"File","height":234,"popup":{"menuitem":[{"value":"New","onclick":"CreateDoc()"},{"value":"Open","onclick":"OpenDoc()"},{"value":"Save","onclick":"SaveDoc()"}]}}}
	
	private String id;
	private String value;
	private String height;
	@Override
	public String toString() {
		return "Menu [id=" + id + ", value=" + value + ", height=" + height + ", popup=" + popup + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public Popup getPopup() {
		return popup;
	}
	public void setPopup(Popup popup) {
		this.popup = popup;
	}
	private Popup popup;
}

class Popup{
	MenuItem[] menuitem;

	@Override
	public String toString() {
		return "Popup [menuitem=" + Arrays.toString(menuitem) + "]";
	}
}

class MenuItem{
	String value;
	String onclick;
	@Override
	public String toString() {
		return "MenuItem [value=" + value + ", onclick=" + onclick + "]";
	}
}


public class HttpGetCall {
	
	public static void main(String...args) {
		
		//Declarations
		String sUrl="https://api.jsonbin.io/b/61a0a59501558c731cc910b5/1";
		String line;									//to store each line
		StringBuffer outputContent=new StringBuffer();   //to take advantage of append
		
		try {
			//Formating URL
			URL url=new URL(sUrl);
			//opening Connection to url.
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line=br.readLine())!=null) 
				outputContent.append(line);
			
			//securing connections
			br.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(outputContent);
		//{"menu":{"id":"file","value":"File","height":234,"popup":{"menuitem":[{"value":"New","onclick":"CreateDoc()"},{"value":"Open","onclick":"OpenDoc()"},{"value":"Save","onclick":"SaveDoc()"}]}}}
		String json=new String(outputContent);
		Gson gson=new Gson();
		JsonObject menu=gson.fromJson(json, JsonObject.class);
		System.out.println(menu);
		

	}
}
