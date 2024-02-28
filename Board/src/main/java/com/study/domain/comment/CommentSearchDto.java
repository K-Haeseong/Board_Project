package com.study.domain.comment;

import com.study.common.dto.SearchDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSearchDto extends SearchDto {

    private Long postId; // 게시글 번호

    // 자바의 기본
    // 상속 : 기존의 클래스에 기능을 추가하거나 재정의하여 새로운 클래스를 정의하는 것
    //      ->  1. 자식 클래스에는 부모 클래스의 필드와 메소드만이 상속되며, 생성자와 초기화 블록은 상속되지 X
    //      ->  2. 상속한 클래스의 생략 되어 있는 기본생성자에서 super();를 통해 부모의 기본 생성자를 호출

    // 여기서 기본 생성자는 부모의 기본 생성자
    //  public CommentSearchDto() {
    //      super();
    //  }

    // public SearchDto() {
    //        this.page = 1;
    //        this.recordSize = 10;
    //        this.pageSize = 10;
    //    }
}
