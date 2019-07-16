package com.lianglong.threadpool.demo.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
/**
 * @author lianglong
 * @date 19-7-12
 */
@Data
@EqualsAndHashCode(exclude = "no")
@Accessors(chain = true)
public class Hero {

      private int no;

      private String name;

      private String nickName;

      private Hero next;

    @Override
    public String toString() {
        return "Hero{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

