package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.Child;
import org.springframework.data.repository.CrudRepository;

public interface ChildRepository extends CrudRepository<Child, Integer> {
}
