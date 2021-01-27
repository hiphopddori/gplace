package kr.smartscore.gplace.web.api;

import kr.smartscore.gplace.domain.sample.SampleRepository;
import kr.smartscore.gplace.domain.sample.entity.Image;
import kr.smartscore.gplace.domain.sample.entity.Sample;
import kr.smartscore.gplace.service.SampleService;
import kr.smartscore.gplace.web.dto.sample.SampleImageDto;
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

import java.util.List;
import java.util.Map;

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

    @Autowired
    private SampleRepository sampleRepository;

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
    @Test
    public void sample_이미지_메타정보_JSON_조회_Test() throws  Exception {
        List<SampleImageDto> images = sampleRepository.findByImageJsonColumn();
        Map metaInfo = images.get(0).getMetaInfos().get(0);
        // Assert.assertTrue(images.size() > 0);
        Assert.assertThat(metaInfo.get("area"), is("chungchundong"));
    }
    @Test
    public void sample_이미지_메타정보_JSON_ARRAY_추가() throws  Exception{
        Image image = sampleService.addImageAndMetaDataList();
        Assert.assertThat(((List)image.getMetaInfo().get("datas")).size(), is(2));
    }

}