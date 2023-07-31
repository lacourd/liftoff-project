package org.launchcode.liftoffproject.services;

import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Parent;

public interface ParentService {

    Parent findByUsername(String username);

    void registerParent(Parent registerFormDTO);

    Parent getParentByChild(Child child);

    boolean isUsernameTaken(String username);

    Parent getParentByUsername(String username);


}
