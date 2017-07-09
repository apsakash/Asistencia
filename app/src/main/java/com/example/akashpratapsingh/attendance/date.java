package com.example.akashpratapsingh.attendance;

/**
 * Created by Akash Pratap Singh on 20-Feb-17.
 */

public class date {
    String subject;
    int bunked;
    date(String s2,int bunk)
    {
        setSubject(s2);
        setbunked(bunk);
    }
    public int getbunked()
    {
        return bunked;
    }
    public void setbunked(int bunk)
    {
        bunked=bunk;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


}
