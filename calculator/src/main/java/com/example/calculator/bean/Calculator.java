package com.example.calculator.bean;

import ch.lambdaj.function.convert.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static ch.lambdaj.Lambda.convert;
import static ch.lambdaj.Lambda.join;

@Component("calculatorDemo")
public class Calculator {
    public static int add(String text){
        List<Integer> numbers=parseNumbers(text);
        ensureAllNonNegatives(numbers);
        return numbers.stream().mapToInt(i -> i).sum();
    }

    private static void ensureAllNonNegatives(List<Integer> numbers) {
        List<Integer> negatives = numbers.stream().filter(number -> number <0).collect(Collectors.toList());
        if(negatives.size() > 0){
            throw  new RuntimeException("Negatives not allowed: "+negatives.toString().replace("[", "").replace("]",""));
        }
    }

    private static List<Integer> parseNumbers(String text) {
        String[] tokens=tokenize(text);
        List<Integer> numbers = convert(tokens, toInt());
        return numbers;
    }

    private static String[] tokenize(String text) {
        if(text.isEmpty()){
            return new String[0];
        }else if(usesCustomDelimiterSyntax(text)){
            return splitUsingCustomDelimiterSyntax(text,true );
        }else if(48 > text.charAt(0) || text.charAt(0) > 57){
            return splitUsingCustomDelimiterSyntax(text,false );
        }
        else {
            return splitUsingNewLinesAndCommas(text);
        }
    }

    private static boolean usesCustomDelimiterSyntax(String text) {
        return text.startsWith("//");
    }

    private static String[] splitUsingCustomDelimiterSyntax(String text, boolean providedCustomDelimeter) {
        if(providedCustomDelimeter) {
            Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(text);
            matcher.matches();
            String customDelimiter = matcher.group(1);
            String numbers = matcher.group(2);
            return numbers.split(Pattern.quote(customDelimiter));
        }
        Matcher matcher = Pattern.compile("(.)\n(.*)").matcher(text);
        matcher.matches();
        String customDelimiter = matcher.group(1);
        String numbers = matcher.group(2);
        return numbers.split(Pattern.quote(customDelimiter));
    }

    private static String[] splitUsingNewLinesAndCommas(String text) {
        String tokens[]=text.split(",|\n");
        return tokens;
    }

    private static Converter<String, Integer> toInt(){
        return new Converter<String,Integer>() {
            public Integer convert(String s) {
                return toInt(s);
            }
        };
    }

    private static int toInt(String text) {
        return Integer.parseInt(text);
    }
}
