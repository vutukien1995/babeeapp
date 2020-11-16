package com.kien.babee.dal;

import com.kien.babee.entities.PhrasalVerb;

import java.util.List;

public interface  PhrasalVerbDAL {

    List<PhrasalVerb> getByVerb (String verb);
    List<PhrasalVerb> getListByVerb (String verb);
    PhrasalVerb getByVerbAndPreposition (String verb, String preposition);

}
