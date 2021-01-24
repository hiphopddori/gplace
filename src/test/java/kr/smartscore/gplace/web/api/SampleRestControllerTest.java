package kr.smartscore.gplace.web.api;

import kr.smartscore.gplace.domain.sample.entity.Image;
import kr.smartscore.gplace.domain.sample.entity.Sample;
import kr.smartscore.gplace.service.SampleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleRestControllerTest {


    @Autowired
    private SampleService sampleService;

    @Test
    public void sample_저장후_자동증가_idx_가져오는지_TEST() throws Exception {
        /*
        String url = "http://localhost:" + port + "/api/sample";
        //when
        mvc.perform(post(url))
                .andExpect(status().isOk());
         */
        Sample sample = sampleService.addSample();
        Assert.assertThat(sample.getId(), is(2L));
    }

    @Test
    public void sample_이미지_저장후_idx_가져오기_Test() throws Exception {
        Image image = sampleService.addImage();
        Assert.assertThat(image.getIdx(), is(8L));
    }
    @Test
    public void sample_이미지_메타정보_JSON_추가_Test() throws  Exception {
        Image image = sampleService.addImageAndMetaData();
        Assert.assertThat(image.getMetaInfo().get("area"), is("gurodigital"));
    }
}