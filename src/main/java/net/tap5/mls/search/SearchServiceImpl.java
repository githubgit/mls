package net.tap5.mls.search;

import java.util.ArrayList;
import java.util.List;

import net.tap5.mls.translate.TranslationDTO;

public class SearchServiceImpl implements SearchService {

	SearchProvider searchProvider = new GoogleSearchProvider();
	
	@Override
	public List<SearchDTO> search(List<TranslationDTO> translations) {
		List<SearchDTO> list = new ArrayList<>();
		
		for(TranslationDTO translation: translations){
			SearchDTO dto = searchProvider.search(translation);
			list.add(dto);
		}
		return list;
	}
}
