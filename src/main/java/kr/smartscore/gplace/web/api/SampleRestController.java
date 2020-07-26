package kr.smartscore.gplace.web.api;

import kr.smartscore.gplace.infrastructure.dao.sample.vo.SampleVo;
import kr.smartscore.gplace.service.SampleService;
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
