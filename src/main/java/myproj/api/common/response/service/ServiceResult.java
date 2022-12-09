package myproj.api.common.response.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class ServiceResult {

    private String message;
    private Object data;
    private boolean result;

    public static ServiceResult fail(String message) {
        return ServiceResult.builder()
                .message(message)
                .result(false)
                .build();
    }

    public static ServiceResult success(Object data) {
        return ServiceResult.builder()
                .data(data)
                .result(true)
                .build();
    }

    public static ServiceResult success() {
        return ServiceResult.success(null);
    }
}
