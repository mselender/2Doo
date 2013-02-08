package com.sanitaryresearch.twodoo.view;

public class ListTypeHolder {
 
    String listTypeTitle;
    int listTypeImageId;
 
    public String getTitle() {
        return listTypeTitle;
    }
 
    public void setTitle(String listTypeTitle) {
        this.listTypeTitle = listTypeTitle;
    }
 
    public int getImageId() {
        return listTypeImageId;
    }
 
    public void setImageId(int listTypeImageId) {
        this.listTypeImageId = listTypeImageId;
    }
 
    public void setAll(int listImageId, String listTypeTitle) {
        setImageId(listTypeImageId);
        setTitle(listTypeTitle);
    }
}