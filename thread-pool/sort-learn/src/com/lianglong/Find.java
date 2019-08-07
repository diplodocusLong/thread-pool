package com.lianglong;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author lianglong
 * @date 19-7-17
 */
public class Find {
      /* 吸血鬼数字是指位数为偶数的数字，可以由一堆数字想乘而得到。而这对数字各包含乘积的一半位数的数字，其中从最初的数字中选取的数字可以任意排序。以两个0结尾的数字是不允许的，例如，下列的数字都是“吸血鬼”数字：

        1260=21*60

        1827=21*87

        2187=27*81

        写出一个程序，找出4位数的所有吸血鬼数字
      */

    public static void main(String[] args) {
        int c=0;
        for (int i = 10; i <= 99; i++) {
            for (int j = i + 1; j <= 99; j++) {
                // 计算出所有两位数的积
                int sum = i * j;
                c++;
                if (sum <= 9999 && sum >= 1000) {
                    String[] t1 = (sum + "").split("");
                    // 对数组t1进行升序排列
                    Arrays.sort(t1);
                    // 这个地方，把i和j都当String型的字符串加起来组成一个四位数
                    String[] t2 = ("" + i + j).split("");
                    // 对t2进行升序排列
                    Arrays.sort(t2);
                    //下面判断是通过两个已经排好序的数组相比较，当完全相同时执行
                    if (Arrays.equals(t1, t2)) {
                        System.out.println(i + "*" + j + "=" + i * j);
                    }
                }
            }
        }
        System.out.println(c);
    }


    /**
     * @param ints
     * @param left
     * @param right
     * @param value
     * @return 前提是数组是有序的
     */
    public static int binarySearch(int[] ints, int left, int right, int value) {

        if (left > right) {
            return -1;
        }

        int midIndex = (left + right) / 2;

        if (ints[midIndex] == value) {
            return midIndex;
        } else if (ints[midIndex] > value) {
            //向左查找 使用递归
            return binarySearch(ints, left, midIndex - 1, value);
        } else {
            //向右查找
            return binarySearch(ints, midIndex + 1, right, value);
        }
    }


}
