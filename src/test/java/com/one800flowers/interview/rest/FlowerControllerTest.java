package com.one800flowers.interview.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.one800flowers.interview.model.Update;
import com.one800flowers.interview.service.FeedService;
import com.one800flowers.interview.service.FlowerService;
import com.one800flowers.interview.service.FlowerServiceImpl;
import com.one800flowers.interview.util.DataSupplier;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(MockitoJUnitRunner.class)
public class FlowerControllerTest {
    private MockMvc mockMvc;

    @Mock
    private FeedService feedService;
    @Spy
    private FlowerService flowerService = new FlowerServiceImpl();
    @InjectMocks
    private FlowerController flowerController;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(flowerController).build();
    }

    @Test
    public void testUserCount() throws Exception {
        Mockito.when(feedService.feed()).thenReturn(DataSupplier.data());
        mockMvc.perform(MockMvcRequestBuilders.get("/flower/usercount")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.uniqueUserIds").value(10));
    }

    @Test
    public void testModify() throws Exception {
        Mockito.when(feedService.feed()).thenReturn(DataSupplier.data());

        mockMvc.perform(MockMvcRequestBuilders.post("/flower/update")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(new Update(4, "1800Flowers", "1800Flowers")))
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[3].title").value("1800Flowers"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[3].body").value("1800Flowers"));
    }

    @Test
    public void testModify_FeedrServiceFail() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Feed Service Failed");
        Mockito.when(feedService.feed()).thenThrow(new Exception("Feed Service Failed"));

        mockMvc.perform(MockMvcRequestBuilders.post("/flower/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(new Update(4, "1800Flowers", "1800Flowers")))
        );
    }
}
