package com.codegym.login_logout.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface GenericService<T> {
    List<T> getAll();

    List<T> getAll(Sort sort);

    Page<T> getAll(Pageable pageable);

    T getOne(long id);

    T addOne(T model);

    T updateOne(T model);

    T updateOne(long id, T newModel);

    void deleteOne(Long id);

    void deleteOne(T model);
}
