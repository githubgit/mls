package net.tap5.mls.pages;

import java.util.ArrayList;
import java.util.List;

import net.tap5.mls.constants.GoogleTranslateLanguages;
import net.tap5.mls.search.SearchServiceImpl;
import net.tap5.mls.search.SearchDTO;
import net.tap5.mls.search.SearchService;
import net.tap5.mls.translate.TranslationDTO;
import net.tap5.mls.translate.TranslationServiceImpl;
import net.tap5.mls.translate.TranslatorService;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@Import(stylesheet = "context:css/js.css")
public class Results {
	@PageActivationContext
	@Property
	private String resultTab;

	@Persist(PersistenceConstants.SESSION)
	private List<GoogleTranslateLanguages> selectedLanguages;

	@Persist(PersistenceConstants.SESSION)
	private String phrase;

	@Property
	private int index;

	@Property
	private TranslationDTO dto;
	@Inject
	private Messages messages;

	@Property
	private List<TranslationDTO> langs = new ArrayList<>();

	@Inject
	private JavaScriptSupport javaScriptSupport;

	private TranslatorService translationService = new TranslationServiceImpl();
	private SearchService searchService = new SearchServiceImpl();

	private List<SearchDTO> searchResults = new ArrayList<>(); 

	void afterRender() {
		javaScriptSupport.require("bootstrap/tab");
	}

	public String getTabCssClass() {
		return index == 0 ? "active" : "";
	}

	public String getTabPaneCssClass() {
		return index == 0 ? "fade in active" : "fade";
	}

	//when we are coming to this page
	public void onActivate() {
		if (!langs.isEmpty())
			langs.clear();
        //STEP 1 translation to every selected language
		langs = translationService.translate(phrase, selectedLanguages);
		
		//STEP 2 performing search for every translation
		searchResults = searchService.search(langs);
		
	}

	public void setLanguages(final List<GoogleTranslateLanguages> selectedLanguages) {
		this.selectedLanguages = selectedLanguages;
	}

	public void setPhrase(final String phrase) {
		this.phrase = phrase;
	}

	public String getPhrase() {
		return phrase;
	}

}
