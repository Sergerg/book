package org.serger.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by galichanin on 01.03.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private String title, author;
    private Long id;
}
