/*package com.mahi.skipline.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mahi.skipline.entity.User;
import com.mahi.skipline.repository.UserRepository;



@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {


@Autowired
UserRepository repo;



@PostMapping("/register")
public User register(@RequestBody User user){


return repo.save(user);

}


@PostMapping("/login")
public User login(@RequestBody User user){


return repo.findByEmail(user.getEmail());

}



}*/

package com.mahi.skipline.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mahi.skipline.entity.User;
import com.mahi.skipline.repository.UserRepository;



@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {


@Autowired
UserRepository repo;




@PostMapping("/register")
public User register(
@RequestBody User user
){

return repo.save(user);

}





@PostMapping("/login")
public User login(
@RequestBody User user
){


return repo.findByEmail(
user.getEmail()
);


}





@GetMapping("/{id}")
public User getUser(
@PathVariable Long id
){


return repo.findById(id).get();


}



}