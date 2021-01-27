package kr.smartscore.gplace.web.dto.sample;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SampleImageDto {
    private String name;
    private long id;
    private String imageName;
    private Map metaInfo;
    private List<Map<String, Object>> metaInfos;
}
