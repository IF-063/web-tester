package com.softserve.webtester.model;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

/**
 * The Service class represents {@code Service} entity stored in the database.
 *
 * @author Roman Zolotar
 * @version 2.0
 */

public class Service extends AbstractDomain implements Serializable {

    private static final long serialVersionUID = -5386109568829000931L;

    @Digits(fraction = 0, integer = 9)
    @DecimalMin("1")
    private Integer sla;


    public Integer getSla() {
        return sla;
    }

    public void setSla(Integer sla) {
        this.sla = sla;
    }


}
