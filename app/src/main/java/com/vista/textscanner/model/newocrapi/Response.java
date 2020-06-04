package com.vista.textscanner.model.newocrapi;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class Response{

	@SerializedName("IsErroredOnProcessing")
	private boolean isErroredOnProcessing;

	@SerializedName("ParsedResults")
	private List<ParsedResultsItem> parsedResults;

	@SerializedName("ProcessingTimeInMilliseconds")
	private String processingTimeInMilliseconds;

	@SerializedName("SearchablePDFURL")
	private String searchablePDFURL;

	@SerializedName("OCRExitCode")
	private int oCRExitCode;

	public void setIsErroredOnProcessing(boolean isErroredOnProcessing){
		this.isErroredOnProcessing = isErroredOnProcessing;
	}

	public boolean isIsErroredOnProcessing(){
		return isErroredOnProcessing;
	}

	public void setParsedResults(List<ParsedResultsItem> parsedResults){
		this.parsedResults = parsedResults;
	}

	public List<ParsedResultsItem> getParsedResults(){
		return parsedResults;
	}

	public void setProcessingTimeInMilliseconds(String processingTimeInMilliseconds){
		this.processingTimeInMilliseconds = processingTimeInMilliseconds;
	}

	public String getProcessingTimeInMilliseconds(){
		return processingTimeInMilliseconds;
	}

	public void setSearchablePDFURL(String searchablePDFURL){
		this.searchablePDFURL = searchablePDFURL;
	}

	public String getSearchablePDFURL(){
		return searchablePDFURL;
	}

	public void setOCRExitCode(int oCRExitCode){
		this.oCRExitCode = oCRExitCode;
	}

	public int getOCRExitCode(){
		return oCRExitCode;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"isErroredOnProcessing = '" + isErroredOnProcessing + '\'' + 
			",parsedResults = '" + parsedResults + '\'' + 
			",processingTimeInMilliseconds = '" + processingTimeInMilliseconds + '\'' + 
			",searchablePDFURL = '" + searchablePDFURL + '\'' + 
			",oCRExitCode = '" + oCRExitCode + '\'' + 
			"}";
		}
}