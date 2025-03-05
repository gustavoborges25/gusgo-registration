package gusgo.tdc.application.dto;

import lombok.Data;

@Data
public class AddressDto {
    private String street;
    private String number;
    private String zipCode;
    private String complement;
    private String state;
    private String city;
}
