package kr.smartscore.gplace.domain.sample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import kr.smartscore.gplace.infrastructure.util.HashMapConverter;
import kr.smartscore.gplace.infrastructure.util.ListConverter;
import kr.smartscore.gplace.infrastructure.util.ObjectConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Convert(converter = ObjectConverter.class)
    @Column(name = "meta_infos", columnDefinition = "json")
    private List<Map<String, Object>> metaInfos = new ArrayList<>();

    @Transient
    private String metaInfoStr;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private Sample sample;

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public Image addMetaInfos(String area, String phone) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("area",area);
        obj.put("phone",phone);

        this.metaInfos.add(obj);
        /*
        List datas = null;
        if (this.metaInfo.containsKey("items")) {
            datas = (List)metaInfo.get("items");
        } else{
            datas =  new ArrayList<Map<String, Object>>();
        }
        datas.add(obj);
        this.metaInfo.put("items", datas);
        */
        return this;
    }

    public Image addMetaList(String area, String phone) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("area",area);
        obj.put("phone",phone);
        List datas = null;
        if (this.metaInfo.containsKey("items")) {
            datas = (List)metaInfo.get("items");
        } else{
            datas =  new ArrayList<Map<String, Object>>();
        }
        datas.add(obj);
        this.metaInfo.put("items", datas);
        return this;
    }

    public Image addMetaInfo(String key, Object value){
        this.metaInfo.put(key, value);
        return this;
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
