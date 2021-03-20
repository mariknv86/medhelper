package ru.mariknv86.medhelper.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "REQUEST")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    /**
     * id запроса
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQ_ID")
    private Long id;

    /**
     * вопрос
     */
    @Column(name = "REQ_QUESTION")
    private String question;

    /**
     * ответ
     */
    @Column(name = "REQ_ANSWER", columnDefinition = "text")
    private String answer;

    /**
     * примечание
     */
    @Column(name = "REQ_NOTE", columnDefinition = "text")
    private String note;

    /**
     * ссылка на норматвный документ
     */
    @Column(name = "REQ_URL")
    private String url;

}
