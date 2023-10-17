-- 코드를 입력하세요
SELECT          DATE_FORMAT(SALES_DATE, '%Y-%m-%d')                   "SALES_DATE",
                PRODUCT_ID,
                USER_ID,
                SALES_AMOUNT
FROM        (
    SELECT      SALES_DATE, PRODUCT_ID,
                USER_ID, SALES_AMOUNT
    FROM        ONLINE_SALE
    UNION ALL
    SELECT      SALES_DATE, PRODUCT_ID,
                NULL "USER_ID",
                SALES_AMOUNT
    FROM        OFFLINE_SALE
            ) T
WHERE       SALES_DATE BETWEEN
            '2022-03-01' AND
            '2022-03-31'
ORDER BY    SALES_DATE ASC,
            PRODUCT_ID ASC,
            USER_ID ASC;