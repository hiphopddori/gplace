package kr.smartscore.gplace.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@WebMvcTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleApiControllerTest {

    @LocalServerPort
    private int port;
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                // .apply(springSecurity())
                .build();
    }

    //@Test
    public void sample_마이바티스_조회() throws Exception {
        String url = "http://localhost:" + port + "/api/sample/1";
        //when
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    //@Test
    public void sample_공통_예외처리_테스트() throws Exception {
        String url = "http://localhost:" + port + "/api/sample/exception/1";
        //when
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
    //@Test
    public void sample_One2One_조_테스트() throws Exception {
        String url = "http://localhost:" + port + "/api/sample/one2one/1";
        //when
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void user_image_reg_findBy없이_fk설정() throws Exception {
        String url = "http://localhost:" + port + "/api/user/21/image";
        //when
        mvc.perform(post(url))
                .andExpect(status().isOk());
    }

    //@Test
    public void sample_mappedBy_test() throws Exception {
        String url = "http://localhost:" + port + "/api/sample/save";
        //when
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
    //@Test
    public void sample_One2One_test() throws Exception {
        String url = "http://localhost:" + port + "/api/sample/save";
        //when
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    /*
    @Test
    public void sample_JPA_등록() throws Exception {
        //given
        SampleSaveRequestDto requestDto = SampleSaveRequestDto.builder()
                .email("developerkorea@gmail.com")
                .name("김또리")
                .role("ADMIN")
                .picture("NONE")
                .build();

        String url = "http://localhost:" + port + "/api/sample";
        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
    }
    @Test
    public void sample_JPA_삭제() throws Exception {
        String url = "http://localhost:" + port + "/api/sample/3";
        //when
        mvc.perform(delete(url))
                .andExpect(status().isOk());
    }
     */

}
