package net.tap5.mls.translate;

public class TranslationDTO {
	private String langName;
	private String langCode;
	private String detectedLangName;
	private String detectedLangCode;
	private String translation;

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(final String translation) {
		this.translation = translation;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(final String langCode) {
		this.langCode = langCode;
	}

	public String getLangName() {
		return langName;
	}

	public void setLangName(final String langName) {
		this.langName = langName;
	}

	public String getDetectedLangCode() {
		return detectedLangCode;
	}

	public void setDetectedLangCode(final String detectedLangCode) {
		this.detectedLangCode = detectedLangCode;
	}

	public String getDetectedLangName() {
		return detectedLangName;
	}

	public void setDetectedLangName(String detectedLangName) {
		this.detectedLangName = detectedLangName;
	}
}
