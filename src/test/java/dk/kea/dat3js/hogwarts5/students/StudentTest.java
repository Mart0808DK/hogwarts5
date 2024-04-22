package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getFullNameWithMiddleName() {
        // Arrange
        Student student = new Student("Harry", "James", "Potter", null, 1);

        // Act
        var fullName = student.getFullName();

        // Assert
        assertEquals("Harry James Potter", fullName);
    }

    @Test
    void getFullNameWithoutMiddleName() {
        // Arrange
        Student student = new Student("Cho", "Chang", null, 1);

        // Act
        var fullName = student.getFullName();

        // Assert
        assertEquals("Cho Chang", fullName, "Expected full name to be 'Cho Chang'");
    }

    @Test
    void setFullNameWithMiddleName() {
        // Arrange
        Student student = new Student("first", "middle", "last", null, 1);

        // Act
        student.setFullName("Hermione Jean Granger");

        // Assert
        assertEquals("Hermione", student.getFirstName());
        assertEquals("Jean", student.getMiddleName());
        assertEquals("Granger", student.getLastName());
    }

    @Test
    void setFullNameWithoutMiddleName() {
        // Arrange
        Student student = new Student("first", "last", null, 1);

        // Act
        student.setFullName("Neville Longbottom");

        // Assert
        assertEquals("Neville", student.getFirstName());
        assertNull(student.getMiddleName());
        assertEquals("Longbottom", student.getLastName());
    }

    @Test
    void setFullNameWithEmptyString() {
        // Arrange
        Student student = new Student(null, null, null, 1);

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
        Student student = new Student(null, null, null, 1);

        // Act
        student.setFullName("Albus Percival Wulfric Brian Dumbledore");

        // Assert
        assertEquals("Albus", student.getFirstName());
        assertEquals("Percival Wulfric Brian", student.getMiddleName());
        assertEquals("Dumbledore", student.getLastName());
    }

    @Test
    void setFullNameWithoutLastName() {
        // Arrange
        Student student = new Student("Albus", null, null, 1);

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
        Student student = new Student(null, null, null, 1);

        // Act
        student.setFullName(null);

        // Assert
        assertNull(student.getFirstName());
        assertNull(student.getMiddleName());
        assertNull(student.getLastName());
    }
}