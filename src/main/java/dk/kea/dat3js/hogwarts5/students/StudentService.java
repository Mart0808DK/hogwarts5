package dk.kea.dat3js.hogwarts5.students;

import dk.kea.dat3js.hogwarts5.house.HouseService;
import dk.kea.dat3js.hogwarts5.prefect.PrefectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
  private final StudentRepository studentRepository;
  private final HouseService houseService;
  private final PrefectService prefectService;

  public StudentService(StudentRepository studentRepository, HouseService houseService, PrefectService prefectService) {
    this.studentRepository = studentRepository;
    this.houseService = houseService;
      this.prefectService = prefectService;
  }

  public List<StudentResponseDTO> findAll() {
    return studentRepository.findAll().stream().map(this::toDTO).toList();
  }

  public Optional<StudentResponseDTO> findById(int id) {
    return studentRepository.findById(id).map(this::toDTO);
  }

  public StudentResponseDTO save(StudentRequestDTO student) {
    return toDTO(studentRepository.save(fromDTO(student)));
  }

  public Optional<StudentResponseDTO> updateIfExists(int id, StudentRequestDTO student) {
    if (studentRepository.existsById(id)) {
      Student existingStudent = fromDTO(student);
      existingStudent.setId(id);
      return Optional.of(toDTO(studentRepository.save(existingStudent)));
    } else {
      // TODO: Throw error?
      return Optional.empty();
    }
  }

  public Optional<StudentResponseDTO> partialUpdate(int id, StudentRequestDTO student) {
    Optional<Student> existingStudent = studentRepository.findById(id);
    if(existingStudent.isPresent()) {
      Student studentToUpdate = existingStudent.get();
      if(student.firstName() != null) {
        studentToUpdate.setFirstName(student.firstName());
      }
      if(student.middleName() != null) {
        studentToUpdate.setMiddleName(student.middleName());
      }
      if(student.lastName() != null) {
        studentToUpdate.setLastName(student.lastName());
      }
      if(student.house() != null) {
        studentToUpdate.setHouse(houseService.findById(student.house()).orElseThrow());
      }
      if(student.schoolYear() != null) {
        studentToUpdate.setSchoolYear(student.schoolYear());
      }
      return Optional.of(toDTO(studentRepository.save(studentToUpdate)));
    } else {
      // TODO: handle error
      return Optional.empty();
    }
  }

  public StudentResponseDTO updateToPrefect(int studentId) {
    Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Student not found"));

    PrefectService.checkForSchoolYearRequirements(student);
    prefectService.checkForAmountOfPrefects();
    prefectService.checkGenderInSameHouse(student, student.getId());

    student.setPrefect(!student.getPrefect());
    student = studentRepository.save(student);
    return toDTO(student);
  }

  public Optional<StudentResponseDTO> deleteById(int id) {
    Optional<StudentResponseDTO> existingStudent = studentRepository.findById(id).map(this::toDTO);
    studentRepository.deleteById(id);
    return existingStudent;
  }

  private StudentResponseDTO toDTO(Student studentEntity) {
    StudentResponseDTO dto = new StudentResponseDTO(
        studentEntity.getId(),
        studentEntity.getFirstName(),
        studentEntity.getMiddleName(),
        studentEntity.getLastName(),
        studentEntity.getFullName(),
        studentEntity.getHouse().getName(),
        studentEntity.getSchoolYear(),
        studentEntity.getGender(),
        studentEntity.getPrefect()
    );

    return dto;
  }

  private Student fromDTO(StudentRequestDTO studentDTO) {
    Student entity = new Student(
        studentDTO.firstName(),
        studentDTO.middleName(),
        studentDTO.lastName(),
        houseService.findById(studentDTO.house()).orElseThrow(),
        studentDTO.schoolYear(),
        studentDTO.gender(),
        studentDTO.prefect()
    );

    if (studentDTO.name() != null) {
      entity.setFullName(studentDTO.name());
    }

    return entity;
  }
}
