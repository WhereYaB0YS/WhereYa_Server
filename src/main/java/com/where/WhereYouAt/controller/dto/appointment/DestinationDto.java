package com.where.WhereYouAt.controller.dto.appointment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.where.WhereYouAt.domain.dto.Destination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DestinationDto {

    @JsonProperty("place_name")
    private String placeName;

    @JsonProperty("address_name")
    private String addressName;

    private String x;

    private String y;

    public DestinationDto (Destination destination){
        this.placeName = destination.getPlaceName();
        this.addressName = destination.getAddressName();
        this.x = destination.getX();
        this.y = destination.getY();
    }

    public static DestinationDto of(Destination destination){
        return new DestinationDto(destination);
    }
}
