package com.sanitaryresearch.pojo;

public class ListTypeHolder {
 
	String listTypeKey;
    String listTypeTitle;
    int listTypeImageId;
 
    public String getKey() {
        return listTypeKey;
    }
 
    public void setKey(String listTypeKey) {
        this.listTypeKey = listTypeKey;
    }
    
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
 
    public void setAll(int listImageId, String listTypeTitle, String listTypeKey) {
        setImageId(listTypeImageId);
        setTitle(listTypeTitle);
        setKey(listTypeKey);
    }
}