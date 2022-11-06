package com.emphasoft;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = CowSerializer.class)
public class CowQuestion2 extends Cow {

    private CowQuestion2 sibling;

    private CowQuestion2 childCow;

    public CowQuestion2(int cowId, String nickName) {
        super(cowId, nickName, true);
        this.sibling = null;
        this.childCow = null;
    }


}