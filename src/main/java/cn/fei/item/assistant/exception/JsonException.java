package cn.fei.item.assistant.exception;

import cn.fei.item.domain.response.JsonResponse;
import cn.fei.item.utils.ServletUtils;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonException extends RuntimeException {

    public JsonException(String messageCode, String message) {
        this(message);
        ServletResponse response = ServletUtils.getResponse();
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JsonResponse<Void> result = new JsonResponse<>(messageCode, message);
        response.reset();
        response.setContentType("application/json");
        writer.print(result);
        writer.flush();
        writer.close();
    }

    public JsonException(String message) {
        super(message);
    }
}
