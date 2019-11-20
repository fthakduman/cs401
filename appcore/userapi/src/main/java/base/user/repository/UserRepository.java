package base.user.repository;

import base.user.model.UserImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<UserImpl, String> {

    Page<UserImpl> findUserImplById (String id, Pageable pageable);

    List<UserImpl> findUserImplByUserName(String userName);

    List<UserImpl> findUserImplByUserRole(String userRole);

    void deleteUserImplById(String id);

    void deleteUserImplByUserName(String Username);

}
