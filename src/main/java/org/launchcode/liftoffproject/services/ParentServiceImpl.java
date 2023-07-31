package org.launchcode.liftoffproject.services;

import org.launchcode.liftoffproject.models.Parent;
import org.launchcode.liftoffproject.data.ParentRepository;
import org.launchcode.liftoffproject.models.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public abstract class ParentServiceImpl implements ParentService {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Parent findByUsername(String username) {

        // return parentRepository.findByUsername(username);
        return null;
    }

    @Override
    public void registerParent(Parent registerFormDTO) {

        // Parent newParent = new Parent();
        // newParent.setUsername(registerFormDTO.getUsername());
        // String encodedPassword = passwordEncoder.encode(registerFormDTO.getPassword());
        // newParent.setPassword(encodedPassword);
        // newParent.setFirstName(registerFormDTO.getFirstName());
        // newParent.setLastName(registerFormDTO.getLastName());
        // parentRepository.save(newParent);
    }

    @Override
    public Parent getParentByChild(Child child) {

        // return parentRepository.findByChild(child);
        return null;
    }

    public abstract Parent getParentById(Integer parentId);
}
