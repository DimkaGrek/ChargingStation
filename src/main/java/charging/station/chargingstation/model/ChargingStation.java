package charging.station.chargingstation.model;

import charging.station.chargingstation.validate.PublicCheck;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ChargingStation {

    @NotNull
    private Long id;

    @Size(min = 1, max = 100, groups = PublicCheck.class)
    private String title;

    @Size(min=10, max=255, groups = PublicCheck.class)
    private String description;

    @Size(min= 10, max=100, groups = PublicCheck.class)
    private String address;

    @Pattern(regexp = "^-?\\d{1,2}\\.\\d{1,6},\\s*-?\\d{1,2}\\.\\d{1,6}$", message = "must match 'latitude, longitude'", groups = PublicCheck.class)
    private String geoCoordinates;

    @NotNull
    private Boolean isPublic;

    @NotNull
    @Size(min = 1, max = 8)
    @Valid
    private List<Connector> connectors;

}
