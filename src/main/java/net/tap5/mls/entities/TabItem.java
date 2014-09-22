package net.tap5.mls.entities;

public class TabItem{
	   private String name;
	   private String label;
	   private String content;
	   
	   public TabItem(String name, String label, String content){
		   this.name = name;
		   this.label = label;
		   this.content = content;
	   }
	   
	   public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
}
