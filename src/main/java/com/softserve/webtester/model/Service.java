package com.softserve.webtester.model;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * The Service class represents {@code Service} entity stored in the database.
 *
 * @author Roman Zolotar
 * @version 2.0
 */

public class Service extends AbstractDomain implements Serializable {

    private static final long serialVersionUID = -5386109568829000931L;

    @NotNull
    @Digits(fraction = 0, integer = 6)
    @Min(1)
    private Integer sla;

    public Integer getSla() {
        return sla;
    }

    public void setSla(Integer sla) {
        this.sla = sla;
    }

}
