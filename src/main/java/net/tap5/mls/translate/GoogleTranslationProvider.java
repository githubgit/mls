package net.tap5.mls.translate;

import java.io.IOException;
import java.net.URLEncoder;

import javax.jws.WebService;

import net.tap5.mls.constants.GoogleLanguagesCodes;
import net.tap5.mls.constants.GoogleTranslateLanguages;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

@WebService
public class GoogleTranslationProvider implements TranslationProvider {
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	@Override
	public TranslationDTO translate(final GoogleTranslateLanguages to, final String phrase) {
		TranslationDTO dto = new TranslationDTO();

		try {

			final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
			String langCode = GoogleLanguagesCodes.getCode(to.name());

			String encodedPhrase = URLEncoder.encode(phrase, org.apache.commons.lang3.CharEncoding.UTF_8);

			final GenericUrl gurl = new GenericUrl(
					new StringBuilder()
					.append("https://translate.google.com/translate_a/single?client=t&sl=auto")
					.append("&hl=")
					.append(langCode)
					.append("&dt=bd&dt=ex&dt=ld&dt=md&dt=qc&dt=rw&dt=rm&dt=ss&dt=t&dt=at&dt=sw&ie=UTF-8&oe=UTF-8&otf=2&ssel=3&tsel=0&q=")
					.append(encodedPhrase)
					.toString());

			HttpRequest request = requestFactory.buildGetRequest(gurl);
			request.getHeaders().setContentType("application/json");

			String json = request.execute().parseAsString();

			//System.out.println(json);
			// find detected lang
			String detectedCode = FlatGoogleJSon.getDetectedLanguage(json);
			String detecteLang = GoogleLanguagesCodes.getLanguage(detectedCode);
			String translation = FlatGoogleJSon.getTranslation(json);

			dto.setDetectedLangCode(detectedCode);
			dto.setTranslation(translation);
			dto.setDetectedLangName(detecteLang);
			dto.setLangCode(langCode);
			dto.setLangName(to.name());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}

}
