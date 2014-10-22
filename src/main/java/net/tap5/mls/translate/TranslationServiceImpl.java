package net.tap5.mls.translate;

import java.util.ArrayList;
import java.util.List;

import net.tap5.mls.constants.GoogleTranslateLanguages;


public class TranslationServiceImpl implements TranslatorService {

	TranslationProvider provider = new GoogleTranslation();

	public TranslationServiceImpl() {
	}


	@Override
	public List<TranslationDTO> translate(final String phrase, final List<GoogleTranslateLanguages> langs) {
		List<TranslationDTO> list = new ArrayList<>();

		for (GoogleTranslateLanguages lang : langs){
			TranslationDTO dto = provider.translate(lang, phrase);
			list.add(dto);
		}
		return list;
	}
}
