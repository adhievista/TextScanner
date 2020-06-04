package com.vista.textscanner.model.googlebook;

public class Items {
    private VolumeInfo volumeInfo;

    private String id;

    private String selfLink;


    public VolumeInfo getVolumeInfo ()
    {
        return volumeInfo;
    }

    public void setVolumeInfo (VolumeInfo volumeInfo)
    {
        this.volumeInfo = volumeInfo;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }


    public String getSelfLink ()
    {
        return selfLink;
    }

    public void setSelfLink (String selfLink)
    {
        this.selfLink = selfLink;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [volumeInfo = "+volumeInfo+", id = "+id+", selfLink = "+selfLink+"]";
    }
}
