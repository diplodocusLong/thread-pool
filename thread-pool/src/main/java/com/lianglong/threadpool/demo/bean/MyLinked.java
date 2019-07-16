package com.lianglong.threadpool.demo.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;

/**
 * @author lianglong
 * @date 19-7-12
 */

@Data
@Accessors(chain = true)
public class MyLinked {
    //头节点
    private Hero head;

    //当前信息Date
    private Hero date;


    public MyLinked() {
        this.head = new Hero();
    }

    public void add(Hero e) {
        Hero temp = head;

        while (temp.getNext() != null) {

            temp = temp.getNext();
        }
        temp.setNext(e);
    }

    public void list(){

        while (head.getNext() != null) {
            head = head.getNext();
            System.out.println(head.toString());
        }
    }

    public static void main(String[] args) {
       /* MyLinked myLinked = new MyLinked();

        Hero no = (Hero) new Hero().setName("宋江").setNickName("及时雨").setNo(1);
        myLinked.add(no);
        no = (Hero) new Hero().setName("卢俊一").setNickName("玉麒麟").setNo(2);
        myLinked.add(no);
        no = (Hero) new Hero().setName("吴用").setNickName("智多星").setNo(3);
        myLinked.add(no);
        no = (Hero) new Hero().setName("林冲").setNickName("豹子头").setNo(4);
        myLinked.add(no);


        myLinked.list();*/
        TreeSet<Hero> heroes = new TreeSet<>(new Comparator<Hero>() {
            @Override
            public int compare(Hero o1, Hero o2){
                return o1.getNo()-o2.getNo();
            }
        });

        heroes.add(new Hero().setName("宋江").setNickName("及时雨").setNo(1));
        heroes.add(new Hero().setName("卢俊一").setNickName("玉麒麟").setNo(2));
        heroes.add(new Hero().setName("林冲").setNickName("豹子头").setNo(4));
        heroes.add(new Hero().setName("吴用").setNickName("智多星").setNo(3));

        Iterator<Hero> iterator = heroes.iterator();

        while (iterator.hasNext()) {
            Hero next =  iterator.next();
            System.out.println(next);

        }

        Iterator<Hero> heroIterator = heroes.descendingIterator();

        while (heroIterator.hasNext()) {

            Hero next =  heroIterator.next();

            System.out.println(next);

        }



    }
}
