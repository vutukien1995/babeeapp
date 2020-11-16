package com.kien.babee.dalimpl;

import com.kien.babee.dal.PhrasalVerbDAL;
import com.kien.babee.entities.PhrasalVerb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhrasalVerbDALImpl implements PhrasalVerbDAL {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<PhrasalVerb> getByVerb(String verb) {
        Query query = new Query();
        query.addCriteria(Criteria.where("verb").is(verb));
        return (List<PhrasalVerb>) mongoTemplate.find(query, PhrasalVerb.class);
    }

    @Override
    public List<PhrasalVerb> getListByVerb(String verb) {
        Query query = new Query();
        query.addCriteria(Criteria.where("verb").is(verb));
        return mongoTemplate.find(query, PhrasalVerb.class);
    }

    @Override
    public PhrasalVerb getByVerbAndPreposition(String verb, String preposition) {
        Query query = new Query();
        query.addCriteria(Criteria.where("verb").is(verb));
        query.addCriteria(Criteria.where("preposition").is(preposition));
        return mongoTemplate.findOne(query, PhrasalVerb.class);
    }
}
