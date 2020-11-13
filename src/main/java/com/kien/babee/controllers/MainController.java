package com.kien.babee.controllers;

import com.kien.babee.dalimpl.PhrasalVerbDALImpl;
import com.kien.babee.entities.MostCommonWord;
import com.kien.babee.entities.PhrasalVerb;
import com.kien.babee.entities.Word;
import com.kien.babee.repositories.MostCommonWordRepository;
import com.kien.babee.repositories.PhrasalVerbRepository;
import com.kien.babee.utils.BaBeeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final PhrasalVerbDALImpl phrasalVerbDAL;

    public MainController (PhrasalVerbRepository phrasalVerbRepository,
                           PhrasalVerbDALImpl phrasalVerbDAL,
                           MostCommonWordRepository mostCommonWordRepository) {
        this.phrasalVerbRepository = phrasalVerbRepository;
        this.phrasalVerbDAL = phrasalVerbDAL;
        this.mostCommonWordRepository = mostCommonWordRepository;
    }

    @GetMapping("/")
    public ModelAndView index() {
        LOG.info("Index ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/catch-the-words")
    public ModelAndView catchTheWords(@RequestParam(value="content", required=false, defaultValue = "") String content) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("content", content);
        modelAndView.setViewName("catchWords");

        // Catch phrasal verbs
        List<PhrasalVerb> list = new ArrayList<>();
        list.addAll(phrasalVerbDAL.getListByVerb("look"));
        modelAndView.addObject("phrasalVerbLst", list);

        // Catch unknow words
        List<String> listSentence = BaBeeUtil.getListSentences(content);
        List<Word> listWord = new ArrayList<>();
        List<MostCommonWord> listAllCommonWords = mostCommonWordRepository.findAll();
        for (String sentence : listSentence) {
            String[] listWordInSentence = sentence.split(" ");
            for (String word: listWordInSentence) {
                String w = word.trim().toLowerCase().replaceAll("[^a-zA-Z0-9]","");
                if (w.isEmpty()) continue;
                
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
        }
        modelAndView.addObject("wordLst", listWord);

        return modelAndView;
    }

    public static void main(String[] args) {
        String str= "This#string%contains^special*characters&\".";
        str = str.replaceAll("[^a-zA-Z0-9]"," ");
        System.out.println(str);
    }

}
