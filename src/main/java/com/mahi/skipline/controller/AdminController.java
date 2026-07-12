package com.mahi.skipline.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.mahi.skipline.service.QueueService;



@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {


@Autowired
QueueService service;



@PutMapping("/serving/{id}")
public String serving(
@PathVariable Long id
){

service.serve(id);

return "Now Serving";

}




@PutMapping("/skip/{id}")
public String skip(
@PathVariable Long id,
@RequestParam String reason){


service.skip(id,reason);


return "Skipped";

}




}