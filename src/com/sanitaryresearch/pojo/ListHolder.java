package com.sanitaryresearch.pojo;

import java.util.Date;

public class ListHolder {
	
	double listId;
	String name;
	String type;
    int typeImageId;
    Date created;
    Date target;
    Date completed;
    
    public double getListId() {
        return listId;
    }
 
    public void setListId(double listId) {
        this.listId = listId;
    }
    
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
    public int getTypeImageId() {
        return typeImageId;
    }
 
    public void setTypeImageId(int typeImageId) {
        this.typeImageId = typeImageId;
    }
    
    public Date getCreated() {
        return created;
    }
 
    public void setCreated(Date created) {
        this.created = created;
    }
    
    public Date getTarget() {
        return target;
    }
 
    public void setTarget(Date target) {
        this.target = target;
    }
    
    public Date getCompleted() {
        return completed;
    }
 
    public void setCompleted(Date completed) {
        this.completed = completed;
    }
 
    public void setAll(double listId, 
    		String name, String type, int listImageId, 
    		Date created, Date target, Date completed) {
        
    		setListId(listId);
        setName(name);
        setType(type);
        setTypeImageId(typeImageId);
        setCreated(created);
        setTarget(target);
        setCompleted(completed);
    }

 // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
      return name;
    }
}
