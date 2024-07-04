package com.etd.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class DocumentURLValidation implements ConstraintValidator<ValidateDocument, String> {

	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		try {
			URL url = new URL(s);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("HEAD");
			int contentLength = con.getContentLength();

			if ("application/pdf".equals(con.getContentType()) && contentLength <= 256000) {
				return true;
			}
		} catch (IOException e) {
			log.error("IOException occurred!!");
		}
		return false;
	}
}
