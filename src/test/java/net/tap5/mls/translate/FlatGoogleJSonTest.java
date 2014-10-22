package net.tap5.mls.translate;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FlatGoogleJSonTest {

	private static final String JSON = "[[[\"Bonjour, mon ami\",\"Hello akong higala\"]],,\"ceb\",,[[\"Bonjour, mon ami\",[1],true,false,1000,0,4,0]],[[\"Hello akong higala\",1,[[\"Bonjour, mon ami\",1000,true,false]],[[0,18]],\"Hello akong higala\"]],,,[[\"ceb\"],,[0.030213101]]]";
	private static final String LANGUAGE = "ceb";
	private static final String TRANSLATION = "Bonjour, mon ami";

    @Test
	public void shouldReturnDetectedLang() {
		String res = FlatGoogleJSon.getDetectedLanguage(JSON);
		assertEquals(LANGUAGE, res);
	}

	@Test
	public void shouldReturnTranslation() {
		String t = FlatGoogleJSon.getTranslation(JSON);
		assertEquals(TRANSLATION, t);
	}
}
