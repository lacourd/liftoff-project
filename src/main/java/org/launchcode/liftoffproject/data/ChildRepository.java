package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Parent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChildRepository extends CrudRepository<Child, Integer> {

    List<Child> findAllByParent(Parent parent);
}
