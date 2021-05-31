package org.spring.conversion;

import java.net.URL;
import java.text.SimpleDateFormat;
import org.joda.time.DateTime;
import javax.validation.constraints.NotNull;

public class EmployeePT {
	@NotNull
	private String fullName;
	private DateTime birthDate;
	private URL personalSite;

	public DateTime getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(DateTime birthDate) {
		this.birthDate = birthDate;
	}

	public URL getPersonalSite() {
		return personalSite;
	}

	public void setPersonalSite(URL personalSite) {
		this.personalSite = personalSite;
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		return String.format("{Full name: %s, Birthday: %s, Site: %s}", fullName, sdf.format(birthDate.toDate()),
				personalSite);
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
