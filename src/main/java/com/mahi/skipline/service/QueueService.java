/*package com.mahi.skipline.service;


import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahi.skipline.entity.QueueEntry;
import com.mahi.skipline.repository.QueueEntryRepository;


@Service
public class QueueService {


@Autowired
QueueEntryRepository repo;



private int token = 100;



public QueueEntry joinQueue(QueueEntry entry){


entry.setTokenNumber("C"+token);



List<QueueEntry> active =
repo.findByQueue_IdAndStatus(
entry.getQueue().getId(),
"WAITING"
);



List<QueueEntry> serving =
repo.findByQueue_IdAndStatus(
entry.getQueue().getId(),
"SERVING"
);



// first person
if(active.isEmpty() && serving.isEmpty()){

entry.setStatus("SERVING");

entry.setPosition(1);

}

else{

entry.setStatus("WAITING");


entry.setPosition(
repo.findByQueue_Id(entry.getQueue().getId())
.size()+1
);


}



String time =
LocalTime.now()
.format(
DateTimeFormatter.ofPattern("hh:mm a")
);


entry.setJoinedTime(time);



token++;


return repo.save(entry);


}





public List<QueueEntry> getQueue(Long id){


return repo.findByQueue_Id(id);


}







public void leaveQueue(Long id){


QueueEntry q =
repo.findById(id).get();


q.setStatus("LEFT");


repo.save(q);


updatePositions(q.getQueue().getId());


}







public void skip(Long id,String reason){


QueueEntry q =
repo.findById(id).get();



q.setStatus("SKIPPED");


q.setReason(reason);



repo.save(q);



makeNextServing(q.getQueue().getId());



}








public void serve(Long id){


// current serving completed

QueueEntry q =
repo.findById(id).get();



q.setStatus("COMPLETED");



repo.save(q);



makeNextServing(
q.getQueue().getId()
);


}








private void makeNextServing(Long queueId){



List<QueueEntry> serving =
repo.findByQueue_IdAndStatus(
queueId,
"SERVING"
);



if(serving.isEmpty()){



List<QueueEntry> waiting =
repo.findByQueue_IdAndStatus(
queueId,
"WAITING"
);



if(!waiting.isEmpty()){


QueueEntry next =
waiting.get(0);



next.setStatus("SERVING");


repo.save(next);


}


}



updatePositions(queueId);


}








private void updatePositions(Long queueId){



List<QueueEntry> list =
repo.findByQueue_IdAndStatus(
queueId,
"WAITING"
);



int pos=1;



for(QueueEntry q:list){


q.setPosition(pos++);


repo.save(q);


}


}





public int getWaitingCount(Long queueId){


return repo.findByQueue_IdAndStatus(
queueId,
"WAITING"
).size();


}



}*/



package com.mahi.skipline.service;

import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahi.skipline.entity.QueueEntry;
import com.mahi.skipline.repository.QueueEntryRepository;

@Service
public class QueueService {

    @Autowired
    QueueEntryRepository repo;

    private int token = 100;

    public QueueEntry joinQueue(QueueEntry entry){

        Long userId =
        entry.getUser().getId();

        Long queueId =
        entry.getQueue().getId();

        // PREVENT DUPLICATE JOIN

        List<QueueEntry> existing =
        repo.findByUser_IdAndQueue_IdAndStatusIn(
            userId,
            queueId,
            List.of("WAITING","SERVING")
        );

        if(!existing.isEmpty()){

            throw new RuntimeException(
                "You already joined this queue"
            );

        }

        entry.setTokenNumber("C"+token);

        List<QueueEntry> active =
        repo.findByQueue_IdAndStatus(
            queueId,
            "WAITING"
        );

        List<QueueEntry> serving =
        repo.findByQueue_IdAndStatus(
            queueId,
            "SERVING"
        );

        // FIRST PERSON

        if(active.isEmpty() && serving.isEmpty()){

            entry.setStatus("SERVING");

            entry.setPosition(1);

        }
        else{

            entry.setStatus("WAITING");

            entry.setPosition(
                active.size()
                +
                serving.size()
                +
                1
            );

        }

        String time =
        LocalTime.now()
        .format(
            DateTimeFormatter.ofPattern("hh:mm a")
        );

        entry.setJoinedTime(time);

        token++;

        return repo.save(entry);

    }

    public List<QueueEntry> getQueue(Long id){

        return repo.findByQueue_Id(id);

    }

    public void leaveQueue(Long id){

        QueueEntry q =
        repo.findById(id).get();

        q.setStatus("LEFT");

        repo.save(q);

        makeNextServing(
            q.getQueue().getId()
        );

    }

    public void skip(Long id,String reason){

        QueueEntry q =
        repo.findById(id).get();

        q.setStatus("SKIPPED");

        q.setReason(reason);

        repo.save(q);

        makeNextServing(
            q.getQueue().getId()
        );

    }

    public void serve(Long id){

        QueueEntry q =
        repo.findById(id).get();

        q.setStatus("COMPLETED");

        repo.save(q);

        makeNextServing(
            q.getQueue().getId()
        );

    }

    private void makeNextServing(Long queueId){

        List<QueueEntry> serving =
        repo.findByQueue_IdAndStatus(
            queueId,
            "SERVING"
        );

        if(serving.isEmpty()){

            List<QueueEntry> waiting =
            repo.findByQueue_IdAndStatus(
                queueId,
                "WAITING"
            );

            if(!waiting.isEmpty()){

                QueueEntry next =
                waiting.get(0);

                next.setStatus("SERVING");

                repo.save(next);

            }

        }

        updatePositions(queueId);

    }

    private void updatePositions(Long queueId){

        List<QueueEntry> list =
        repo.findByQueue_IdAndStatus(
            queueId,
            "WAITING"
        );

        int pos = 1;

        for(QueueEntry q : list){

            q.setPosition(pos++);

            repo.save(q);

        }

    }

    public int getWaitingCount(Long queueId){

        return repo.findByQueue_IdAndStatus(
            queueId,
            "WAITING"
        ).size();

    }

}