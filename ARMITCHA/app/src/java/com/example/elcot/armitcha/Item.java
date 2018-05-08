package com.example.elcot.armitcha;

public class Item {

    private String Title;
    private  String Cateogry;
    private String Description;
    private int Thumbnail;

    public Item()
    {

    }
    public Item(String title,String cateogry,String description,int thumbnail)
    {
        Title=title;
        Cateogry=cateogry;
        Description=description;
        Thumbnail=thumbnail;
    }


    public String getTitle()
    {
        return Title;
    }
    public String getCateogry()
    {
        return Cateogry;
    }
    public String getDescription()
    {
        return Description;
    }
    public int getThumbnail()
    {
        return Thumbnail;
    }


    public void setTitle(String title)
    {
        Title=title;
    }
    public void setCateogry(String cateogry)
    {
        Cateogry=cateogry;
    }
    public void setDescription(String description)
    {
        Description=description;
    }
    public void setThumbnail(int thumbnail)
    {
        Thumbnail=thumbnail;
    }

}
