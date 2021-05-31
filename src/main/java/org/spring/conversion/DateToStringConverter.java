package org.spring.conversion;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

public class DateToStringConverter implements Converter<DateTime, String> {

	private static final String DEFAULT_DATE_PATTERN = "dd.mm.yyyy";
	private DateTimeFormatter dateFormat;

	@Override
	public String convert(DateTime dateString) {
		return dateFormat.print(dateString);
	}

	@PostConstruct
	public void init() {
		dateFormat = DateTimeFormat.forPattern(DEFAULT_DATE_PATTERN);
	}

	@Bean
	DateToStringConverter converter() {
		DateToStringConverter conv = new DateToStringConverter();
		return conv;
	}
}
