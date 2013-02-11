package com.sanitaryresearch.pojo;

import java.util.Date;

public class ItemHolder {
	
	double listId;
	double itemId;
	String description;
    Date created;
    Date target;
    Date completed;
    
    public double getListId() {
        return listId;
    }
 
    public void setListId(double listId) {
        this.listId = listId;
    }
    
    public double getItemId() {
        return itemId;
    }
 
    public void setItemId(double itemId) {
        this.itemId = itemId;
    }
    
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
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
 
    public void setAll(double listId, double itemId, 
    		String description, 
    		Date created, Date target, Date completed) {
        
    		setListId(listId);
    		setItemId(itemId);
        setDescription(description);
        setCreated(created);
        setTarget(target);
        setCompleted(completed);
    }

 // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
      return description;
    }
}
