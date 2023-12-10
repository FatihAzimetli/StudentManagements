package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementsApplication.class, args);
	}

}

/*Tablolar db olusturuldu simdi pom,eml gidip iki adet kütüphane ekliyecegiz
*<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt</artifactId>
			<version>0.9.1</version>
		</dependency> */
