package pl.binarnie.kursy.controller.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.binarnie.kursy.response.ApiResponse;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @RequestMapping("")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> test() {
        return new ResponseEntity<>(new ApiResponse(true, "XD", ""), HttpStatus.OK);
    }
}
