package com.kien.babee.controllers;

import com.kien.babee.dalimpl.PhrasalVerbDALImpl;
import com.kien.babee.entities.Contribute;
import com.kien.babee.entities.MostCommonWord;
import com.kien.babee.entities.PhrasalVerb;
import com.kien.babee.entities.Word;
import com.kien.babee.repositories.ContributeRepository;
import com.kien.babee.repositories.MostCommonWordRepository;
import com.kien.babee.repositories.PhrasalVerbRepository;
import com.kien.babee.utils.BaBeeUtil;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final PhrasalVerbRepository phrasalVerbRepository;
    private final MostCommonWordRepository mostCommonWordRepository;
    private final ContributeRepository contributeRepository;
    private final PhrasalVerbDALImpl phrasalVerbDAL;

    public MainController (PhrasalVerbRepository phrasalVerbRepository,
                           PhrasalVerbDALImpl phrasalVerbDAL,
                           MostCommonWordRepository mostCommonWordRepository,
                           ContributeRepository contributeRepository) {
        this.phrasalVerbRepository = phrasalVerbRepository;
        this.phrasalVerbDAL = phrasalVerbDAL;
        this.mostCommonWordRepository = mostCommonWordRepository;
        this.contributeRepository = contributeRepository;
    }

    @GetMapping("/")
    public ModelAndView index() {
        LOG.info("Index ");

        List<Contribute> contributeLst = contributeRepository.findAll(Sort.by(Sort.Direction.DESC, "creationDate"));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "Home");
        modelAndView.addObject("contributeLst", contributeLst);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping("/test")
    public ModelAndView test() {
        LOG.info("Index ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "Test");
        modelAndView.setViewName("test");
        return modelAndView;
    }

    @GetMapping("/catch-the-words")
    public ModelAndView catchTheWords(@RequestParam(value="content", required=false, defaultValue = "") String content) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("content", content);
        modelAndView.addObject("title", "Catch Words");
        modelAndView.setViewName("catchWords");

        // Catch unknow words
        List<String> listSentence = BaBeeUtil.getListSentences(content);
        List<Word> listWord = new ArrayList<>();
        List<MostCommonWord> listAllCommonWords = mostCommonWordRepository.findAll();
        List<List<String>> listWordForPhrasalVerb = new ArrayList<>();
        for (String sentence : listSentence) {
            List<String> listWordForPV = new ArrayList<>();
            String[] listWordInSentence = sentence.split(" ");
            for (String word: listWordInSentence) {
                String w = BaBeeUtil.removeSpecialCharacters(word);
                if (w.isEmpty()) continue;
                listWordForPV.add(w);

                MostCommonWord commonWord = listAllCommonWords.stream()
                        .filter(element -> w.equals(element.getWord()))
                        .findAny().orElse(null);
                if (commonWord == null) {
                    Word word_to_view = new Word();
                    word_to_view.setWord(w);
                    word_to_view.setType("n");
                    word_to_view.setMeaning("what is mean?");
                    listWord.add(word_to_view);
                }
            }
            listWordForPhrasalVerb.add(listWordForPV);
        }
        // Check words after catch with api

        // Catch phrasal verbs
        List<PhrasalVerb> phrasalVerbLst = new ArrayList<>();
        for (List<String> pvList : listWordForPhrasalVerb) {
            for (String word : pvList) {
                List<PhrasalVerb> list = phrasalVerbDAL.getListByVerb(word);

                phrasalVerbLst.addAll(list);
            }
        }

        modelAndView.addObject("phrasalVerbLst", phrasalVerbLst);
        modelAndView.addObject("wordLst", listWord);

        return modelAndView;
    }

    @GetMapping("/phrasal-verbs")
    public ModelAndView phrasal_verbs() {
        LOG.info("Call phrasal-verbs");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "Phrasal Verb");
        modelAndView.setViewName("phrasalVerbs");
        return modelAndView;
    }

    @GetMapping("/contribute-phrasal-verbs")
    public ModelAndView contribute_phrasal_verbs() {
        LOG.info("Call contribute-phrasal-verbs ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "Contribute Phrasal Verbs");
        modelAndView.setViewName("contributePhrasalVerbs");
        return modelAndView;
    }

    @GetMapping("/contribute-phrasal-verbs-view")
    public ModelAndView contribute_phrasal_verbs_view(@RequestParam(value="contribute", required=false, defaultValue = "") String contribute) {
        LOG.info("Call contribute-phrasal-verbs-view ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "Contribute Phrasal Verbs");
        modelAndView.setViewName("contributePhrasalVerbsView");

        // Get contribute
        Contribute contributeElement = contributeRepository.findByid(new ObjectId(contribute));
        System.out.println("contributeElement: " + contributeElement);
        // Phrasal verb for table
        List<PhrasalVerb> phrasalVerbLst = BaBeeUtil.getPhrasalVerbFromText(contributeElement.getContent());

        modelAndView.addObject("contribute", contributeElement);
        modelAndView.addObject("phrasalVerbLst", phrasalVerbLst);

        return modelAndView;
    }

    @GetMapping("/contribute-phrasal-verbs-view-admin")
    public ModelAndView contribute_phrasal_verbs_view_admin(@RequestParam(value="contribute", required=false, defaultValue = "") String contribute) {
        LOG.info("Call contribute-phrasal-verbs-view ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "Contribute Phrasal Verbs");
        modelAndView.setViewName("contributePhrasalVerbsViewAdmin");

        // Get contribute
        Contribute contributeElement = contributeRepository.findByid(new ObjectId(contribute));
        System.out.println("contributeElement: " + contributeElement);
        // Phrasal verb for table
        List<PhrasalVerb> phrasalVerbLst = BaBeeUtil.getPhrasalVerbFromText(contributeElement.getContent());

        modelAndView.addObject("contribute", contributeElement);
        modelAndView.addObject("phrasalVerbLst", phrasalVerbLst);

        return modelAndView;
    }




    public static void main(String[] args) {
        String str= "This#string-contains^s'^pecial*characters& Country: How One Family’s\".";
        str = str.replaceAll("’s","");
        str = str.replaceAll("'s","");
        str = str.replaceAll("[:,\"]","");
        System.out.println(str);
    }

}
