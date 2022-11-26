package myproj.api.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReponseMessage {

    private ResponseHeader headers;
    private Object body;

    public static ReponseMessage fail(String message, Object data) {
        return ReponseMessage.builder()
                .headers(ResponseHeader.builder()
                        .message(message)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build())
                .body(data)
                .build();
    }
}
