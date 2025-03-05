package gusgo.tdc.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonDto {
    private String name;
    private String nickname;
    private String isCustumer;
    private String isProvider;
    private String type;
    private String mainDocument;
    private String secondaryDocument;
    private List<AddressDto> addresses;
}
