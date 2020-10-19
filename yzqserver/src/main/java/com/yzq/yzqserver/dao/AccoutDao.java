package com.yzq.yzqserver.dao;

import com.yzq.yzqserver.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class AccoutDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> queryAccout(String accoutType, String userId) {
        String sql = "select * from accout where ACCOUT_TYPE=? and USERID=?";
        return jdbcTemplate.queryForList(sql, accoutType, userId);
    }

    public int addCost(String accoutType, String userId, String costType, String costValue) {
        String sql = "insert into bill(id,userid,accout_type,cost_type,cost) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, UUID.randomUUID().toString(), userId, accoutType, costType, costValue);
    }

    public double queryBudget(String userId, String s) {
        String sql = "select budget from budget where userid= ? & month = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId, s}, Double.class);
    }

    public int updateBudget(String userId, double budget, String month) {
        String sql = "update budget set budget= ? where userid = ? and month = ?";
        return jdbcTemplate.update(sql, budget, userId, month);
    }

    public double queryAccoutBalance(String userId, String accoutType) {
        String sql = "select accout_value from accout where userid = ? and accout_type= ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId, accoutType}, double.class);
    }

    public int updateAccoutBalance(String userId, double accoutBalance, String accoutType) {
        String sql = "uodate accout set accout_value = ? where userid = ? and accout_type = ?";
        return jdbcTemplate.update(sql, accoutBalance, userId, accoutType);
    }

    public List<Map<String, Object>> queryBillByDate(String date, String userId, String dateType, int page, int size) {
        return null;
    }

    public List<Map<String, Object>> queryBillByDay(String date, String userId, String dateType, int page, int size) {
        String sql = "select * from bill where DATE_FORMAT(CREATETIME,'%Y-%m-%d')  = DATE_FORMAT(?,'%Y-%m-%d') and userid = ?  ORDER BY CREATETIME desc limit ?,?";
        return jdbcTemplate.queryForList(sql, date, userId, page, size);
    }

    public List<Map<String, Object>> queryBillByMonth(String date, String userId, String dateType, int page, int i) {
        String sql = "select DATE_FORMAT(CREATETIME,'%Y-%m-%d')  DAY, `ID`, `USERID`, `COST`, `COST_TYPE`, `ACCOUT_TYPE`, `CREATETIME` from bill where userid = ? and DATE_FORMAT(CREATETIME,'%Y-%m')  = DATE_FORMAT(?,'%Y-%m') ORDER BY CREATETIME desc";
        return jdbcTemplate.queryForList(sql, userId, date);
    }

    public List<Map<String, Object>> queryBillByQuarter(String date, String userId, String dateType, int page, int i) {
        String sql = "select DATE_FORMAT(CREATETIME,'%Y-%m-%d')  DAY, `ID`, `USERID`, `COST`, `COST_TYPE`, `ACCOUT_TYPE`, `CREATETIME` from bill where userid = ? and QUARTER(CREATETIME)  = QUARTER(?) ORDER BY CREATETIME desc";
        return jdbcTemplate.queryForList(sql, userId, date);
    }

    public int updateConfig(String userId, String configContent, String configName) {
        String sql = "select count(1) from config where userid = ? and configName= ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{userId, configName}, Integer.class);
        int re = 0;
        if (count == 0) {
            sql = "insert into config(id,userid,config_name,config_content) values(?,?,?,?)";
            re = jdbcTemplate.update(sql, UUID.randomUUID().toString(), userId, configName, configContent);
        } else {
            sql = "update config set config_content = ? where userid= ? and configName=?";
            re = jdbcTemplate.update(sql, configContent, userId, configName);
        }
        return re;
    }

    public List<Map<String, Object>> queryCostType(String costBigType, String userId) {
        String sql = "select * from costtype where (userId = ? or userId = '0') and type like ? ORDER BY CREATETIME ASC";
        return jdbcTemplate.queryForList(sql, userId, costBigType);
    }

    public int addCostType(String costBigType, String userId, String cost_type, String cost_name) {
        String sql = "insert into costtype(id,COST_TYPE,COST_NAME,USERID, TYPE) value(?,?,?,?,?)";
        return jdbcTemplate.update(sql, UUID.randomUUID().toString(), cost_type, cost_name, userId, costBigType);
    }

    public int isAccoutExist(String userId, String accoutType) {
        String sql = "select count(*) from accout where userid=? and accout_type = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId, accoutType}, Integer.class);
    }

    public int addAccoutType(String accoutType, String userId, String accoutValue, String accoutName) {
        String sql = "insert into accout(`ID`, `ACCOUT_TYPE`, `ACCOUT_VALUE` , `ACCOUT_NAME`, `USERID`) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, UUID.randomUUID().toString(), accoutType, accoutValue, accoutName, userId);
    }

    public int updateAccout(String accoutType, String userId, String accoutValue, String accoutName) {
        String sql = "update accout set accoutValue= ? where userid=? and ACCOUT_TYPE=?";
        return jdbcTemplate.update(sql, accoutValue, userId, accoutType);
    }

    public List<Map<String, Object>> queryCost(String accoutType, String userId) {
        String sql = "select * from accout where userId=?";
        return jdbcTemplate.queryForList(sql, userId);
    }

    public List<Map<String, Object>> queryCostByaccoutType(String accoutType, String userId) {
        String sql = "select * from accout where userId=? and accoutType = ?";
        return jdbcTemplate.queryForList(sql, userId,accoutType);
    }
}
