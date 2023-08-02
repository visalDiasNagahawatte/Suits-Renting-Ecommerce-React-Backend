package com.example.ecommerceapi.utill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 21-Jul-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponse {
    private int code;
    private String message;
    private Object data;
}
