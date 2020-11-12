package com.kien.babee.repositories;

import com.kien.babee.entities.PhrasalVerb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhrasalVerbRepository extends MongoRepository<PhrasalVerb, String> {
}

