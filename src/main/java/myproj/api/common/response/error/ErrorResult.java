package myproj.api.common.response.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResult {

    public Object errors;

    public static ResponseEntity<?> result(List<?> responseErrors) {

        ErrorResult result = ErrorResult.builder()
                .errors(responseErrors)
                .build();

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
