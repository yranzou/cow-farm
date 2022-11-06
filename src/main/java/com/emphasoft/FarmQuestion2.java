package com.emphasoft;

import java.util.Optional;

public class FarmQuestion2 extends AbstractFarm {

    @Override
    public Cow getNewCow(int cowId, String nickName) {
        return new CowQuestion2(cowId, nickName);
    }

    public Optional<Cow> findCow(int cowId) {
        if (getRootCow().getCowId() == cowId) {
            return Optional.of(getRootCow());
        }
        return findInSiblingOrChild(getRootCow(), cowId);
    }

    @Override
    protected void addChild(Cow parent, Cow calf) {
        var parentCow = (CowQuestion2) parent;
        var child = (CowQuestion2) calf;
        if (parentCow.getChildCow() == null) {
            parentCow.setChildCow(child);
        } else {
            addSibling(parentCow.getChildCow(), child);
        }
    }

    private Optional<Cow> findInSiblingOrChild(Cow cow, int cowId) {
        if (cow.getCowId() == cowId) {
            return Optional.of(cow);
        }
        var siblingResult = findInSibling(cow, cowId);
        if (siblingResult.isPresent()) {
            return siblingResult;
        }
        return findInChild(cow, cowId);
    }

    private Optional<Cow> findInSibling(Cow cow, int cowId) {
        var sibling = ((CowQuestion2) cow).getSibling();
        if (sibling != null) {
            if (sibling.getCowId() == cowId) {
                return Optional.of(sibling);
            }
            return findInSiblingOrChild(sibling, cowId);
        }
        return Optional.empty();
    }

    private Optional<Cow> findInChild(Cow cow, int cowId) {
        var child = ((CowQuestion2) cow).getChildCow();
        if (child != null) {
            if (child.getCowId() == cowId) {
                return Optional.of(child);
            }
            return findInSiblingOrChild(child, cowId);
        }
        return Optional.empty();
    }

    private void addSibling(CowQuestion2 sibling, CowQuestion2 newSibling) {
        while (sibling.getSibling() != null) {
            sibling = sibling.getSibling();
        }
        sibling.setSibling(newSibling);
    }
}
