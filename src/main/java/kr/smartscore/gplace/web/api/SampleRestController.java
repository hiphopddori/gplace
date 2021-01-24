package kr.smartscore.gplace.web.api;

import kr.smartscore.gplace.domain.sample.entity.Sample;
import kr.smartscore.gplace.infrastructure.dao.sample.vo.SampleVo;
import kr.smartscore.gplace.service.SampleService;
import kr.smartscore.gplace.web.ResultInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SampleRestController {
    private final SampleService sampleService;

    @GetMapping("api/sample/{id}")
    public SampleVo findById(@PathVariable Long id) {
        return sampleService.findeById(id);
    }

    @PostMapping("api/sample")
    public Sample addSample() {
        return this.sampleService.addSample();
    }

    @GetMapping("api/sample/exception/{id}")
    public ResultInfo throwException(@PathVariable Long id) {
        ResultInfo test = null;
        test.setData("test");
        return test;
    }
    @GetMapping("api/sample/one2one/{id}")
    public Long One2OneTest(@PathVariable Long id) {
        sampleService.One2OneTest(id);
        return 1L;
    }
    @PostMapping("api/user/{id}/image")
    public void reg(@PathVariable Long id) {
        sampleService.regImage(id);
    }

    /*
    @PostMapping("api/sample")
    public Long reg(@RequestBody SampleSaveRequestDto requestDto) {
        return sampleService.reg(requestDto);
    }
    @DeleteMapping("/api/sample/{id}")
    public Long delete(@PathVariable Long id) {
        sampleService.delete(id);
        return id;
    }
     */
}
