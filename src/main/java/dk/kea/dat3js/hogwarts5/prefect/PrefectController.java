package dk.kea.dat3js.hogwarts5.prefect;

import dk.kea.dat3js.hogwarts5.students.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prefects")
public class PrefectController {
    private final PrefectService prefectService;

    public PrefectController(PrefectService prefectService) {
        this.prefectService = prefectService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(prefectService.findAll());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> findByPrefectId(@PathVariable int studentId) {
        return ResponseEntity.ok(prefectService.findByPrefectId(studentId));
    }

    @GetMapping("/house/{houseName}")
    public ResponseEntity<List<Student>> findAllPrefectByHouseName(@PathVariable String houseName) {
        return ResponseEntity.ok(prefectService.findAllPrefectByHouseName(houseName));
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student) {
        return ResponseEntity.ok(prefectService.update(student));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Student> delete(@PathVariable int studentId) {
        return ResponseEntity.ok(prefectService.delete(studentId));
    }
}
