package com.kien.babee.apis;

import com.kien.babee.entities.Contribute;
import com.kien.babee.repositories.ContributeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/contribute")
public class ContributeApi {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final ContributeRepository contributeRepository;

    ContributeApi (ContributeRepository contributeRepository) {
        this.contributeRepository = contributeRepository;
    }

    @PostMapping("/saveContribute")
    public ModelAndView saveContribute (@ModelAttribute Contribute contribute) {
        LOG.info("Contribute save new.");

        contribute.setType("PHRASAL_VERB");
        contributeRepository.save(contribute);

        ModelMap modelMap = new ModelMap();
        return new ModelAndView("redirect:/", modelMap);
    }

}
