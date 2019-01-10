package app.works;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.nlp.AipNlp;

public class SemblanceWork {
	public static final String APP_ID = "10508969";
	public static final String API_KEY = "heO94PxAyEhOn0lFFE0jTa2L";
	public static final String SECRET_KEY = "AfNPCBR7LfVIBaM2eHboPa3QIRkSCm8y";

	public static float semblance(String text1, String text2) throws UnsupportedEncodingException {
		AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("model", "CNN");
		
		text1 = processWord(text1);
		text2 = processWord(text2);

		int text1Length = text1.length();
		int text2Length = text2.length();
		if (text1Length > 510) {
			text1 = text1.substring(1, 500);
		}
		if (text2Length > 510) {
			text2 = text2.substring(1, 500);
		}

		JSONObject res = client.simnet(text1, text2, options);
		return res.getFloat("score");
		// System.out.println(res.toString(2));
	}
	
	public static String processWord(String text) {
		text = text.replaceAll("\\s", " ");
		return text;
	}
}
