package dk.kea.dat3js.hogwarts5.prefect;

import dk.kea.dat3js.hogwarts5.house.House;
import dk.kea.dat3js.hogwarts5.students.Gender;
import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrefectServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private PrefectService prefectService;

    @Test
    void findAll() {
        // Arrange
        House gryffindor = new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"});
        Student harry = new Student("Harry", "James", "Potter", gryffindor, 5, Gender.MALE, true);
        Student hermione = new Student("Hermione", null, "Granger", gryffindor, 5, Gender.FEMALE, true);
        Student ron = new Student("Ron", "Bilius", "Weasley", gryffindor, 5, Gender.MALE, false);
        Student ginny = new Student("Ginny", null, "Weasley", gryffindor, 5, Gender.FEMALE, false);

        // Act
        when(studentRepository.findAllByPrefectIsTrue()).thenReturn(Arrays.asList(harry, hermione));

        // Assert
        var result = prefectService.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(harry));
        assertTrue(result.contains(hermione));
        assertFalse(result.contains(ron));
        assertFalse(result.contains(ginny));

    }

    @Test
    void findByPrefectId() {
        // Arrange
        House gryffindor = new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"});
        Student harry = new Student("Harry", "James", "Potter", gryffindor, 5, Gender.MALE, true);
        Student hermione = new Student("Hermione", null, "Granger", gryffindor, 5, Gender.FEMALE, true);
        Student ron = new Student("Ron", "Bilius", "Weasley", gryffindor, 5, Gender.MALE, false);
        Student ginny = new Student("Ginny", null, "Weasley", gryffindor, 5, Gender.FEMALE, false);

        // Act
        when(studentRepository.findById(1)).thenReturn(Optional.of(harry));
        when(studentRepository.findById(2)).thenReturn(Optional.of(hermione));
        when(studentRepository.findById(3)).thenReturn(Optional.of(ron));
        when(studentRepository.findById(4)).thenReturn(Optional.of(ginny));

        // Assert
        assertEquals(harry, prefectService.findByPrefectId(1));
        assertEquals(hermione, prefectService.findByPrefectId(2));
        assertThrows(IllegalArgumentException.class, () -> prefectService.findByPrefectId(3));
        assertThrows(IllegalArgumentException.class, () -> prefectService.findByPrefectId(4));
    }

    @Test
    void findAllPrefectByHouseName() {
        // Arrange
        House gryffindor = new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"});
        Student harry = new Student("Harry", "James", "Potter", gryffindor, 5, Gender.MALE, true);
        Student hermione = new Student("Hermione", null, "Granger", gryffindor, 5, Gender.FEMALE, true);

        // Act
        when(studentRepository.findAllByPrefectIsTrueAndHouse_Name("Gryffindor")).thenReturn(Arrays.asList(harry, hermione));

        // Assert
        var result = prefectService.findAllPrefectByHouseName("Gryffindor");
        assertEquals(2, result.size());
        assertTrue(result.contains(harry));
        assertTrue(result.contains(hermione));

    }

    @Test
    void update() {
        // Arrange
        House gryffindor = new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"});
            Student harry = new Student("Harry", "James", "Potter", gryffindor, 5, Gender.MALE, false);

        // Act
        when(studentRepository.save(harry)).thenReturn(harry);

        // Assert
        var result = prefectService.update(harry);
        assertTrue(result.getPrefect());

    }

    @Test
    void delete() {
        // Arrange
        House gryffindor = new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"});
        Student harry = new Student("Harry", "James", "Potter", gryffindor, 5, Gender.MALE, true);
        Student hermione = new Student("Hermione", null, "Granger", gryffindor, 5, Gender.FEMALE, true);
        Student ron = new Student("Ron", "Bilius", "Weasley", gryffindor, 5, Gender.MALE, false);
        Student ginny = new Student("Ginny", null, "Weasley", gryffindor, 5, Gender.FEMALE, false);

        // Act
        when(studentRepository.findById(1)).thenReturn(Optional.of(harry));
        when(studentRepository.findById(2)).thenReturn(Optional.of(hermione));
        when(studentRepository.findById(3)).thenReturn(Optional.of(ron));
        when(studentRepository.findById(4)).thenReturn(Optional.of(ginny));

        // Assert
        assertEquals(harry, prefectService.delete(1));
        assertEquals(hermione, prefectService.delete(2));
        assertThrows(IllegalArgumentException.class, () -> prefectService.delete(3));
        assertThrows(IllegalArgumentException.class, () -> prefectService.delete(4));
    }
}