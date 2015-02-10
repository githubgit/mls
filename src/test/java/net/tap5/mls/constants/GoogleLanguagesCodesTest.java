package net.tap5.mls.constants;

import static org.junit.Assert.fail;

import org.junit.Test;

public class GoogleLanguagesCodesTest {

	@Test
	public void shouldHaveAllGoogleLanguages() {

		GoogleTranslateLanguages[] langs = GoogleTranslateLanguages.values();

		for (GoogleTranslateLanguages lang : langs) {

			if (GoogleLanguagesCodes.getCode(lang.name()) == null) {
				fail("Can't find language code for " + lang);
			}
		}
	}
}
