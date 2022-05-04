package com.example.calculator;

import com.example.calculator.bean.Calculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
		String input=args[0];
		System.out.println(Calculator.add(input));
	}

}
