package org.sale.apiRest.saleApiRest.repository.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.sale.apiRest.saleApiRest.dto.QueryWeeklySummary;
import org.sale.apiRest.saleApiRest.dto.WeeklySummary;
import org.sale.apiRest.saleApiRest.support.WhereParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WeeklySummaryRepository {
	
	@Autowired
    DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    private String getQueryWeeklySummary(QueryWeeklySummary dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT SUM(TOTAL), SALE_DATE FROM INVENTORY.SALE ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND SALE_DATE between  '"+dto.getStartDate()+"' AND  '"+dto.getEndDate()+"' ");
		sql.append(" GROUP BY SALE_DATE"); 
		 System.out.println(sql.toString());
		return sql.toString();
	}    
    
    public List<WeeklySummary> getWeeklySummary(String startDate, String endDate) {
        WhereParams params = new WhereParams();
        QueryWeeklySummary dto = new QueryWeeklySummary();
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        String sql = getQueryWeeklySummary(dto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<WeeklySummary>(WeeklySummary.class));                               
    }
}
