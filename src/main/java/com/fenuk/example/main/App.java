package com.fenuk.example.main;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fenuk.example.config.AppConfig;
import com.fenuk.example.dao.EmployeeDao;
import com.fenuk.example.model.Employee;

public class App {

	@Autowired
	EmployeeDao eDao;

	public void run() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);
		EmployeeDao dao = context.getBean(EmployeeDao.class);

		for (int i = 0; i < 10; i++) {

			String name = RandomStringUtils.randomAlphabetic(10);
			long salary = RandomUtils.nextLong(0, 10000);

			dao.saveEmployee(new Employee(name, salary));
			Employee e = dao.getEmployeeByName(name);

			System.out.println(i+": "+e);
		}
	}

	public static void main(String[] args) {

		new App().run();

	}
}