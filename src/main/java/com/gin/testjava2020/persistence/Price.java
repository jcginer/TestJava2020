package com.gin.testjava2020.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name= "PRICES")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = Price.PRICE_SEQ, sequenceName = Price.PRICE_SEQ
    , initialValue = 5, allocationSize = 1)
//@Check(constraints = "license IN ('A', 'B', 'C', 'D', 'E')")
public class Price {
    /*BRAND_ID         START_DATE                                    END_DATE                        PRICE_LIST                   PRODUCT_ID  PRIORITY                 PRICE           CURR
    ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    1         2020-06-14-00.00.00                        2020-12-31-23.59.59                        1                        35455                0                        35.50            EUR
    1         2020-06-14-15.00.00                        2020-06-14-18.30.00                        2                        35455                1                        25.45            EUR
    1         2020-06-15-00.00.00                        2020-06-15-11.00.00                        3                        35455                1                        30.50            EUR
    1         2020-06-15-16.00.00                        2020-12-31-23.59.59                        4                        35455                1                        38.95            EUR*/

    private static final long serialVersionUID = 1L;
    static final String PRICE_SEQ = "PRICE_SEQ";

    @Column(name = "BRAND_ID")
    private Long brandId;

    @Column(name = "START_DATE", nullable = false)
//    @Temporal(TemporalType.DATE)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyy-MM-dd-HH.mm.ss")
    private Date startDate;

    @Column(name = "END_DATE", nullable = false)
//    @Temporal(TemporalType.DATE)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyy-MM-dd-HH.mm.ss")
    private Date endDate;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PRICE_SEQ)
    @Column(name = "PRICE_LIST")
    private Long priceList;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Column(name = "PRIORITY", length = 1, nullable = false, columnDefinition = "integer(1) default 0")
    private Integer priority;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "CURR", length = 3, nullable = false, columnDefinition = "varchar(3) default 'EUR'")
    private String currency;

}
