package com.vista.textscanner.model.newocrapi;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class ParsedResultsItem{

	@SerializedName("TextOrientation")
	private String textOrientation;

	@SerializedName("ParsedText")
	private String parsedText;

	@SerializedName("FileParseExitCode")
	private int fileParseExitCode;

	@SerializedName("ErrorDetails")
	private String errorDetails;

	@SerializedName("ErrorMessage")
	private String errorMessage;

	@SerializedName("TextOverlay")
	private TextOverlay textOverlay;

	public void setTextOrientation(String textOrientation){
		this.textOrientation = textOrientation;
	}

	public String getTextOrientation(){
		return textOrientation;
	}

	public void setParsedText(String parsedText){
		this.parsedText = parsedText;
	}

	public String getParsedText(){
		return parsedText;
	}

	public void setFileParseExitCode(int fileParseExitCode){
		this.fileParseExitCode = fileParseExitCode;
	}

	public int getFileParseExitCode(){
		return fileParseExitCode;
	}

	public void setErrorDetails(String errorDetails){
		this.errorDetails = errorDetails;
	}

	public String getErrorDetails(){
		return errorDetails;
	}

	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage(){
		return errorMessage;
	}

	public void setTextOverlay(TextOverlay textOverlay){
		this.textOverlay = textOverlay;
	}

	public TextOverlay getTextOverlay(){
		return textOverlay;
	}

	@Override
 	public String toString(){
		return 
			"ParsedResultsItem{" + 
			"textOrientation = '" + textOrientation + '\'' + 
			",parsedText = '" + parsedText + '\'' + 
			",fileParseExitCode = '" + fileParseExitCode + '\'' + 
			",errorDetails = '" + errorDetails + '\'' + 
			",errorMessage = '" + errorMessage + '\'' + 
			",textOverlay = '" + textOverlay + '\'' + 
			"}";
		}
}