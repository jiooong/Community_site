package org.example.article;

import org.example.article.dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleRepository { // 직접 저장
    private static List<ArticleDto> datum;
    private static long lastId;
    static{ //static 생성자 초기화하는 것 , 여기에 넣으면 프로그램 거의 초창기에 바로 실행이 된다.
        datum = new ArrayList<>();
        lastId=0;

        makeTestData();
    }

    private static void makeTestData() {
        IntStream.rangeClosed(1,10).forEach(id->{ //for문을 멋있게 쓰기
            String title = "제목%d".formatted(id);
            String body = "내용%d".formatted(id);
            write(title,body);

        });
    }

    public static long write(String title, String body) {
        long id = ++lastId;
        ArticleDto newArticleDto = new ArticleDto(id,title,body);

        datum.add(newArticleDto);
        return id;

    }

    public List<ArticleDto> findAll() {
        return datum;
    }

    public ArticleDto findById(long id) {
        for ( ArticleDto articleDto : datum ) {
            if ( articleDto.getId() == id ) {
                return articleDto;
            }
        }

        return null;
    }

    public void delete(long id) {
        ArticleDto articleDto = findById(id);

        if (articleDto == null) return;

        datum.remove(articleDto);

    }

    public void modify(long id, String title, String body) {
        ArticleDto articleDto = findById(id);

        if (articleDto == null) return;

        articleDto.setTitle(title);
        articleDto.setBody(body);

    }
}
