package com.himanshu.student_api.controller;
import com.himanshu.student_api.dto.StudentRequestDTO;
import com.himanshu.student_api.dto.StudentResponseDTO;
import com.himanshu.student_api.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StudentController {
    private StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/students")
    public List<StudentResponseDTO> getAllStudents(){
        return studentService.getAllStudents();
    }
    @GetMapping("/students/{id}")
    public StudentResponseDTO getStudentById(@PathVariable Long id){
        return  studentService.getStudentById(id);
    }
    @PostMapping("/students")
    public StudentResponseDTO saveStudent( @Valid @RequestBody StudentRequestDTO dto) {
        return studentService.saveStudent(dto);
    }

    @PutMapping("/students/{id}")
    public StudentResponseDTO updateStudent(@PathVariable Long id ,  @Valid @RequestBody StudentRequestDTO dto) {
        return  studentService.updateStudent(id, dto);
    }
    @DeleteMapping("/students/{id}")
    public void  deleteStudentById(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

}
