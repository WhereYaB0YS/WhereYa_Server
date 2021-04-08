package com.where.WhereYouAt.controller.dto.appointment;

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

    private String place_name;

    private String address_name;

    private String x;

    private String y;

    public DestinationDto (Destination destination){
        this.place_name = destination.getPlaceName();
        this.address_name = destination.getAddressName();
        this.x = destination.getX();
        this.y = destination.getY();
    }

    public static DestinationDto of(Destination destination){
        return new DestinationDto(destination);
    }
}
