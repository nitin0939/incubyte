package com.example.calculator.bean;

import ch.lambdaj.function.convert.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

import static ch.lambdaj.Lambda.convert;

@Component("calculatorDemo")
public class Calculator {
    public static int add(String text){
        List<Integer> numbers=parseNumbers(text);
        return numbers.stream().mapToInt(i -> i).sum();
    }

    private static List<Integer> parseNumbers(String text) {
        String[] tokens=tokenize(text);
        List<Integer> numbers = convert(tokens, toInt());
        return numbers;
    }

    private static String[] tokenize(String text) {
        if(text.isEmpty()){
            return new String[0];
        }else {
            return splitUsingNewLinesAndCommas(text);
        }
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
