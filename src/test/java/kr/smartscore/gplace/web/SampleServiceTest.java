package kr.smartscore.gplace.web;

import kr.smartscore.gplace.service.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleServiceTest {
    @Autowired
    private SampleService sampleService;

    //@Test
    public void Sample_One2One_테스트 () {
        sampleService.regEtc(21);
    }

    //@Test
    public void Sample_One2Many_Many_수정_테스트 () {
        sampleService.changeImage();
    }
    //@Test
    public void Sample_ImageById_JPQL_수정_테스트 () {
        sampleService.changeImageById(22L,15L);
    }

    //@Test
    public void Sample_ImageById_JPQL_삭제_테스트 () {
        sampleService.removeImageById(22L,14L);
    }

    //@Test
    public void Sample_Image_추가_테스트 () {
        sampleService.regImage(22L);
    }



    //@Test
    public void Sample_One2One_delete_테스트 (){
        sampleService.deleteEtc(21);
    }


}
