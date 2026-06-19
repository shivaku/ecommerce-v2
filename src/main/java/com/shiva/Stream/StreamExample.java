package com.shiva.Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(3,6,7,7,9));
        List<Integer> data =list
                            .stream()
                            .distinct()
                            .sorted(Comparator.reverseOrder())
                            .collect(Collectors.toList());
        System.out.println(data);
    }
}
