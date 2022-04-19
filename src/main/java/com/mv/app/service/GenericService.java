package com.mv.app.service;

import java.util.List;

public interface GenericService<T> {

    void excluir(Long id);

    T buscarPorId(Long id);

    List<T> buscarTodos();
}
