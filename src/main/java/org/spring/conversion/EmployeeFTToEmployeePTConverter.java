package org.spring.conversion;

import org.springframework.core.convert.converter.Converter;

public class EmployeeFTToEmployeePTConverter
        implements Converter<EmployeeFT, EmployeePT> {

    @Override
    public EmployeePT convert(EmployeeFT employeeFT) {
        EmployeePT employeePT = new EmployeePT();
        employeePT.setFullName(employeeFT.getFirstName()+" "+employeeFT.getLastName());
        employeePT.setBirthDate(employeeFT.getBirthDate());
        employeePT.setPersonalSite(employeeFT.getPersonalSite());

        return employeePT;
    }
}
