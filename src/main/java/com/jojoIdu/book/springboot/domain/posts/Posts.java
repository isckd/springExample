package com.jojoIdu.book.springboot.domain.posts;

import com.jojoIdu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 특이점으로 Setter 메소드가 없다. Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다. 다만,
// 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야만함.
@Getter
@NoArgsConstructor
@Entity     // 테이블과 링크될 클래스임을 나타냄. 기본 Repository 와 같이 있어야 역할 수행.
public class Posts extends BaseTimeEntity {

    @Id // 해당테이블의 PK 필드를 나타냄.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙을 나타냄. GenerationType.IDENTITY 속성을 추가해야만 함.
    private Long id;

    @Column(length = 500, nullable = false)  /* 굳이 선언 안해도 해당 클래스의 필드는 모두 컬럼이 됨.
    사용하는이우ㅠ는, 기본값 외 추가로 변경이필요한 옵션이 있으면 사용.
    문자열의 경우 VARCHAR2(255)가 기본인데, 사이즈를 500으로 늘리고 싶거나 타입을 TEXT로 변경하고 싶거나 등의 경우에 사용됨.

    웬만하면 Entity의 PK는 Long타입의 Auto_increment를 추천함. 주민등록번호와 같이 비즈니스상 유니크 키나, 여러 키를 조합한
    복잡키로 PK를 잡을 경우 난감한 상황이 종종 발생함.*/
    private String title;

    @Column(columnDefinition = "Text", nullable = false)
    private String content;

    private String author;

    @Builder    // 해당클래스의 빌더 패턴 클래스를 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
