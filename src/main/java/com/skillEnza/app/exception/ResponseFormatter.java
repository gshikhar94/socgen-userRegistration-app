package com.skillEnza.app.exception;

public class ResponseFormatter {

	private boolean isSuccess;
	private String error;

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public ResponseFormatter() {
	}

	/**
	 * @param isSuccess
	 * @param error
	 */
	public ResponseFormatter(boolean isSuccess, String error) {
		super();
		this.isSuccess = isSuccess;
		this.error = error;
	}

}
