package dk.kea.dat3js.hogwarts5.students;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByPrefectIsTrue ();

    List<Student> findAllByPrefectIsTrueAndHouse_Name(String house_name);
}
