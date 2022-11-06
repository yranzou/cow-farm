package com.emphasoft;

import java.util.ArrayList;
import java.util.Optional;

public class FarmQuestion1 extends AbstractFarm {

    public Cow getNewCow(int cowId, String nickName) {
        return new CowQuestion1(cowId, nickName, true, new ArrayList<>());
    }

    public Optional<Cow> findCow(int cowId) {
        if (getRootCow().getCowId() == cowId) {
            return Optional.of(getRootCow());
        }
        return findInSiblingOrChild(getRootCow(), cowId);
    }

    @Override
    protected void addChild(Cow parent, Cow calf) {
        ((CowQuestion1) parent).getChildren().add((CowQuestion1) calf);
    }

    private Optional<Cow> findInSiblingOrChild(Cow cow, int cowId) {
        if (cow.getCowId() == cowId) {
            return Optional.of(cow);
        }
        return findInChildren(cow, cowId);
    }

    private Optional<Cow> findInChildren(Cow cow, int cowId) {
        return ((CowQuestion1) cow).getChildren()
                .stream()
                .map(c -> findInSiblingOrChild(c, cowId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny();
    }
}
