// package com.controller;

// import com.services.JobService;
// import com.jobUser.JobUser;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/forms")
// public class JobController {

//     @Autowired
//     private JobUser studentFormService;

//     @PostMapping("/submit")
//     public JobUser submitForm(@RequestBody JobUser form) {
//         return studentFormService.saveForm(form);
//     }

//     @GetMapping("/all")
//     public List<JobUser> getAllForms() {
//         return studentFormService.getAllForms();
//     }

//     @GetMapping("/{id}")
//     public Optional<JobUser> getFormById(@PathVariable Long id) {
//         return studentFormService.getFormById(id);
//     }
// }
