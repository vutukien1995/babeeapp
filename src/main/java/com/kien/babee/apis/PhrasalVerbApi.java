package com.kien.babee.apis;

import com.kien.babee.dto.ParagraphDTO;
import com.kien.babee.entities.PhrasalVerb;
import com.kien.babee.repositories.PhrasalVerbRepository;
import com.kien.babee.utils.BaBeeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/phrasal_verb")
public class PhrasalVerbApi {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final PhrasalVerbRepository phrasalVerbRepository;

    public PhrasalVerbApi(PhrasalVerbRepository phrasalVerbRepository) {
        this.phrasalVerbRepository = phrasalVerbRepository;
    }

    @PostMapping("/create")
    public PhrasalVerb addNew (@RequestBody PhrasalVerb phrasalVerb) {
        LOG.info("Saving a new PhrasalVerb.");
        return phrasalVerbRepository.save(phrasalVerb);
    }

    @GetMapping("/getAll")
    public List<PhrasalVerb> getAllUsers() {
        LOG.info("Getting all PhrasalVerb.");
        return phrasalVerbRepository.findAll();
    }

    @PostMapping("/doCatchWords")
    public List<String> getAllUsers(@ModelAttribute("paragraphDTO") ParagraphDTO paragraphDTO) {
        LOG.info("Call get doCatchWords.");
        return BaBeeUtil.getListSentences(paragraphDTO.getContent());
    }

}
