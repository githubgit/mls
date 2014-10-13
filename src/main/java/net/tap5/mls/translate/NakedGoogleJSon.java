package net.tap5.mls.translate;


public class NakedGoogleJSon {
	public static final String END_OF_STRING = "\",";

	public static String getDetectedLanguage(String json) {
		int b = json.indexOf(",,\"") + 3;
		int e = json.indexOf(END_OF_STRING, b);
		return json.substring(b, e);
	}

	public static String getTranslation(String json) {
		int b = json.indexOf("[[[\"") + 4;
		int e = json.indexOf(END_OF_STRING, b);
		return json.substring(b, e);
	}
}
