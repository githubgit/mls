package net.tap5.mls.translate;

import net.tap5.mls.constants.GoogleTranslateLanguages;


public interface TranslationProvider {
	public TranslationDTO translate(final GoogleTranslateLanguages to, final String phrase);
}
