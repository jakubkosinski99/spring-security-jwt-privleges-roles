package pl.binarnie.kursy.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
    private Boolean success;
    private String message;
    private String code;
}
