package com.rlogman.guaranteedrate.sorttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class PeopleSortApplication {

	public static void main(String[] args) {
	  SpringApplication.run(PeopleSortApplication.class, args);
	  if (args.length > 0) {
		CLI.run(args);
	  }
	}
}
