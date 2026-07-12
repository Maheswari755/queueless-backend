package com.mahi.skipline.entity;


import jakarta.persistence.*;


@Entity
@Table(name="queue_entries")
public class QueueEntry {



@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;



private String tokenNumber;


private int position;


private String status;


private String joinedTime;


private String reason;



@ManyToOne
private User user;



@ManyToOne
private Queue queue;




public Long getId(){
return id;
}



public String getTokenNumber(){
return tokenNumber;
}


public void setTokenNumber(String tokenNumber){
this.tokenNumber=tokenNumber;
}



public int getPosition(){
return position;
}


public void setPosition(int position){
this.position=position;
}



public String getStatus(){
return status;
}


public void setStatus(String status){
this.status=status;
}



public String getJoinedTime(){
return joinedTime;
}


public void setJoinedTime(String joinedTime){
this.joinedTime=joinedTime;
}



public String getReason(){
return reason;
}


public void setReason(String reason){
this.reason=reason;
}



public User getUser(){
return user;
}


public void setUser(User user){
this.user=user;
}



public Queue getQueue(){
return queue;
}


public void setQueue(Queue queue){
this.queue=queue;
}


}