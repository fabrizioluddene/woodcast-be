INSERT INTO resources_rate_param (rrp_id, rrp_rate, rrp_grade) VALUES(1, 354.97, 'B3');
INSERT INTO resources_rate_param (rrp_id, rrp_rate, rrp_grade) VALUES(15, 263.49, 'B1');
INSERT INTO resources_rate_param (rrp_id, rrp_rate, rrp_grade) VALUES(16, 302.93, 'B2');
INSERT INTO resources_rate_param (rrp_id, rrp_rate, rrp_grade) VALUES(17, 405.68, 'A1');
INSERT INTO resources_rate_param (rrp_id, rrp_rate, rrp_grade) VALUES(18, 804.33, 'D');
INSERT INTO resources_rate_param (rrp_id, rrp_rate, rrp_grade) VALUES(19, 223.56, 'C3');
INSERT INTO resources_rate_param (rrp_id, rrp_rate, rrp_grade) VALUES(20, 223.56, 'C3');
INSERT INTO resources_rate_param (rrp_id, rrp_rate, rrp_grade) VALUES(21, 199.03, 'L5A');
INSERT INTO resources_rate_param (rrp_id, rrp_rate, rrp_grade) VALUES(22, 250.00, '3Parte');
INSERT INTO resources_rate_param (rrp_id, rrp_rate, rrp_grade) VALUES(23, 275.00, '3Parte A');
INSERT INTO resources_rate_param (rrp_id, rrp_rate, rrp_grade) VALUES(24, 290.00, '3Parte B');
INSERT INTO resources_rate_param (rrp_id, rrp_rate, rrp_grade) VALUES(25, 285.00, '3Parte C');
INSERT INTO resources_rate_param (rrp_id, rrp_rate, rrp_grade) VALUES(26, 1200.00, 'C');
INSERT INTO resources_rate_param (rrp_id, rrp_rate, rrp_grade) VALUES(27, 290.00, '3Parte D');
INSERT INTO customer (c_id, c_customer_name) VALUES(1, 'Bper nuovo');
INSERT INTO customer (c_id, c_customer_name) VALUES(2, 'Intesa San Paolo');

create or replace view working_calendar as
SELECT year,
       SUM(CASE WHEN month = 1 THEN working_days ELSE 0 END) AS january,
       SUM(CASE WHEN month = 2 THEN working_days ELSE 0 END) AS february,
       SUM(CASE WHEN month = 3 THEN working_days ELSE 0 END) AS march,
       SUM(CASE WHEN month = 4 THEN working_days ELSE 0 END) AS april,
       SUM(CASE WHEN month = 5 THEN working_days ELSE 0 END) AS may,
       SUM(CASE WHEN month = 6 THEN working_days ELSE 0 END) AS june,
       SUM(CASE WHEN month = 7 THEN working_days ELSE 0 END) AS july,
       SUM(CASE WHEN month = 8 THEN working_days ELSE 0 END) AS august,
       SUM(CASE WHEN month = 9 THEN working_days ELSE 0 END) AS september,
       SUM(CASE WHEN month = 10 THEN working_days ELSE 0 END) AS october,
       SUM(CASE WHEN month = 11 THEN working_days ELSE 0 END) AS november,
       SUM(CASE WHEN month = 12 THEN working_days ELSE 0 END) AS december
FROM (
    SELECT EXTRACT(YEAR FROM date) AS year,
           EXTRACT(MONTH FROM date) AS month,
           COUNT(*) AS working_days
    FROM generate_series('2024-01-01'::date, '2050-12-31'::date, '1 day') AS gs(date)
    WHERE EXTRACT(DOW FROM date) BETWEEN 1 AND 5 -- Dal lunedì al venerdì
      AND NOT EXISTS (SELECT 1 FROM holiday_calendar WHERE hc_holiday_date = gs.date)
    GROUP BY EXTRACT(YEAR FROM date), EXTRACT(MONTH FROM date)
) AS subquery
GROUP BY year
ORDER BY year;



select  row_number() OVER () as id,
	resource_name,
	SUM(case when month = 1 then working_days else 0 end) as january,
	wc.january as january_wc,
	rv_january,
	SUM(case when month = 2 then working_days else 0 end) as february,
	wc.february as february_wc,
	rv_february,
	SUM(case when month = 3 then working_days else 0 end) as march,
	wc.march as march_wc,
	rv_march,
	SUM(case when month = 4 then working_days else 0 end) as april,
	wc.april as april_wc,
	rv_april,
	SUM(case when month = 5 then working_days else 0 end) as may,
	wc.may as may_wc,
	rv_may,
	SUM(case when month = 6 then working_days else 0 end) as june,
	wc.june as june_wc,
	rv_june,
	SUM(case when month = 7 then working_days else 0 end) as july,
	wc.july as july_wc,
	rv_july,
	SUM(case when month = 8 then working_days else 0 end) as august,
	wc.august as august_wc,
	rv_august,
	SUM(case when month = 9 then working_days else 0 end) as september,
	wc.september as september_wc,
	rv_september,
	SUM(case when month = 10 then working_days else 0 end) as october,
	wc.october as october_wc,
	rv_october,
	SUM(case when month = 11 then working_days else 0 end) as november,
	wc.november as november_wc,
	rv_november,
	SUM(case when month = 12 then working_days else 0 end) as december,
	wc.december as december_wc,
	rv_december
from
	(
	select
		r.r_nominative as resource_name,
		extract(month
	from
		month) as month,
		c.working_day as working_days,
		rv.*
	from
		calendar c
	join
    resources r on
		c.resource_entities_r_id = r.r_id
	left join resources_vacation rv on
		rv.rv_fiscal_code = r.r_fiscal_code
		and rv.rv_year = 2024
	),
	working_calendar wc
where
	year = 2024
group by
	resource_name,
	wc.january,
	wc.february,
	wc.march,
	wc.april,
	wc.may,
	wc.june,
	wc.july,
	wc.august,
	wc.september,
	wc.october,
	wc.november,
	wc.december,
	rv_january,
	rv_april,
	rv_august,
	rv_december,
	rv_february,
	rv_july,
	rv_june,
	rv_march,
	rv_may,
	rv_november,
	rv_october,
	rv_september