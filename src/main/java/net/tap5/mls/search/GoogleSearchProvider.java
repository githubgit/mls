package net.tap5.mls.search;

import java.net.URLEncoder;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import net.tap5.mls.constants.GoogleLanguagesCodes;
import net.tap5.mls.translate.TranslationDTO;

public class GoogleSearchProvider implements SearchProvider {
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	@Override
	public SearchDTO search(TranslationDTO translation) {
		//FIXME 
		//implementation of Google search logic
		return null;
	}

	public static void main(String[] args){
		
		String query = "Very best moments";
		
		final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
//		String langCode = GoogleLanguagesCodes.getCode(dto.name());
//
//		String encodedPhrase = URLEncoder.encode(query, org.apache.commons.lang3.CharEncoding.UTF_8);
//
//		final GenericUrl gurl = new GenericUrl(
//				new StringBuilder()
//				.append("https://translate.google.com/translate_a/single?client=t&sl=auto")
//				.append("&hl=")
//				.append(langCode)
//				.append("&dt=bd&dt=ex&dt=ld&dt=md&dt=qc&dt=rw&dt=rm&dt=ss&dt=t&dt=at&dt=sw&ie=UTF-8&oe=UTF-8&otf=2&ssel=3&tsel=0&q=")
//				.append(encodedPhrase)
//				.toString());
//
//		HttpRequest request = requestFactory.buildGetRequest(gurl);
//		request.getHeaders().setContentType("application/json");
//
//		String json = request.execute().parseAsString();
//
//		System.out.println(json);
		
	}
	
}
