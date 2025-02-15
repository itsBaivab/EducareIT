// package com.services;

// import com.jobUser.JobUser;
// import com.repo.jobRepo;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class JobService {

//     @Autowired
//     private jobRepo studentFormRepository;

//     public JobUser saveForm(JobUser form) {
//         return studentFormRepository.save(form);
//     }

//     public List<JobUser> getAllForms() {
//         return studentFormRepository.findAll();
//     }

//     public Optional<JobUser> getFormById(Long id) {
//         return studentFormRepository.findById(id);
//     }
// }
