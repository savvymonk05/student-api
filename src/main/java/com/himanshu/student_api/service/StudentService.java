package com.himanshu.student_api.service;

import com.himanshu.student_api.dto.PaginationResponse;
import com.himanshu.student_api.dto.StudentRequestDTO;
import com.himanshu.student_api.dto.StudentResponseDTO;
import com.himanshu.student_api.exception.StudentNotFoundException;
import com.himanshu.student_api.model.Student;
import com.himanshu.student_api.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return toResponseDTO(student);
    }

    public StudentResponseDTO saveStudent(StudentRequestDTO dto) {

        if (studentRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Student student = toEntity(dto);
        Student saved = studentRepository.save(student);
        return toResponseDTO(saved);
    }

    @Transactional
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {

        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setAge(dto.getAge());

        return toResponseDTO(studentRepository.save(existing));
    }

    private Student toEntity(StudentRequestDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        return student;
    }

    private StudentResponseDTO toResponseDTO(Student student) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setAge(student.getAge());
        return dto;
    }
    public PaginationResponse<StudentResponseDTO> getAllStudents(int page, int size) {

        // 1. Create pagination request
        Pageable pageable = PageRequest.of(page, size);

        // 2. Fetch paginated data from DB
        Page<Student> studentPage = studentRepository.findAll(pageable);

        // 3. Convert entity → DTO
        List<StudentResponseDTO> students = studentPage.getContent()
                .stream()
                .map(this::toResponseDTO)
                .toList();

        // 4. Wrap into response
        return new PaginationResponse<>(
                students,
                studentPage.getNumber(),
                studentPage.getSize(),
                studentPage.getTotalElements(),
                studentPage.getTotalPages(),
                studentPage.isLast()
        );
    }
}