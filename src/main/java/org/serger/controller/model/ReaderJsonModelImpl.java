package org.serger.controller.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by galichanin on 05.03.2017.
 */
@Component
public class ReaderJsonModelImpl implements ReaderJsonModel {

    private static final Logger log = LoggerFactory.getLogger(ReaderJsonModelImpl.class);

    @Override
    public String select(long readerId) {
        return null;
    }

    @Override
    public String delete(long readerId, long bookId) {

        return null;
    }
}
