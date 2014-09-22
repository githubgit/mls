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
import org.apache.tapestry5.internal.TapestryInternalUtils;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Content copied from wikipedia
 */
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
	private List<TabItem> tabList;

	public void onActivate() {
		tabList = new ArrayList<>();

		for (GoogleTranslateLanguages lang : selectedLanguages) {

			TabItem tabItem = new TabItem(lang.toString(), TapestryInternalUtils.getLabelForEnum(messages,
					GoogleTranslateLanguages.class.getSimpleName(), lang), "Content of TabItem1");

			tabList.add(tabItem);
		}
	}

	public List getTabs() {

		return tabList;
	}

	public void set(List<GoogleTranslateLanguages> selectedLanguages) {
		this.selectedLanguages = selectedLanguages;
	}

}
