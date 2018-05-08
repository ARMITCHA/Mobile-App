package com.example.elcot.armitcha;

public class userdatabase
{
    String name;
    String email;
    String mob;
    public userdatabase()
    {

    }

    public userdatabase(String name, String email,String mob)
    {
        this.name=name;
        this.email=email;
        this.mob=mob;
    }
    public String getEmail() {
        return email;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
