package com.lianglong;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author lianglong
 * @date 19-7-19
 */
public class TestTree {


    public static void main(String[] args) {
        TreeSet<Integer> ints = new TreeSet<>();


        List<Integer> integers = Arrays.asList(434, 3, 4, 34, 56, 76, 7, 86, 9);

        ints.addAll(integers);


        String s = ints.toString();

        System.out.println(s);

        Iterator<Integer> iterator = ints.iterator();

        while (iterator.hasNext()) {
            Integer next =  iterator.next();

            if(next==434||next==3){
                iterator.remove();
            }

        }

        System.out.println(ints.toString());

    }


}
