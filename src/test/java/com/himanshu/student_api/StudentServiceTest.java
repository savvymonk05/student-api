package com.himanshu.student_api;

import com.himanshu.student_api.dto.StudentRequestDTO;
import com.himanshu.student_api.dto.StudentResponseDTO;
import com.himanshu.student_api.exception.StudentNotFoundException;
import com.himanshu.student_api.model.Student;
import com.himanshu.student_api.repository.StudentRepository;
import com.himanshu.student_api.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    @Test
    void getStudentById_shouldReturnStudent_whenExists() {
        Student student = new Student(1L, "John", "john@example.com", 20);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        StudentResponseDTO result = studentService.getStudentById(1L);

        assertEquals("John", result.getName());
        assertEquals("john@example.com", result.getEmail());
        assertEquals(20, result.getAge());
    }

    @Test
    void getStudentById_shouldThrow_whenNotFound() {
        when(studentRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.getStudentById(2L));
    }
}
