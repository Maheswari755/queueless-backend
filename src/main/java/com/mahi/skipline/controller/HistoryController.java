package com.mahi.skipline.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mahi.skipline.entity.QueueEntry;
import com.mahi.skipline.repository.QueueEntryRepository;



@RestController
@RequestMapping("/history")
@CrossOrigin
public class HistoryController {



@Autowired
QueueEntryRepository repo;





@GetMapping
public List<QueueEntry> getHistory(){


return repo.findAllByOrderByIdDesc();


}






@DeleteMapping("/{id}")
public String deleteHistory(
@PathVariable Long id
){


repo.deleteById(id);


return "Deleted";


}


}