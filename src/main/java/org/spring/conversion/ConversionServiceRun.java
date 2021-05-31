/**
 * Spring Framework conversion of different data types and different java objects 
 * using inbuilt Spring ConversionService interface.
 */
package org.spring.conversion;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.spring.conversion.configuration.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.ConversionService;

public class ConversionServiceRun {

	public static void main(String... args) {

		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		EmployeeFT employeeFT = ctx.getBean("employeeFT", EmployeeFT.class);
		System.out.println("EmployeeFT info: " + employeeFT);

		ConversionService conversionService = ctx.getBean(ConversionService.class);

		/* Convert from String to Joda DateTime */
		DateTime strToDate = conversionService.convert("03.05.1974", DateTime.class);
		System.out.println("Birth Date: " + strToDate);

		/* Convert from Joda DateTime to String */
		String str = conversionService.convert(employeeFT.getBirthDate(), String.class);
		System.out.println("Birth Date in string : " + str);

		/* Convert from EmplyeeFT object to EmployeePT */
		EmployeePT employeePT = conversionService.convert(employeeFT, EmployeePT.class);
		System.out.println("EmployeePT : " + employeePT.toString());

		/* Convert from Joda DateTime to java.util.Date */
		Date date = conversionService.convert(employeeFT.getBirthDate(), Date.class);
		String pattern = "mm-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		System.out.println("Util date " + simpleDateFormat.format(date));

		/* Convert from java.util.Date to Joda Datetime */
		DateTime jodaTime = conversionService.convert(date, DateTime.class);
		System.out.println(jodaTime);

		ctx.close();
	}
}
