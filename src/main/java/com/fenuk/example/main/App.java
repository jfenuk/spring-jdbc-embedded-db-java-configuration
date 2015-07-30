package com.fenuk.example.main;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fenuk.example.config.AppConfig;
import com.fenuk.example.model.Employee;
import com.fenuk.example.repository.EmployeeJdbcRepository;

public class App {

	@Autowired
	EmployeeJdbcRepository eDao;

	public void run() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);
		EmployeeJdbcRepository dao = context.getBean(EmployeeJdbcRepository.class);

		for (int i = 0; i < 10; i++) {

			String name = RandomStringUtils.randomAlphabetic(10);
			long salary = RandomUtils.nextLong(0, 10000);

			dao.save(new Employee(name, salary));
			Employee e = dao.getByName(name);

			System.out.println(i+": "+e);
		}
	}

	public static void main(String[] args) {

		new App().run();

	}
}