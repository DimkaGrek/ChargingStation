package charging.station.chargingstation.controllers;

import charging.station.chargingstation.model.ChargingStation;
import charging.station.chargingstation.validate.PublicCheck;
import jakarta.validation.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class StationController {

    private final LocalValidatorFactoryBean validator;

    @PostMapping("/api/validate")
    public ResponseEntity<?> validateStation(@Valid @RequestBody ChargingStation chargingStation) {
        System.out.println("Validate Station data...");
        if (chargingStation.getIsPublic()) {
                Set<ConstraintViolation<ChargingStation>> violations = validator.validate(chargingStation, PublicCheck.class);

                if (!violations.isEmpty()) {
                    Map<String, String> errors = violations.stream()
                            .collect(Collectors.toMap(
                                    violation -> violation.getPropertyPath().toString(),
                                    ConstraintViolation::getMessage,
                                    (existingValue, newValue) -> existingValue + "; " + newValue));
                    return ResponseEntity.badRequest().body(errors);
                }

        }

        return ResponseEntity.ok().body(Map.of("message", "Data validated successfully"));
    }
}
