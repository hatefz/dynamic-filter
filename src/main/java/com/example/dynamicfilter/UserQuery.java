package com.example.dynamicfilter;

import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQuery {

    public Specification<UserEntity> hasId(Long id) {
        if (id == null) return null;
        return (root, cq, cb) -> cb.equal(root.get("id"), id);
    }

    public Specification<UserEntity> hasPersianName(String persianName) {
        if (persianName == null || persianName.isEmpty()) return null;
        return (root, cq, cb) -> cb.like(root.get("persianName"), "%" + persianName + "%");
    }

    public Specification<UserEntity> hasEnglishName(String englishName) {
        if (englishName == null || englishName.isEmpty()) return null;
        return (root, cq, cb) -> cb.like(cb.function("binary", String.class, root.get("englishName")), "%" + englishName + "%");
    }

    public Specification<UserEntity> hasEmail(String email) {
        if (email == null || email.isEmpty()) return null;
        return (root, cq, cb) -> cb.like(cb.function("binary", String.class, root.get("email")), "%" + email + "%");
    }

    public Specification<UserEntity> hasPhone(String phone) {
        if (phone == null || phone.isEmpty()) return null;
        return (root, cq, cb) -> cb.equal(root.get("phone"), phone);
    }

    public Specification<UserEntity> hasGender(Gender gender) {
        if (gender == null) return null;
        return (root, cq, cb) -> cb.equal(root.get("gender"), gender);
    }

    public Specification<UserEntity> hasCompanyName(String companyName) {
        if (companyName == null || companyName.isEmpty()) return null;
        return (root, cq, cb) -> cb.equal(root.join("employee", JoinType.LEFT).join("company", JoinType.LEFT).get("name"), companyName);
    }

    public Specification<UserEntity> hasCountryName(String countryName) {
        if (countryName == null || countryName.isEmpty()) return null;
        return (root, cq, cb) -> cb.equal(root.get("employee").get("company").get("country").get("name"), countryName);
    }

    public Specification<UserEntity> isMarried(Boolean married) {
        if (married == null) return null;
        if (married) {
            return (root, cq, cb) -> cb.equal(root.get("married"), true);
        }
        return (root, cq, cb) -> cb.or(cb.isNull(root.get("married")), cb.equal(root.get("married"), false));
    }

    public Specification<UserEntity> hasIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return null;
        return (root, cq, cb) -> root.get("id").in(ids);
    }
}