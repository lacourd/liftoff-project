package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.User;
import org.springframework.data.repository.CrudRepository;

public interface ChoreRepository extends CrudRepository<User, Integer> {
}
