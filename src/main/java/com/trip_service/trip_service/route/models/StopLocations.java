package com.trip_service.trip_service.route.models;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StopLocations implements Serializable {
    private String locationName;
    private String cityName;
    private String pinCode;
}