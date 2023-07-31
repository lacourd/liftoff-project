package org.launchcode.liftoffproject.services;

import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Parent;
import org.launchcode.liftoffproject.models.User;

import java.util.List;

public interface ChildService {

    List<Child> getAllChildren();

    Child getChildById(int id);

    void addChild(Child child);

    void updateChild(Child child);

    void deleteChild(int id);

    List<Child> getChildrenByParent(Parent parent);

    void registerChild(Child newChild);

    Child getChildByUsername(String username);

    Object getEarnedRewards(String username);

    Object getChores(String username);


    User getUserById(Integer userId);
}
