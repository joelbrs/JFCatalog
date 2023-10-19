package br.com.joelbrs.JFCatalog.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericResource<T, K> {
    Page<T> findAllPaged(Pageable pageable);
    T findById(Long id);
    T insert(K dto);
    T update(Long id, K dto);
    void delete(Long id);
}
