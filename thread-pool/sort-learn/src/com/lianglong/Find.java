package com.lianglong;

import java.util.Arrays;

/**
 * @author lianglong
 * @date 19-7-17
 */
public class Find {


    public static void main(String[] args) {



    }

    /**
     *
     * @param ints
     * @param left
     * @param right
     * @param value
     * @return
     * 前提是数组是有序的
     */
    public static int binarySearch(int[] ints ,int left,int right,int value){



            if(left>right){
                return -1;
            }

            int midIndex= (left+right)/2;

            if(ints[midIndex]==value){
                return midIndex;
            }else if(ints[midIndex]>value){
                //向左查找 使用递归
               return binarySearch(ints,left,midIndex-1,value);
            }else{
                //向右查找
              return  binarySearch(ints,midIndex+1,right,value);
            }
    }


}
