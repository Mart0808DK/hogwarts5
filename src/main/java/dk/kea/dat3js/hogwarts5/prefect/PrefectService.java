package dk.kea.dat3js.hogwarts5.prefect;

import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrefectService {
    private final StudentRepository studentRepository;

    public PrefectService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAllByPrefectIsTrue();
    }

    public Student update(Student student) {
        // Check if student is old enough to be a prefect
        checkForSchoolYearRequirements(student);

        checkForAmountOfPrefects();

        checkGenderInSameHouse(student, student.getId());

        student.setPrefect(true);
        return studentRepository.save(student);
    }

    public static void checkForSchoolYearRequirements(Student student) {
        if (student.getSchoolYear() < 5) {
            throw new IllegalArgumentException("Student is not old enough to be a prefect");
        }
    }

    public void checkForAmountOfPrefects() {
        List<Student> allPrefects = studentRepository.findAllByPrefectIsTrue();
        if (allPrefects.size() > 2) {
            throw new IllegalArgumentException("There can only be two prefects");
        }
    }

    public void checkGenderInSameHouse(Student student, int studentId) {
        List<Student> prefectsInSameHouse = studentRepository.findAllByPrefectIsTrueAndHouse_Name(student.getHouse().getName());
        for (Student prefect : prefectsInSameHouse) {
            if (prefect.getGender() == student.getGender() && prefect.getId() != studentId) {
                throw new IllegalArgumentException("There can only be male and female prefects in a house");
            }
        }
    }

    public List<Student> findAllPrefectByHouseName(String houseName) {
        return studentRepository.findAllByPrefectIsTrueAndHouse_Name(houseName);
    }

    public Student findByPrefectId(int id) {
        var student = studentRepository.findById(id).orElseThrow();

        if (student.getPrefect()) {
            return student;
        } else {
            throw new IllegalArgumentException("Student is not a prefect");
        }
    }

    public Student delete(int id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (student.getPrefect()) {
                studentRepository.delete(student);
                return student;
            } else {
                throw new IllegalArgumentException("Student with id " + id + " is not a prefect");
            }
        } else {
            throw new IllegalArgumentException("No student found with id " + id);
        }
    }
}
