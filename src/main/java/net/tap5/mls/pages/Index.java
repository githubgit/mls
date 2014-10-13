package net.tap5.mls.pages;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import net.tap5.mls.constants.GoogleTranslateLanguages;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.services.TypeCoercer;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.util.EnumSelectModel;
import org.apache.tapestry5.util.EnumValueEncoder;

/**
 * Start page of application mls.
 */
public class Index {
	@Property
	@Inject
	@Symbol(SymbolConstants.TAPESTRY_VERSION)
	private String tapestryVersion;

	@Inject
	private AlertManager alertManager;

	public Date getCurrentTime() {
		return new Date();
	}

	// //////////////////////////////////////////////////////////////////

	@Property
	@Persist(PersistenceConstants.SESSION)
	private List<GoogleTranslateLanguages> selectedLanguages;

	@InjectPage
	private Results resultsPage;

	@Inject
	private Messages messages;
	@Inject
	TypeCoercer typeCoercer;
	@Inject
	Request request;
	@Inject
	PageRenderLinkSource linkSource;

	public ValueEncoder<GoogleTranslateLanguages> getEncoder() {
		return new EnumValueEncoder<GoogleTranslateLanguages>(typeCoercer, GoogleTranslateLanguages.class);
	}

	public SelectModel getModel() {
		return new EnumSelectModel(GoogleTranslateLanguages.class, messages);
	}

	Object onSuccessFromSearch() {
		if (selectedLanguages == null)
			selectedLanguages = Collections.emptyList();

		resultsPage.set(selectedLanguages);

		return resultsPage;
	}
}
