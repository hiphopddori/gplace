package kr.smartscore.gplace.domain.sample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import kr.smartscore.gplace.infrastructure.util.HashMapConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
//@TypeDef(name = "json", typeClass = JsonStringType.class)
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name="image_name")
    private String imageName;
    @Column(name="image_url")
    private String imageUrl;

    //@Type(type = "josn")
    //@Column(name = "meta_info", columnDefinition = "json", nullable = false)
    @Convert(converter = HashMapConverter.class)
    @Column(name = "meta_info", columnDefinition = "json")
    private Map<String, Object> metaInfo = new HashMap<>();
    @Transient
    private String metaInfoStr;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private Sample sample;

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public void serializeMetaInfo() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.metaInfoStr = objectMapper.writeValueAsString(metaInfo);
    }
    public void deserializeMetaInfo() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.metaInfo = objectMapper.readValue(metaInfoStr, HashMap.class);
    }
}
