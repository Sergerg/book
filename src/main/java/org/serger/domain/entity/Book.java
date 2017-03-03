package org.serger.domain.entity;

import lombok.*;

/**
 * Created by galichanin on 01.03.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Book {
    private String title, author;
    private Long id;
}
