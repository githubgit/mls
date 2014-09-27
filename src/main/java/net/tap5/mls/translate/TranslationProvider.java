package net.tap5.mls.translate;


public interface TranslationProvider {
	public String autodetect(final String phrase);

	public String translate(String from, String to, String phrase);
}
