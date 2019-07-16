package com.lianglong.threadpool.demo.bean;

import java.util.Queue;


/**
 * @author lianglong
 * @date 19-7-11
 */
public class MyBlockArrayQueue<T> {
    //队列中的有效数据
    private int count;

    //队列的容量
    private int maxSize;

    //下一个数据 在数组中的下标
    private int offIndex ;

    //数组中 下一个存数据的下标
    private int pushIndex;

    //存数据的数组
    private Object[] objects;

    public MyBlockArrayQueue(int maxSize){
        this.maxSize=maxSize;
        this.objects=new Object[maxSize];
    }


    public boolean add(T o){
        if(count==maxSize){
            return false;
        }
        objects[pushIndex]=o;
        count++;

        pushIndex++;
        if(pushIndex==maxSize){
            pushIndex=0;
        }
        return true;
    }

    public T get(){
        if(count==0){
            return null;
        }
        T t=(T)objects[offIndex];
        offIndex++;
        if(offIndex==maxSize){

            offIndex=0;
        }
        count--;
        return t;
    }

    public static void main(String[] args) {
       MyBlockArrayQueue queue= new MyBlockArrayQueue<Integer>(5);

       queue.add(1);
       queue.add(2);
       queue.add(3);
       queue.add(4);
       queue.add(5);
        try {
            System.out.println(queue.add(6));

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.get());

        for (int i = 6; i < 11; i++) {
           queue.add(i);

        }
        for (int i = 6; i < 11; i++) {
            System.out.println(queue.get());

        }

        for (int i = 0; i < 20; i++) {
            queue.add(i);
            System.out.println(queue.get());

        }
        System.out.println(queue.get());
    }

}
