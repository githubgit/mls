package net.tap5.mls.entities;

import java.security.InvalidParameterException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
public class Language{
   
	@Id
	@NonVisual
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	@NonVisual
	private boolean selected;
	

	public Language(String title){
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
