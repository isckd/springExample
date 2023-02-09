package com.jojoIdu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest     // 별다른 설정 없이 @SpringBootTest 실행하면, H2 데이터베이스를 자동으로 수행.
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After  // JUnit에서단위 테스트가 끝날 때마다수행되는 메소드 지정
            // 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아 다음 테스트실행시 테스트 실패할 수도 있음.
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        String title= "테스트게시글";
        String content = "테스트 본문";

        //테이블 posts에 insert/update 쿼리를 실행.
        //id값 있다면 update, 없다면 insert 쿼리 실행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojoIdu@gmail.com")
                .build());
        // 테이블 postst에 있는 모든 데이터를 조회해오는 메소드.
        List<Posts> postsList =postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> createDate=" +posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }


}
