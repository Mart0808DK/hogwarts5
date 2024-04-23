package dk.kea.dat3js.hogwarts5.teachers;

import dk.kea.dat3js.hogwarts5.teachers.Teacher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TeacherTest {

    @Test
    void getFullNameWithMiddleName() {
        // Arrange
        Teacher teacher = new Teacher("Severus", "Snape", "Prince", null, null, null);

        // Act
        var fullName = teacher.getFullName();

        // Print the output of the getFullName method
        System.out.println(fullName);

        // Assert
        assertEquals("Severus Snape Prince", fullName);
    }

    @Test
    void getFullNameWithoutMiddleName() {
        // Arrange
        Teacher teacher = new Teacher("Cho", "Chang", null, null, null);

        // Act
        var fullName = teacher.getFullName();

        // Print the output of the getFullName method
        System.out.println(fullName);

        // Assert
        assertEquals("Cho Chang", fullName, "Expected full name to be 'Cho Chang'");
    }

    @Test
    void setFullNameWithMiddleName() {
        // Arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);

        // Act
        teacher.setFullName("Hermione Jean Granger");

        // Print the output of the getFullName method
        System.out.println(teacher.getFullName());

        // Assert
        assertEquals("Hermione", teacher.getFirstName());
        assertEquals("Jean", teacher.getMiddleName());
        assertEquals("Granger", teacher.getLastName());
    }

    @Test
    void setFullNameWithoutMiddleName() {
        // Arrange
        Teacher teacher = new Teacher("first", "last", null, null, null);

        // Act
        teacher.setFullName("Neville Longbottom");

        // Print the output of the getFullName method
        System.out.println(teacher.getFullName());

        // Assert
        assertEquals("Neville", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertEquals("Longbottom", teacher.getLastName());
    }

    @Test
    void setFullNameWithEmptyString() {
        // Arrange
        Teacher teacher = new Teacher(null, null, null, null, null);

        // Act
        teacher.setFullName("");

        // Assert
        assertEquals("", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertNull(teacher.getLastName());
    }

    @Test
    void setFullNameWithMultipleMiddleNames() {
        // Arrange
        Teacher teacher = new Teacher(null, null, null, null, null);

        // Act
        teacher.setFullName("Albus Percival Wulfric Brian Dumbledore");

        // Print the output of the getFullName method
        System.out.println(teacher.getFullName());

        // Assert
        assertEquals("Albus", teacher.getFirstName());
        assertEquals("Percival Wulfric Brian", teacher.getMiddleName());
        assertEquals("Dumbledore", teacher.getLastName());
    }

    @Test
    void setFullNameWithoutLastName() {
        // Arrange
        Teacher teacher = new Teacher(null, null, null, null, null);

        // Act
        teacher.setFullName("Albus");

        // Assert
        assertEquals("Albus", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertNull(teacher.getLastName());
    }

    @Test
    void setFullNameWithNull() {
        // Arrange
        Teacher teacher = new Teacher(null, null, null, null, null);

        // Act
        teacher.setFullName(null);

        // Assert
        assertNull(teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertNull(teacher.getLastName());
    }

    @Test
    void capatalizeIndividualNames() {
        // Arrange
        Teacher teacher = new Teacher(null, null, null, null, null);

        // Act
        teacher.setFirstName("albus");
        teacher.setMiddleName("percival wulfric brian");
        teacher.setLastName("dumbledore");

        // Print the output of the getFullName method
        System.out.println(teacher.getFullName());

        // Assert
        assertEquals("Albus", teacher.getFirstName());
        assertEquals("Percival Wulfric Brian", teacher.getMiddleName());
        assertEquals("Dumbledore", teacher.getLastName());
    }

    @Test
    void capatalizeIndividualNamesWithCrazyCapatalization() {
        // Arrange
        Teacher teacher = new Teacher(null, null, null, null, null);

        // Act
        teacher.setFirstName("hArRy");
        teacher.setMiddleName("jAmEs");
        teacher.setLastName("pOtTeR");

        // Print the output of the getFullName method
        System.out.println(teacher.getFullName());

        // Assert
        assertEquals("Harry", teacher.getFirstName());
        assertEquals("James", teacher.getMiddleName());
        assertEquals("Potter", teacher.getLastName());
    }
}