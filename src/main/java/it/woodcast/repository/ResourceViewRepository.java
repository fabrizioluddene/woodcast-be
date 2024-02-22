package it.woodcast.repository;

import it.woodcast.entity.ResourceViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceViewRepository extends JpaRepository<ResourceViewEntity, Integer> {

    @Query(value="select  row_number() OVER () as id,  " +
            "resource_id,resource_name,  " +
            "SUM(case when month = 1 then working_days else 0 end) as january,  " +
            "wc.january as january_wc,  " +
            "COALESCE(rv_january,0) rv_january,  " +
            "SUM(case when month = 2 then working_days else 0 end) as february,  " +
            "wc.february as february_wc,  " +
            "COALESCE(rv_february,0) rv_february,  " +
            "SUM(case when month = 3 then working_days else 0 end) as march,  " +
            "wc.march as march_wc,  " +
            "COALESCE(rv_march,0) rv_march,  " +
            "SUM(case when month = 4 then working_days else 0 end) as april,  " +
            "wc.april as april_wc,  " +
            "COALESCE(rv_april,0) rv_april,  " +
            "SUM(case when month = 5 then working_days else 0 end) as may,  " +
            "wc.may as may_wc,  " +
            "COALESCE(rv_may,0) rv_may,  " +
            "SUM(case when month = 6 then working_days else 0 end) as june,  " +
            "wc.june as june_wc,  " +
            "COALESCE(rv_june,0) rv_june,  " +
            "SUM(case when month = 7 then working_days else 0 end) as july,  " +
            "wc.july as july_wc,  " +
            "COALESCE(rv_july,0) rv_july,  " +
            "SUM(case when month = 8 then working_days else 0 end) as august,  " +
            "wc.august as august_wc,  " +
            "COALESCE(rv_august,0) rv_august,  " +
            "SUM(case when month = 9 then working_days else 0 end) as september,  " +
            "wc.september as september_wc,  " +
            "COALESCE(rv_september,0) rv_september,  " +
            "SUM(case when month = 10 then working_days else 0 end) as october,  " +
            "wc.october as october_wc,  " +
            "COALESCE(rv_october,0) rv_october,  " +
            "SUM(case when month = 11 then working_days else 0 end) as november,  " +
            "wc.november as november_wc,  " +
            "COALESCE(rv_november,0) rv_november,  " +
            "SUM(case when month = 12 then working_days else 0 end) as december,  " +
            "wc.december as december_wc,  " +
            "COALESCE(rv_december,0) rv_december  " +
            "from  " +
            "(  " +
            "select  " +
            "r.r_nominative as resource_name,r.r_id as resource_id,  " +
            "extract(month  " +
            "from  " +
            "month) as month,  " +
            "c.working_day as working_days,  " +
            "rv.*  " +
            "from  " +
            "calendar c  " +
            "join  " +
            "resources r on  " +
            "c.resource_entities_r_id = r.r_id  " +
            "left join resources_vacation rv on  " +
            "rv.rv_fiscal_code = r.r_fiscal_code  " +
            "and rv.rv_year = 2024  " +
            "),  " +
            "working_calendar wc  " +
            "where  " +
            "year = 2024  " +
            "group by  " +
            "resource_id,  " +
            "resource_name,  " +
            "wc.january,  " +
            "wc.february,  " +
            "wc.march,  " +
            "wc.april,  " +
            "wc.may,  " +
            "wc.june,  " +
            "wc.july,  " +
            "wc.august,  " +
            "wc.september,  " +
            "wc.october,  " +
            "wc.november,  " +
            "wc.december,  " +
            "rv_january,  " +
            "rv_april,  " +
            "rv_august,  " +
            "rv_december,  " +
            "rv_february,  " +
            "rv_july,  " +
            "rv_june,  " +
            "rv_march,  " +
            "rv_may,  " +
            "rv_november,  " +
            "rv_october,  " +
            "rv_september  ",nativeQuery = true)
    List<ResourceViewEntity> findAll();
}
