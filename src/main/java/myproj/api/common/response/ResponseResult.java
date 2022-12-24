package myproj.api.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import myproj.api.common.response.service.ServiceResult;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
@Builder
public final class ResponseResult {

    public static ResponseEntity<?> fail(String message, Object data) {
        return ResponseEntity.badRequest().body(ResponseMessage.fail(message, data));
    }

    public static ResponseEntity<?> fail(String message) {
        return ResponseResult.fail(message, null);
    }

    public static ResponseEntity<?> success(Object data) {
        return ResponseEntity.ok(data);
    }

    public static ResponseEntity<?> success() {
        return ResponseResult.success(null);
    }

    public static ResponseEntity<?> result(ServiceResult result) {

        if (!result.isResult()) {
            return ResponseResult.fail(result.getMessage());
        }

        return ResponseResult.success(result.getData());
    }
}
