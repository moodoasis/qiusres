package service;

import java.util.List;

import domain.Word;

public interface XmlService {
	
	List<Word> getWordsByLevel(int level);
	
}
