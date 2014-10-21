package net.tap5.mls.pages;

import java.util.ArrayList;
import java.util.List;

import net.tap5.mls.constants.GoogleTranslateLanguages;
import net.tap5.mls.entities.TabItem;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Results {
	@PageActivationContext
	@Property
	private String resultTab;
	@Persist(PersistenceConstants.SESSION)
	private List<GoogleTranslateLanguages> selectedLanguages;

	@InjectPage
	private Index indexPage;

	@Property
	private TabItem tabItem;
	@Inject
	private Messages messages;

	@Property
	private List<TabItem> tabs = new ArrayList<>();

	public void onActivate() {

		for (GoogleTranslateLanguages lang : selectedLanguages) {

			TabItem tabItem = new TabItem(lang.toString(), lang.name(), "Content of " + lang.name() + " tab");
			System.out.println(lang.name());
			tabs.add(tabItem);
		}
	}

	public void set(List<GoogleTranslateLanguages> selectedLanguages) {
		this.selectedLanguages = selectedLanguages;
	}

}
