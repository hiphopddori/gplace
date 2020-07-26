package kr.smartscore.gplace.web.dto.sample;

import kr.smartscore.gplace.domain.sample.entity.Sample;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
// @NoArgsConstructor
public class SampleSaveRequestDto {
    private String name;
    private String email;
    private String picture;
    private String role;
    /*
    public SampleSaveRequestDto(String name, String email, String picture, String role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }
    */
    public Sample toEntity() {
        return Sample.builder()
                .name(this.name)
                .email(this.email)
                .picture(this.picture)
                .role(this.role)
                .build();
    }

}
