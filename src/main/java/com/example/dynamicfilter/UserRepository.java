package com.example.dynamicfilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    List<UserEntity> findByGenderAndAgeIsIn(Gender gender, List<Long> ages);
    List<UserEntity> findByGenderAndAgeIn(Gender gender, List<Long> ages);
    List<UserEntity> findByAgeIn(List<Long> ages);
    List<UserEntity> findByGender(Gender gender);
    List<UserEntity> findByAge(Long age);

    @Query(value = "SELECT * from `user_entity` as users where users.age in (:ages)",
            nativeQuery = true)
    List<UserEntity> getUsersByAgeIn(@Param("ages") List<Long> ages);

    @Transactional
    @Modifying
    @Query(value = "update `user_entity` users set users.`gender`='FEMALE' where users.age in (:ages)",
            nativeQuery = true)
    void updateGender(@Param("ages") List<Long> ages);

    @Query(value = "SELECT * FROM user_entity users WHERE users.gender = :gender", nativeQuery = true)
    List<UserEntity> getByGender(@Param("gender") Gender gender);


}
