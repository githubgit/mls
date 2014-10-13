package net.tap5.mls.translate;

import java.io.IOException;

import net.tap5.mls.constants.GoogleLanguagesCodes;
import net.tap5.mls.constants.GoogleTranslateLanguages;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

public class GoogleTranslation implements TranslationProvider {
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	@Override
	public TranslationDTO translate(GoogleTranslateLanguages to, String phrase) {
		TranslationDTO dto = new TranslationDTO();

		final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
		String lang = GoogleLanguagesCodes.getCode(GoogleTranslateLanguages.French.name());

		final GenericUrl gurl = new GenericUrl(
				new StringBuilder()
						.append("https://translate.google.com/translate_a/single?client=t&sl=auto")
						.append("&hl=")
						.append(lang)
						.append("&dt=bd&dt=ex&dt=ld&dt=md&dt=qc&dt=rw&dt=rm&dt=ss&dt=t&dt=at&dt=sw&ie=UTF-8&oe=UTF-8&otf=2&ssel=3&tsel=0&q=Hello%20akong%20higala")
						.toString());
		HttpRequest request;

		try {
			request = requestFactory.buildGetRequest(gurl);
			request.getHeaders().setContentType("application/json");

			String json = request.execute().parseAsString();

			System.out.println(json);
			// find detected lang
			String code = NakedGoogleJSon.getDetectedLanguage(json);
			// String language = GoogleLanguagesCodes.getLanguage(code);
			String translation = NakedGoogleJSon.getTranslation(json);

			dto.setLangCode(code);
			dto.setPhrase(translation);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}

}
