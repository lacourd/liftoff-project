package org.launchcode.liftoffproject.services;

import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Parent;
import org.launchcode.liftoffproject.data.ChildRepository;
import org.launchcode.liftoffproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.launchcode.liftoffproject.services.ChildService;

import java.util.List;

@Service
public  class ChildServiceImpl implements ChildService {

    @Autowired
    private ChildRepository childRepository;

    @Override
    public List<Child> getAllChildren() {
        return (List<Child>) childRepository.findAll();
    }

    @Override
    public Child getChildById(int id) {
        return childRepository.findById(id).orElse(null);
    }

    @Override
    public void addChild(Child child) {
        childRepository.save(child);
    }

    @Override
    public void updateChild(Child child) {
        childRepository.save(child);
    }

    @Override
    public void deleteChild(int id) {
        childRepository.deleteById(id);
    }

    @Override
    public List<Child> getChildrenByParent(Parent parent) {
        return childRepository.findByParent(parent);
    }

    @Override
    public void registerChild(Child child) {

        childRepository.save(child);
    }

//    @Override
//    public Child getChildByUsername(String username) {
//        return null;
//    }

    @Override
    public Child getChildByUsername(String username) {
        return childRepository.findByUserAccountUsername(username);
    }

    @Override
    public Object getEarnedRewards(String username) {
        return null;
    }

    @Override
    public Object getChores(String username) {
        return null;
    }

    @Override
    public User getUserById(Integer userId) {
        return null;
    }


}
