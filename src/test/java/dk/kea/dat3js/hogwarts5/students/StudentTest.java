package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getFullNameWithMiddleName() {
        // Arrange
        Student student = new Student("Harry", "James", "Potter", null, 1, Gender.MALE, true);

        // Act
        var fullName = student.getFullName();

        // Print the output of the getFullName method
        System.out.println(fullName);

        // Assert
        assertEquals("Harry James Potter", fullName);
    }

    @Test
    void getFullNameWithoutMiddleName() {
        // Arrange
        Student student = new Student("Cho", "Chang", null, 1, Gender.FEMALE, false);

        // Act
        var fullName = student.getFullName();

        // Print the output of the getFullName method
        System.out.println(fullName);

        // Assert
        assertEquals("Cho Chang", fullName, "Expected full name to be 'Cho Chang'");
    }

    @Test
    void setFullNameWithMiddleName() {
        // Arrange
        Student student = new Student("first", "middle", "last", null, 1, Gender.MALE, false);

        // Act
        student.setFullName("Hermione Jean Granger");

        // Print the output of the getFullName method
        System.out.println(student.getFullName());

        // Assert
        assertEquals("Hermione", student.getFirstName());
        assertEquals("Jean", student.getMiddleName());
        assertEquals("Granger", student.getLastName());
    }

    @Test
    void setFullNameWithoutMiddleName() {
        // Arrange
        Student student = new Student("first", "last", null, 1, Gender.MALE, false);

        // Act
        student.setFullName("Neville Longbottom");

        // Print the output of the getFullName method
        System.out.println(student.getFullName());

        // Assert
        assertEquals("Neville", student.getFirstName());
        assertNull(student.getMiddleName());
        assertEquals("Longbottom", student.getLastName());
    }

    @Test
    void setFullNameWithEmptyString() {
        // Arrange
        Student student = new Student(null, null, null, 1, Gender.FEMALE, false);

        // Act
        student.setFullName("");

        // Assert
        assertEquals("", student.getFirstName());
        assertNull(student.getMiddleName());
        assertNull(student.getLastName());
    }

    @Test
    void setFullNameWithMultipleMiddleNames() {
        // Arrange
        Student student = new Student(null, null, null, 1, Gender.MALE, false);

        // Act
        student.setFullName("Albus Percival Wulfric Brian Dumbledore");

        // Print the output of the getFullName method
        System.out.println(student.getFullName());

        // Assert
        assertEquals("Albus", student.getFirstName());
        assertEquals("Percival Wulfric Brian", student.getMiddleName());
        assertEquals("Dumbledore", student.getLastName());
    }

    @Test
    void setFullNameWithoutLastName() {
        // Arrange
        Student student = new Student("Albus", null, null, 1, Gender.FEMALE, false);

        // Act
        student.setFullName("Albus");

        // Assert
        assertEquals("Albus", student.getFirstName());
        assertNull(student.getMiddleName());
        assertNull(student.getLastName());
    }

    @Test
    void setFullNameWithNull() {
        // Arrange
        Student student = new Student(null, null, null, 1, Gender.MALE, false);

        // Act
        student.setFullName(null);

        // Assert
        assertNull(student.getFirstName());
        assertNull(student.getMiddleName());
        assertNull(student.getLastName());
    }

    @Test
    void capatalizeIndividualNames() {
        // Arrange
        Student student = new Student(null, null, null, 1, Gender.MALE, false);

        // Act
        student.setFirstName("albus");
        student.setMiddleName("percival wulfric brian");
        student.setLastName("dumbledore");

        // Print the output of the getFullName method
        System.out.println(student.getFullName());

        // Assert
        assertEquals("Albus", student.getFirstName());
        assertEquals("Percival Wulfric Brian", student.getMiddleName());
        assertEquals("Dumbledore", student.getLastName());
    }

    @Test
    void capatalizeIndividualNamesWithCrazyCapatalization() {
        // Arrange
        Student student = new Student(null, null, null, 1, Gender.MALE, false);

        // Act
        student.setFirstName("hArRy");
        student.setMiddleName("jAmEs");
        student.setLastName("pOtTeR");

        // Print the output of the getFullName method
        System.out.println(student.getFullName());

        // Assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("James", student.getMiddleName());
        assertEquals("Potter", student.getLastName());
    }
}