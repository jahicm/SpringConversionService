package org.spring.conversion.configuration;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.spring.conversion.DateToStringConverter;
import org.spring.conversion.EmployeeFT;
import org.spring.conversion.EmployeeFTToEmployeePTConverter;
import org.spring.conversion.StringToDateTimeConverter;
import org.spring.conversion.UtilToJoda;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.converter.Converter;

@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan(basePackages = "org.spring.conversion")
public class AppConfig {

	@Value("${date.format.pattern}")
	private String dateFormatPattern;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public EmployeeFT employeeFT(@Value("${emaployee.firstName}") String firstName,
			@Value("${emaployee.lastName}") String lastName, @Value("${emaployee.personalSite}") URL personalSite,
			@Value("${emaployee.birthDate}") DateTime birthDate) throws Exception {
		EmployeeFT employeeFT = new EmployeeFT();
		employeeFT.setFirstName(firstName);
		employeeFT.setLastName(lastName);
		employeeFT.setPersonalSite(personalSite);
		employeeFT.setBirthDate(birthDate);
		return employeeFT;
	}

	@Bean
	public ConversionServiceFactoryBean conversionService() {
		ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
		@SuppressWarnings("rawtypes")
		Set<Converter> convs = new HashSet<Converter>();
		convs.add(converter());
		convs.add(converterToString());
		convs.add(convertToEmployeePT());
		convs.add(convertToJoda());
		conversionServiceFactoryBean.setConverters(convs);
		conversionServiceFactoryBean.afterPropertiesSet();
		return conversionServiceFactoryBean;
	}

	@Bean
	StringToDateTimeConverter converter() {
		StringToDateTimeConverter conv = new StringToDateTimeConverter();
		conv.setDatePattern(dateFormatPattern);
		conv.init();
		return conv;
	}
/* Converter from Joda DateTime to String */
	@Bean
	DateToStringConverter converterToString() {
		DateToStringConverter conv = new DateToStringConverter();
		return conv;
	}
	/* Converter from EmployeeFT to EmployeePT */
	@Bean
	EmployeeFTToEmployeePTConverter convertToEmployeePT() {
		return new EmployeeFTToEmployeePTConverter();
	}
	/* Converter from java.util.Date to Joda DateTime */
	@Bean
	UtilToJoda convertToJoda() {
		return new UtilToJoda();
	}
}
