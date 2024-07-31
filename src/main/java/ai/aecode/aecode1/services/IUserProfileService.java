package ai.aecode.aecode1.services;

import ai.aecode.aecode1.entities.UserProfile;

import java.util.List;

public interface IUserProfileService {
    public void insert(UserProfile userprofile);
    List<UserProfile> list();
    public void delete(int id_user);
    public UserProfile listId(int id_user);
}
