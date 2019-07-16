package io.github.eyulingo.Dao;


import io.github.eyulingo.Entity.Carts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Carts,Long> {
    List<Carts> findByUserId(Long id);
    Carts findByUserIdAndGoodId(Long userId,Long goodId);
}
