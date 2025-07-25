package com.lalit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lalit.controller.PlayerController;
import com.lalit.exception.AlreadyExistsException;
import com.lalit.exception.NotFoundException;
import com.lalit.model.Player;
import com.lalit.service.FootballService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value =  PlayerController.class)

public class PlayerControllerTest {
    @Autowired
    private MockMvc mvc ;

    @MockitoBean
    private FootballService footballService ;

    @Test
    public void testListPlayers() throws Exception {
        // ARRANGE The following lines configure
        //FootballService to return a list of two players when invoking the listPlayers method:
        Player player1 = new Player("1884823", 5, "Ivana ANDRES", "Defender", LocalDate.of(1994, 07, 13));
        Player player2 = new Player("325636", 11, "Alexia PUTELLAS", "Midfielder", LocalDate.of(1994, 02, 04));
        List<Player> players = List.of(player1, player2);
        given(footballService.listPlayers()).willReturn(players);
        // ACT & ASSERT
        // create an mvc field to emulate the http and validate the behavour
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/players").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andReturn();
// The preceding code performs a GET request that accepts application/JSON content. The expected
//result is OK, meaning any HTTP status code between 200 and 299. The expected result is a JSON
//array with two elements. Finally, we save the result in the result variable.
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        // In summary, this code snippet configures an ObjectMapper to handle Java 8 date and time types by registering the JavaTimeModule. This is essential for correctly converting these types to and from JSON format,
        // ensuring that date and time information is preserved accurately during serialization and deserialization processes.
        String json = result.getResponse().getContentAsString();
        List<Player> returnedPlayers = mapper.readValue(json,
                mapper.getTypeFactory().constructCollectionType(List.class, Player.class));
        // Putting it all together, this line of code reads a JSON string (stored in the variable json) and deserializes it into a List of Player objects. The ObjectMapper uses the type information provided by constructCollectionType to understand that it should create a list where each element is an instance of the Player class.
        // The result is stored in the returnedPlayers variable.
        assertArrayEquals(players.toArray(), returnedPlayers.toArray());
    }

    @Test
    public void testReadPlayer_doesnt_exist() throws Exception {
        // ARRANGE
        String id = "1884823";
        given(footballService.getPlayer(id))
                .willThrow(new NotFoundException("Player not found")); // for checking exception

        // ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders.get("/players/" + id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testReadPlayer_exist() throws Exception {
        // ARRANGE
        Player player = new Player("1884823", 5, "Ivana ANDRES", "Defender", LocalDate.of(1994, 07, 13));
        given(footballService.getPlayer("1884823"))
                .willReturn(player);
        String id = "1884823";
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        // ACT & ASSERT
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/players/" + id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Player returnedPlayer = mapper.readValue(result.getResponse().getContentAsString(), Player.class);
        assertEquals(player, returnedPlayer);
    }



    @Test
    public void testDeletePlayer() throws Exception {
        String id = "1884823";
        mvc.perform(MockMvcRequestBuilders.delete("/players/" + id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePlayer_exists() throws Exception {
        // ARRANGE
        Player player = new Player("1884823", 5, "Ivana ANDRES", "Defender", LocalDate.of(1994, 07, 13));
        given(footballService.updatePlayer(player)).willReturn(player);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        // ACT & ASSERT
        mvc.perform(
                        MockMvcRequestBuilders.put("/players/" + player.id())
                                .content(mapper.writeValueAsString(player))
                                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePlayer_doesnt_exists() throws Exception {
        // ARRANGE
        Player player = new Player("1884823", 5, "Ivana ANDRES", "Defender", LocalDate.of(1994, 07, 13));
        given(footballService.updatePlayer(player)).willThrow(new NotFoundException("Player not found"));
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        // ACT & ASSERT
        mvc.perform(
                        MockMvcRequestBuilders.put("/players/" + player.id())
                                .content(mapper.writeValueAsString(player))
                                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreatePlayer_exists() throws Exception {
        // ARRANGE
        Player player = new Player("1884823", 5, "Ivana ANDRES", "Defender", LocalDate.of(1994, 07, 13));
        given(footballService.addPlayer(player)).willReturn(player);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        // ACT & ASSERT
        mvc.perform(
                        MockMvcRequestBuilders.post("/players")
                                .content(mapper.writeValueAsString(player))
                                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreatePlayer_already_exists() throws Exception {
        // ARRANGE
        Player player = new Player("1884823", 5, "Ivana ANDRES", "Defender", LocalDate.of(1994, 07, 13));
        given(footballService.addPlayer(player)).willThrow(new AlreadyExistsException("The player already exists"));
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        // ACT & ASSERT
        mvc.perform(
                        MockMvcRequestBuilders.post("/players")
                                .content(mapper.writeValueAsString(player))
                                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
