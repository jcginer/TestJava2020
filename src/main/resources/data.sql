/*BRAND_ID         START_DATE                                    END_DATE                        PRICE_LIST                   PRODUCT_ID  PRIORITY                 PRICE           CURR
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
1         2020-06-14-00.00.00                        2020-12-31-23.59.59                        1                        35455                0                        35.50            EUR
1         2020-06-14-15.00.00                        2020-06-14-18.30.00                        2                        35455                1                        25.45            EUR
1         2020-06-15-00.00.00                        2020-06-15-11.00.00                        3                        35455                1                        30.50            EUR
1         2020-06-15-16.00.00                        2020-12-31-23.59.59                        4                        35455                1                        38.95            EUR*/

-- insert into PRICE(PRICE_LIST, BRAND_ID, START_DATE, END_DATE, PRODUCT_ID, PRIORITY, PRICE, CURR) VALUES();
INSERT INTO PRICES(PRICE_LIST, BRAND_ID, START_DATE, END_DATE, PRODUCT_ID, PRICE)
VALUES(1, 1, PARSEDATETIME('2020-06-14-00.00.00', 'yyyy-MM-dd-hh.mm.ss'), PARSEDATETIME('2020-12-31-23.59.59', 'yyyy-MM-dd-hh.mm.ss'), 35455, 35.50);
INSERT INTO PRICES(PRICE_LIST, BRAND_ID, START_DATE, END_DATE, PRODUCT_ID, PRIORITY, PRICE)
VALUES(2, 1, PARSEDATETIME('2020-06-14-15.00.00', 'yyyy-MM-dd-hh.mm.ss'), PARSEDATETIME('2020-06-14-18.30.00', 'yyyy-MM-dd-hh.mm.ss'), 35455, 1, 25.45);
INSERT INTO PRICES(PRICE_LIST, BRAND_ID, START_DATE, END_DATE, PRODUCT_ID, PRIORITY, PRICE)
VALUES(3, 1, PARSEDATETIME('2020-06-15-00.00.00', 'yyyy-MM-dd-hh.mm.ss'), PARSEDATETIME('2020-06-15-11.00.00', 'yyyy-MM-dd-hh.mm.ss'), 35455, 1, 30.50);
INSERT INTO PRICES(PRICE_LIST, BRAND_ID, START_DATE, END_DATE, PRODUCT_ID, PRIORITY, PRICE)
VALUES(4, 1, PARSEDATETIME('2020-06-15-16.00.00', 'yyyy-MM-dd-hh.mm.ss'), PARSEDATETIME('2020-12-31-23.59.59', 'yyyy-MM-dd-hh.mm.ss'), 35455, 1, 38.95);

