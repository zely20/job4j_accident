package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.entity.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

    Authority findByAuthority(String authority);
}
