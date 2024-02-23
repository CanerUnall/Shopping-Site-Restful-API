package com.project.service.helper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.Objects;
@Component
public class PageableHelper {
    public Pageable returnPageable(int page, int size, String sort, String type) {
        Pageable pageable;
        if (Objects.equals(type, "desc")) {
            return pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        } else {
            return pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        }
    }
}
