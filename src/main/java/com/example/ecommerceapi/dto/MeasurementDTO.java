package com.example.ecommerceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 27-Jul-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDTO {
    private Long measurementId;
    private String waistSize;
    private String hipSize;
    private String pantLength;
    private String neckSize;
    private String sleeveLength;
    private String jacketSize;
    private String extra;
}
