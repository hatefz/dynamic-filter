package com.example.dynamicfilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserQuery query;

    public UserService(UserRepository userRepository, UserQuery query) {
        this.userRepository = userRepository;
        this.query = query;
    }

    public Page<UserEntity> getUsers(UserFilter filter, Pageable pageable) {
        Specification<UserEntity> query = this.convertFilterToSpecification(filter);
        return userRepository.findAll(query, pageable);
    }


    public List<UserEntity> getUsers(UserFilter filter) {
        Specification<UserEntity> query = this.convertFilterToSpecification(filter);
        return userRepository.findAll(query);
    }

    public Specification<UserEntity> convertFilterToSpecification(UserFilter filter) {
        List<Specification<UserEntity>> specifications = this.getSpecifications(filter);

        if (filter.getOr() != null && filter.getOr()) {
            return Specification.anyOf(specifications);
        }
        return Specification.allOf(specifications);
    }

    public Page<UserEntity> getUsers(GeneralUserFilter generalFilter, Pageable pageable) {
        Specification<UserEntity> query = this.convertGeneralFilterToSpecification(generalFilter);
        return userRepository.findAll(query, pageable);
    }

    public List<UserEntity> getUsers(GeneralUserFilter generalFilter) {
        Specification<UserEntity> query = this.convertGeneralFilterToSpecification(generalFilter);
        return userRepository.findAll(query);
    }

    public Specification<UserEntity> convertGeneralFilterToSpecification(GeneralUserFilter generalFilter) {
        List<GeneralUserFilter.GeneralUserFilterUtil> utils = generalFilter.getGeneralFilterUtils();

        Specification<UserEntity> specification = convertFilterToSpecification(utils.get(0).getFilter());

        for (int i = 1; i < utils.size(); i++) {
            GeneralUserFilter.GeneralUserFilterUtil util = utils.get(i);
            Specification<UserEntity> spec = convertFilterToSpecification(util.getFilter());

            if (util.getOr() != null && util.getOr()) {
                specification = specification.or(spec);
            } else {
                specification = specification.and(spec);
            }
        }
        return specification;
    }

    private List<Specification<UserEntity>> getSpecifications(UserFilter filter) {
        List<Specification<UserEntity>> specifications = new ArrayList<>();
        specifications.add(query.hasId(filter.getId()));
        specifications.add(query.hasIds(filter.getIds()));
        specifications.add(query.hasGender(filter.getGender()));
        specifications.add(query.isMarried(filter.getMarried()));
        specifications.add(query.hasCountryName(filter.getCountryName()));
        specifications.add(query.hasCompanyName(filter.getCompanyName()));
        specifications.add(query.hasEmail(filter.getEmail()));
        specifications.add(query.hasPhone(filter.getPhone()));
        specifications.add(query.hasEnglishName(filter.getEnglishName()));
        specifications.add(query.hasPersianName(filter.getPersianName()));
        return specifications;
    }

}
