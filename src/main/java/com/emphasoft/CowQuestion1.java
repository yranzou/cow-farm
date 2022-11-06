package com.emphasoft;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = CowSerializer.class)
public class CowQuestion1 extends Cow {
    private List<CowQuestion1> children;

    public CowQuestion1(int cowId, String nickName, boolean isAlive, List<CowQuestion1> children) {
        super(cowId, nickName, isAlive);
        this.children = children;
    }
}