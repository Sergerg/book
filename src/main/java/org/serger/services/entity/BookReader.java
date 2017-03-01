package org.serger.services.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by galichanin on 01.03.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookReader {
    private long id;
    private long bookId;
    private long readerId;
}
