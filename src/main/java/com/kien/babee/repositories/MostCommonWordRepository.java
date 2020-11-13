package com.kien.babee.repositories;

import com.kien.babee.entities.MostCommonWord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MostCommonWordRepository extends MongoRepository<MostCommonWord, String> {
}
