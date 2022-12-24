package myproj.api.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public final class ResponseMessage {

    private ResponseHeader headers;
    private Object body;

    public static ResponseMessage fail(String message, Object data) {
        return ResponseMessage.builder()
                .headers(ResponseHeader.builder()
                        .message(message)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build())
                .body(data)
                .build();
    }

    public static ResponseMessage fail(String message) {
        return ResponseMessage.fail(message, null);
    }
}
