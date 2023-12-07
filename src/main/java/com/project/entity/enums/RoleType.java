package com.project.entity.enums;

public enum RoleType {

    ADMIN("Admin"),

    TEACHER("Teacher"),

    STUDENT("Student"),

    MANAGER("Dean"),

    ASSISTANT_MANAGER("ViceDean"); //338

    public final String name;//339


    RoleType(String name) {
        this.name = name;
    }//340 construtcer


    public String getName() {
        return name;
    }//341


} //337

/* 339-340-341- MANAGER("Dean") cagirirken dean diye cagiracagiz dekan anlaminda ASSISTANT_MANAGER("ViceDean") dekan yardimcisi
* bu String ifadelere ulasabilmek icin String bir ifade olusturuyoruz setlene bilmesi icin final queword koyduk
* ve parametreli contricter olusturuyoruz  name üzerinden string ifadeyi olusturduk
* ve getter olusturduk*/

/* 342 Usrlarla ile enum typeler arasindaki ilskiyi kumak icin contrite bir class olusturuyoruz user pakeg icinde UserRole class i*/