package sunpp.its.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponseDTO {
    public enum ErrCodeEnum {
        FIELD_MUST_NOT_BE_EMPTY,
        FIELD_MUST_BE_UNIQUE,
        RECORD_NOT_FOUND,
    }
    public static class ErrorDTO {
        public ErrCodeEnum code;
        public String variableName;
        public String message;

        public ErrorDTO(ErrCodeEnum code, String variableName, String message) {
            this.code = code;
            this.variableName = variableName;
            this.message = message;
        }
    }

    public ErrorResponseDTO() {
        this.errors = new ArrayList<>();
    }

    public List<ErrorDTO> errors;
}
