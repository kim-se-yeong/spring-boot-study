package com.example.basic;

import lombok.Data;

/**
 * @Data 는 @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor 를 자동으로 적용해준다.
 * 자세한 내용은 @Data 파일 확인해보자!
 */
@Data
public class PhoneData {

    private String name;
    private int price;
}
