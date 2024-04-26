package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StudentControllerIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void notNull() {
        assertNotNull(webClient);
    }

    @Test
    void createStudentWithFullName() {
        webClient.post().uri("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new StudentRequestDTO(12, "Martin", "Luther", "King", null, "Gryffindor", 5, Gender.MALE, false))
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().jsonPath("$.id").isEqualTo(12)
                .jsonPath("$.firstName").isEqualTo("Martin")
                .jsonPath("$.middleName").isEqualTo("Luther")
                .jsonPath("$.lastName").isEqualTo("King")
                .jsonPath("$.house").isEqualTo("Gryffindor")
                .jsonPath("$.schoolYear").isEqualTo(5)
                .jsonPath("$.gender").isEqualTo("MALE")
                .jsonPath("$.prefect").isEqualTo(false);
    }

    @Test
    void createStudentWithNameParts() {
        webClient.post().uri("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                            
                            "firstName": "Martin",
                            "middleName": "Luther",
                            "lastName": "King",
                            "house": "Gryffindor",
                            "schoolYear": 5
                        }    
                           """)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().jsonPath("$.id").isEqualTo(12)
                .jsonPath("$.firstName").isEqualTo("Martin")
                .jsonPath("$.middleName").isEqualTo("Luther")
                .jsonPath("$.lastName").isEqualTo("King")
                .jsonPath("$.house").isEqualTo("Gryffindor")
                .jsonPath("$.schoolYear").isEqualTo(5);
    }
}
