package com.example.calculator.bean;

import ch.lambdaj.function.convert.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.Lambda.convert;
import static ch.lambdaj.Lambda.join;
import static ch.lambdaj.Lambda.sum;
import static org.hamcrest.Matchers.*;

@Component("calculatorDemo")
public class Calculator {

}
