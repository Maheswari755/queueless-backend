package com.mahi.skipline.entity;


import jakarta.persistence.*;


@Entity
@Table(name="queues")
public class Queue {



@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;



private String queueName;


private String location;


private String openTime;


private String closeTime;


private String description;


private String status;




public Long getId(){
return id;
}



public String getQueueName(){
return queueName;
}



public void setQueueName(String queueName){
this.queueName=queueName;
}



public String getLocation(){
return location;
}



public void setLocation(String location){
this.location=location;
}



public String getOpenTime(){
return openTime;
}



public void setOpenTime(String openTime){
this.openTime=openTime;
}



public String getCloseTime(){
return closeTime;
}



public void setCloseTime(String closeTime){
this.closeTime=closeTime;
}



public String getDescription(){
return description;
}



public void setDescription(String description){
this.description=description;
}



public String getStatus(){
return status;
}



public void setStatus(String status){
this.status=status;
}

}