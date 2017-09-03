package com.dkarachurin.trainTickets.dto;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Denis Karachurin on 03.09.2017.
 */
public interface DTOConverter<E, D> {
    D convert(E entity);

    default Collection<D> convertCollection(Collection<E> collection){
        Collection<D> result = new ArrayList<>();

        for (E entity : collection) {
            result.add(convert(entity));
        }

        return result;
    }
}
