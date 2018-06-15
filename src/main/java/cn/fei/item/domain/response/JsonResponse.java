package cn.fei.item.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author liufei
 * @date 2018/6/15 15:17
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse<T> implements Serializable {
    private String code;//状态码
    private String msg;//消息
    private T data;//数据

    public JsonResponse(String code, String msg) {
        this(code, msg, null);
    }

}
