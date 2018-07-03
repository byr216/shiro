package cn.fei.item.assistant.aspect;

import cn.fei.item.assistant.exception.JsonException;
import cn.fei.item.domain.response.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liufei
 * @date 2018/6/19
 */
@RestController
@ControllerAdvice
@Slf4j
public class HandleExceptionAspect {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(JsonException.class)
    public JsonResponse<Void> jsonException(JsonException e) {
        return new JsonResponse<Void>(e.getMessageCode(), e.getMessage());
    }
}
