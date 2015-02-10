package net.tap5.mls.translate;

import java.util.List;

import net.tap5.mls.constants.GoogleTranslateLanguages;

public interface TranslatorService {

	public List<TranslationDTO> translate(String phrase, List<GoogleTranslateLanguages> langs);

}