package com.lianglong;


import java.util.Random;


/**
 * @author lianglong
 * @date 19-7-15
 */

public class Bubble {

    public static void main(String[] args) {


    }


    /**
     * 冒泡排序
     *
     * @param ints
     * @return
     */
    public static int[] bubble(int[] ints) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < ints.length - 1; i++) {
            //第 i+1 趟 找出最大数到数组最后  总共需要  数组长度-1趟

            //从i+1个数开始，i为0时 两两比较到 ints[ints.length-2] ints[ints.length-1]
            //              i为1时  两两比较到  ints.length-2-i  ints.length-1-i
            for (int j = i; j < ints.length - i - 1; j++) {
                //比较相邻两个 前边的大就交换
                if (ints[j] > ints[j + 1]) {
                    int temp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = temp;
                }
            }

        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
        return ints;
    }

    /**
     * 选择排序
     *
     * @param ints
     * @return
     */
    public static int[] select(int[] ints) {
        //每次找出  未排序最小值  把它交换到 排好序的右边
        //用来记录每次找出的最小值的下标 先假定第一个数就是最小的
        long start = System.currentTimeMillis();
        int minIndex;
        for (int i = 0; i < ints.length; i++) {
            //让这个数和后边的所有数进行一次比较 同时找出最小的数的下标
            minIndex = i;
            for (int j = i + 1; j < ints.length; j++) {
                if (ints[minIndex] > ints[j]) {
                    minIndex = j;
                }
            }
            //把下标 i的数 与 下标minIndex的数交换
            if (minIndex == i) {
                //这种情况不需要交换
                continue;
                //进行下一最小数查找
            }
            //把最小数换到  当前i处
            int temp = ints[i];
            ints[i] = ints[minIndex];
            ints[minIndex] = temp;

        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return ints;
    }


    public static int[] quick(int left, int right, int[] ints) {

        if (left >= right) {
            return ints;
        }

        //左侧第一个数为参照
        int ref = ints[left];
        //记录下左界限下标
        int index = left;

        //从下一个数开始遍历
        for (int i = index + 1; i < right; i++) {
            if (ints[i] < ref) {
                //left右移一位
                left++;
                //交换数据 不是同一值才交换
                if (ints[left] != ints[i]) {
                    int temp = ints[left];
                    ints[left] = ints[i];
                    ints[i] = temp;
                }

            }
        }
        //把参照的数放在中间
        ints[index] = ints[left];
        ints[left] = ref;
        //然后递归调用

        quick(index, left, ints);

        quick(left + 1, right, ints);

        return ints;

    }

    /**
     * 插入排序
     */
    public static int[] insert(int[] ins) {

        long start = System.currentTimeMillis();

        l:
        for (int i = 1; i < ins.length; i++) {
            int temp = ins[i];//保存每次需要插入的那个数
            int j;
            for (j = i; j > 0; j--) {//从后往前遍历  有序部分
                if (ins[j - 1] <= temp) {
                    //不需要处理  跳出本次需要处理的temp 继续执行之后待处理项
                    //移动完成后  将temp插入空出来的 数组位置
                    if (j != i) {
                        ins[j] = temp;
                    }

                    continue l;
                }
                //需要处理 假定第一次就需要处理  把最后一个数向后移一位  temp已经保存该数 不怕丢失
                //假如第一次都不需要处理 那么之后的肯定也不需要处理了因为左边的肯定比 temp小
                ins[j] = ins[j - 1];
            }

        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);
        return ins;

    }

    /*
      归并排序 二路归并
     */

    public static int[] merge(int[] a,int low,int high){
        int mid = (low+high)/2;
        if(low<high){
            merge(a,low,mid);
            merge(a,mid+1,high);
            //左右归并
            toMerge(a,low,mid,high);
        }
        return a;
    }

    public static void toMerge(int[] ints, int low, int mid, int high) {
        int[] temp = new int[high-low+1];
        int i= low;
        int j = mid+1;
        int k=0;
        // 把较小的数先移到新数组中
        while(i<=mid && j<=high){
            if(ints[i]<ints[j]){
                temp[k++] = ints[i++];
            }else{
                temp[k++] = ints[j++];
            }
        }
        // 把左边剩余的数移入数组
        while(i<=mid){
            temp[k++] = ints[i++];
        }
        // 把右边边剩余的数移入数组
        while(j<=high){
            temp[k++] = ints[j++];
        }
        // 把新数组中的数覆盖nums数组
        for(int x=0;x<temp.length;x++){
            ints[x+low] = temp[x];
        }
    }


}
