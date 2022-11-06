package com.emphasoft;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Cow {

    private int cowId;

    private String nickName;

    private boolean isAlive;
}
