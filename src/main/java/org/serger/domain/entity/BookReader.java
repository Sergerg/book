package org.serger.domain.entity;

import lombok.*;

/**
 * Created by galichanin on 01.03.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BookReader {
    private Long id;
    private long bookId;
    private long readerId;
}
