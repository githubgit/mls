package net.tap5.mls.search;

import java.util.List;

import net.tap5.mls.translate.TranslationDTO;

public interface SearchService {

	List<SearchDTO> search(List<TranslationDTO> langs);

}
