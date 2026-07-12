package com.mahi.skipline.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.mahi.skipline.entity.QueueEntry;
import com.mahi.skipline.service.QueueService;



@RestController
@RequestMapping("/queue")
@CrossOrigin
public class QueueController {



@Autowired
QueueService service;




@PostMapping("/join")
public QueueEntry join(
@RequestBody QueueEntry entry){


return service.joinQueue(entry);


}




@GetMapping("/{id}")
public Object view(
@PathVariable Long id){


return service.getQueue(id);

}




@PutMapping("/leave/{id}")
public String leave(
@PathVariable Long id){


service.leaveQueue(id);


return "Left Queue";


}

@PutMapping("/serve/{id}")
public String serve(
@PathVariable Long id){

    service.serve(id);

    return "Completed";
}



@PutMapping("/skip/{id}")
public String skip(
@PathVariable Long id,
@RequestParam String reason){

    service.skip(id,reason);

    return "Skipped";
}


@GetMapping("/count/{queueId}")
public int getCount(
@PathVariable Long queueId
){

    return service.getWaitingCount(queueId);

}
}