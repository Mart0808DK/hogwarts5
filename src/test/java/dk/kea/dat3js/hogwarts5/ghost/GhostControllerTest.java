package dk.kea.dat3js.hogwarts5.ghost;

import dk.kea.dat3js.hogwarts5.house.House;
import dk.kea.dat3js.hogwarts5.house.HouseRepository;
import dk.kea.dat3js.hogwarts5.house.HouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GhostController.class)
@ComponentScan(basePackageClasses = {HouseService.class})
class GhostControllerTest {

    @MockBean
    private HouseRepository houseRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        when(houseRepository.findById("Gryffindor")).thenReturn(Optional.of(new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"})));
        when(houseRepository.findById("Slytherin")).thenReturn(Optional.of(new House("Slytherin", "Salazar Slytherin", new String[]{"green", "silver"})));
        when(houseRepository.findById("Ravenclaw")).thenReturn(Optional.of(new House("Ravenclaw", "Rowena Ravenclaw", new String[]{"blue", "silver"})));
        when(houseRepository.findById("Hufflepuff")).thenReturn(Optional.of(new House("Hufflepuff", "Helga Hufflepuff", new String[]{"yellow", "black"})));
    }

    @Test
    void getAllGhost() throws Exception {
        mockMvc.perform(get("/ghosts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder("Nearly Headless Nick", "The Bloody Baron", "The Grey Lady", "The Fat Friar")));
    }

    @Test
    void getGhostByName () throws Exception {
        mockMvc.perform(get("/ghosts/Nearly Headless Nick"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nearly Headless Nick"))
                .andExpect(jsonPath("$.realName").value("Sir Nicholas de Mimsy-Porpington"))
                .andExpect(jsonPath("$.house").value("Gryffindor"));
    }

    /*@Test
    void getGhost() {

        // Arrange
        GhostController ghostController = new GhostController();

        // Act
        var ghost = ghostController.getGhostByName("Nearly Headless Nick");

        // Assert
        assertTrue(ghost.hasBody());
        assertEquals("Nearly Headless Nick", ghost.getBody().getName());
        assertEquals("Sir Nicholas de Mimsy-Porpington", ghost.getBody().getRealName());
        assertEquals("Gryffindor", ghost.getBody().getHouse());


    }*/

}