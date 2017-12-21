package de.addrinks.backend.error;

public class HTTPError {
	 
    private String errorMessage;
 
    public HTTPError(String errorMessage){
        this.errorMessage = errorMessage;
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }
}