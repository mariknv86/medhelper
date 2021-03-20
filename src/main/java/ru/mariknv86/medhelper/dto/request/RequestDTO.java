package ru.mariknv86.medhelper.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Тело запроса
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    /**
     * вопрос
     */
    private String question;

    /**
     * ответ
     */
    private String answer;

    /**
     * примечание
     */
    private String note;

    /**
     * ссылка на нормативный документ
     */
    private String url;


}
