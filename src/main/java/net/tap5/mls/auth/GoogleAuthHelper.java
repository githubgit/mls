package net.tap5.mls.auth;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;

import net.tap5.mls.constants.GoogleLanguagesCodes;
import net.tap5.mls.constants.GoogleTranslateLanguages;
import net.tap5.mls.translate.NakedGoogleJSon;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * A helper class for Google's OAuth2 authentication API.
 *
 * @version 20130224
 * @author Matyas Danter
 */
public final class GoogleAuthHelper {


	/**
	 * Please provide a value for the CLIENT_ID constant before proceeding, set
	 * this up at https://code.google.com/apis/console/
	 */
	private static final String CLIENT_ID = "865725949135-tq45uocblldfdrvufb3qm6482ghompso.apps.googleusercontent.com";
	/**
	 * Please provide a value for the CLIENT_SECRET constant before proceeding,
	 * set this up at https://code.google.com/apis/console/
	 */
	private static final String CLIENT_SECRET = "gl-B0uj2r1lR86cFiHgjIKd5";
	/**
	 * Callback URI that google will redirect to after successful authentication
	 */
	private String collbackUri = "http://localhost:8080/mls/oauth.jsp";
	// start google authentication constants
	private static final Collection<String> SCOPE = Arrays
			.asList("https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email"
					.split(";"));
	private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	// end google authentication constants
	private String stateToken;
	private final GoogleAuthorizationCodeFlow flow;

	/**
	 * Constructor initializes the Google Authorization Code Flow with CLIENT
	 * ID, SECRET, and SCOPE
	 */
	public GoogleAuthHelper() {
		flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, SCOPE)
				.build();
		generateStateToken();
	}

	public String getCollbackUri() {
		return collbackUri;
	}

	public void setCollbackUri(String collbackUri) {
		this.collbackUri = collbackUri;
	}

	/**
	 * Builds a login URL based on client ID, secret, callback URI, and scope
	 */
	public String buildLoginUrl() {
		final GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
		return url.setRedirectUri(collbackUri).setState(stateToken).build();
	}

	/**
	 * Generates a secure state token
	 */
	private void generateStateToken() {
		SecureRandom sr1 = new SecureRandom();
		stateToken = "google;" + sr1.nextInt();
	}

	/**
	 * Accessor for state token
	 */
	public String getStateToken() {
		return stateToken;
	}

	/**
	 * Expects an Authentication Code, and makes an authenticated request for
	 * the user's profile information
	 *
	 * @return JSON formatted user profile information
	 * @param authCode
	 *            authentication code provided by google
	 */
	public String getUserInfoJson(final String authCode) throws IOException {
		final GoogleTokenResponse response = flow.newTokenRequest(authCode).setRedirectUri(collbackUri).execute();
		final Credential credential = flow.createAndStoreCredential(response, null);
		final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
		// Make an authenticated request
		final GenericUrl url = new GenericUrl(USER_INFO_URL);
		final HttpRequest request = requestFactory.buildGetRequest(url);
		request.getHeaders().setContentType("application/json");
		final String jsonIdentity = request.execute().parseAsString();
		return jsonIdentity;
	}

	public String getjSon(String v2url) throws IOException {
		final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
		// Make an authenticated request
		final GenericUrl url = new GenericUrl(v2url);
		final HttpRequest request = requestFactory.buildGetRequest(url);
		request.getHeaders().setContentType("application/json");
		final String json = request.execute().parseAsString();
		return json;
	}

	public static void main(final String[] args) {
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
			String language = GoogleLanguagesCodes.getLanguage(code);
			String translation = NakedGoogleJSon.getTranslation(json);

			System.out.println(language);
			System.out.println(translation);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
