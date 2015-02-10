package net.tap5.mls.search;

import net.tap5.mls.translate.TranslationDTO;

public interface SearchProvider {

	public SearchDTO search(TranslationDTO translation);
	
}
