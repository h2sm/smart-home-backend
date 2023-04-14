package com.h2sm.smarthomebackend.service.entityToDTO;

public interface BaseEntityToDTO<E, D> {

    public E dtoToEntity(D dto);

    public D entityToDTO(E entity);
}
