package org.spring.conversion;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;

public class UtilToJoda implements Converter<Date, DateTime> {

	private static final String DEFAULT_DATE_PATTERN = "yyyy-mm-dd";

	@Override
	public DateTime convert(Date date) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		String dateStr = simpleDateFormat.format(date);

		return DateTime.parse(dateStr);
	}

}
