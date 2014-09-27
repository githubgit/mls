package net.tap5.mls.translate;


public class DetectLangBean implements Comparable<DetectLangBean> {
	public String getLanguage() {
		return language;
	}

	public void setLanguage(final String language) {
		this.language = language;
	}

	public Double getConfidence() {
		return confidence;
	}

	public void setConfidence(final Double confidence) {
		this.confidence = confidence;
	}

	private String language;
	private Double confidence;

	@Override
	public int compareTo(final DetectLangBean o) {
		return this.confidence.compareTo(o.confidence);
	}
}
