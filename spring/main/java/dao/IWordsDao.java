package dao;

import java.util.List;

import domain.Word;

public interface IWordsDao {
	List<Word> getWordsByLevel(int level);
}
