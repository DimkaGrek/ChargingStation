package charging.station.chargingstation.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Connector {

    @NotNull
    private Long id;

    @NotNull
    @Pattern(regexp = "^(CCS|CHAdeMO|Type1|Type2)$")
    private String type;

    @NotNull
    @Min(1)
    @Max(350)
    private Integer maxPower;
}
