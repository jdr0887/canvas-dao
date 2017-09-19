package org.renci.canvas.dao.var.model.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.renci.canvas.dao.var.model.LocatedVariant;

public class LocatedVariantConstraintValidator implements ConstraintValidator<LocatedVariantConstraint, LocatedVariant> {

    @Override
    public void initialize(LocatedVariantConstraint arg0) {

    }

    @Override
    public boolean isValid(LocatedVariant locatedVariant, ConstraintValidatorContext context) {
        boolean ret = false;

        switch (locatedVariant.getVariantType().getId()) {
            case "ins":
            case "snp":
                ret = locatedVariant.getEndPosition().equals(locatedVariant.getPosition() + 1);
                break;
            case "del":
                ret = locatedVariant.getEndPosition().equals(locatedVariant.getPosition() + locatedVariant.getSeq().length());
                break;
            case "sub":
                ret = locatedVariant.getEndPosition() > locatedVariant.getPosition();
                break;
        }

        return ret;
    }

}
