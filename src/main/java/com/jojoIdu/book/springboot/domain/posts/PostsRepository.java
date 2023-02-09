// Database를 접근하게 해 줄 JpaRepository 를 생성 (인터페이스).
// Entity 클래스와 기본 Entity Repository는 함께 위치해야 함.
// 보통 Mybatis에서 Dao 라고 불리는 DB Layer 접근자.
package com.jojoIdu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository<Entity 클래스, PK 타입)
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
