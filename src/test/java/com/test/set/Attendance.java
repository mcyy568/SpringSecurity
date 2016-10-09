package com.test.set;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * 创建用户：杨辽
 * 创建时间：2016-10-04 20:28:00
 * 描    述：测试set对象集合排序
 */
public class Attendance implements Comparable {

    private Integer attId;

    public Integer getAttId() {
        return this.attId;
    }

    public void setAttId(Integer attId) {
        this.attId = attId;
    }

    public String toString() {
        return " 编号=" + this.attId;
    }

    //实现接口
    public int compareTo(Object o) {
        Attendance attendance = (Attendance) o;
        return this.attId - attendance.attId;
    }

    //测试的main方法
    public static void main(String[] args) {
        Attendance atta = new Attendance();
        atta.setAttId(30);
        Attendance attb = new Attendance();
        attb.setAttId(25);
        Attendance attc = new Attendance();
        attc.setAttId(23);
        Attendance attd = new Attendance();
        attd.setAttId(22);
        Attendance atte = new Attendance();
        atte.setAttId(21);

        Set<Attendance> s = new TreeSet<Attendance>(new ArrayList<Attendance>());
        s.add(atte);
        s.add(atta);
        s.add(attc);
        s.add(attb);
        s.add(attd);

        System.out.println(s);
    }
}


