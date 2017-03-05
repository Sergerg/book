package org.serger.controller.model;

/**
 * Created by galichanin on 04.03.2017.
 */
public interface BookJsonModel {
    String select(long id);
    void delete(long id);
    void update(BookJson book);
    void insert(BookJson book);
    String selectAll();
}
