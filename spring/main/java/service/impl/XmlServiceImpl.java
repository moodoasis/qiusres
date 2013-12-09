package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.IWordsDao;
import domain.Word;
import service.XmlService;

@Component
public class XmlServiceImpl implements XmlService{
	
	@Autowired IWordsDao dao;
	
	@Override
	public List<Word> getWordsByLevel(int level) {
		return dao.getWordsByLevel(level);
	}
	
	
	
}
