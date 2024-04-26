package dk.kea.dat3js.hogwarts5.students;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  // get all students
  @GetMapping
  public List<StudentResponseDTO> getAllStudents() {
    return studentService.findAll();
  }

  // get student by id
  @GetMapping("/{id}")
  public ResponseEntity<StudentResponseDTO> getStudent(@PathVariable int id) {
    return ResponseEntity.of(studentService.findById(id));
  }

  // create post, put, patch, delete methods
  @PostMapping
  public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO student) {
    return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student));
  }

  @PutMapping("/{id}")
  public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable int id, @RequestBody StudentRequestDTO student) {
    return ResponseEntity.of(studentService.updateIfExists(id, student));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<StudentResponseDTO> partialUpdateStudent(@PathVariable int id, @RequestBody StudentRequestDTO student) {
    return ResponseEntity.of(studentService.partialUpdate(id, student));
  }

  @PatchMapping("/{id}/prefect")
  public ResponseEntity<StudentResponseDTO> updateToPrefect(@PathVariable int id) {
    return ResponseEntity.of(Optional.of(studentService.updateToPrefect(id)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<StudentResponseDTO> deleteStudent(@PathVariable int id) {
    return ResponseEntity.of(studentService.deleteById(id));
  }
}
