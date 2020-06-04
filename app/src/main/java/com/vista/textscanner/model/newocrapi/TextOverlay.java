package com.vista.textscanner.model.newocrapi;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class TextOverlay{

	@SerializedName("Message")
	private String message;

	@SerializedName("Lines")
	private List<Object> lines;

	@SerializedName("HasOverlay")
	private boolean hasOverlay;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setLines(List<Object> lines){
		this.lines = lines;
	}

	public List<Object> getLines(){
		return lines;
	}

	public void setHasOverlay(boolean hasOverlay){
		this.hasOverlay = hasOverlay;
	}

	public boolean isHasOverlay(){
		return hasOverlay;
	}

	@Override
 	public String toString(){
		return 
			"TextOverlay{" + 
			"message = '" + message + '\'' + 
			",lines = '" + lines + '\'' + 
			",hasOverlay = '" + hasOverlay + '\'' + 
			"}";
		}
}