package base.user.repository;

import base.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<User, String> {

    Page<User> findUserById(String id, Pageable pageable);

    List<User> findUserByUsername(String username);

    List<User> findUserByUserRole(String userRole);

    void deleteUserById(String id);

    void deleteUserByUsername(String Username);

    User update(User user);
}
