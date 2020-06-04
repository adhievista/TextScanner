package com.vista.textscanner.model.googlebook;

public class Book
{
    public static final String BookApi = "https://www.googleapis.com/books/v1/";

    private String totalItems;

    private String kind;

    private Items[] items;

    public String getTotalItems ()
    {
        return totalItems;
    }

    public void setTotalItems (String totalItems)
    {
        this.totalItems = totalItems;
    }

    public String getKind ()
    {
        return kind;
    }

    public void setKind (String kind)
    {
        this.kind = kind;
    }

    public Items[] getItems ()
    {
        return items;
    }

    public void setItems (Items[] items)
    {
        this.items = items;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [totalItems = "+totalItems+", kind = "+kind+", items = "+items+"]";
    }
}
