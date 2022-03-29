package com.fystock.bigdata.cloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.fystock.bigdata.cloud.annotation.Log4FYStock;
import com.fystock.bigdata.cloud.cache.RedisCache;
import com.fystock.bigdata.cloud.cache.RedisKey;
import com.fystock.bigdata.cloud.entity.hbase.RealTimeMarketCalculation;
import com.fystock.bigdata.cloud.mapper.RealTimeMarketCalculationMapper;
import com.fystock.bigdata.cloud.mapping.RealTimeMarketCalculationMapping;
import com.fystock.bigdata.cloud.model.RealTimeMarketCalculationCacheModel;
import com.fystock.bigdata.cloud.model.RealTimeMarketCalculationModel;
import com.fystock.bigdata.cloud.service.RealTimeMarketCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 大盘云图实时行情计算结果查询服务
 *
 * @author He.Yong
 * @since 2021-05-20 10:58:29
 */
@Slf4j
@Service("realTimeMarketCalculationService")
/*@CacheConfig(cacheNames = "REALTIME-MARKET-CALCULATION") 代替 @CacheEvict(value = "REALTIME-MARKET-CALCULATION"）*/
@CacheConfig(cacheNames = "REALTIME-MARKET-CALCULATION")
public class RealTimeMarketCalculationServiceImpl implements RealTimeMarketCalculationService {

    @Resource
    private RealTimeMarketCalculationMapper realTimeMarketCalculationMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 获取缓存中所有版块中每只股票最后一次计算结果
     *
     * @return
     */
    @Log4FYStock(value = "获取Redis缓存中所有版块中每只股票最后一次计算结果")
    @Override
    public Map<String, List<RealTimeMarketCalculationModel>> getMarketDataLatestFromCache() {

        //获取所有版块的Key
        Set<String> keys = redisCache.getRedisTemplate().keys(String.format(RedisKey.REALTIME_MARKET_CALCULATION_LATEST, "*"));

        if (ObjectUtils.isEmpty(keys)) {
            return null;
        }

        // 批量获取数据
        List<Object> myObjectListRedis = redisCache.getRedisTemplate().opsForValue().multiGet(keys);

        if (ObjectUtils.isEmpty(myObjectListRedis)) {
            return null;
        }

        List<RealTimeMarketCalculationCacheModel> realTimeMarketCalculationCacheModels = JSON.parseArray(JSON.toJSONString(myObjectListRedis), RealTimeMarketCalculationCacheModel.class);

        return this.getCacheAndAssembleList(realTimeMarketCalculationCacheModels);
    }

    /**
     * 按指定交易日获取所有版块中每只股票最后一次计算结果
     *
     * @param tradeDate
     * @return
     */
    @Log4FYStock(value = "按指定交易日获取所有版块中每只股票最后一次计算结果")
    @Cacheable(/*value = "REALTIME-MARKET-CALCULATION",*/ key = "'getMarketDataByTradeDate:' + #tradeDate")
    @Override
    public Map<String, List<RealTimeMarketCalculationModel>> getMarketDataByTradeDate(String tradeDate) {
        List<RealTimeMarketCalculation> realTimeMarketCalculations = realTimeMarketCalculationMapper.getMarketDataByTradeDate(tradeDate);

        List<RealTimeMarketCalculationModel> results = new ArrayList<>();
        realTimeMarketCalculations.forEach(o -> results.add(RealTimeMarketCalculationMapping.toModel(o)));

        return this.groupAndOrderTop10List(results, 10);
    }

    /**
     * 按指定交易日获取所有版块中每只股票最后一次计算结果-定时器调用
     *
     * @param tradeDate
     * @return
     */
    @Log4FYStock(value = "按指定交易日获取所有版块中每只股票最后一次计算结果-定时器调用")
    @Override
    public Map<String, List<RealTimeMarketCalculationModel>> getMarketDataByTradeDateForTimer(String tradeDate) {
        List<RealTimeMarketCalculation> realTimeMarketCalculations = realTimeMarketCalculationMapper.getMarketDataByTradeDate(tradeDate);

        List<RealTimeMarketCalculationModel> results = new ArrayList<>();
        realTimeMarketCalculations.forEach(o -> results.add(RealTimeMarketCalculationMapping.toModel(o)));

        return this.groupAndOrderTop10List(results, 10);
    }

    /**
     * 按指定交易日和版块代码获取每只股票最后一次计算结果
     *
     * @param tradeDate
     * @param sectionCode
     * @return
     */
    @Log4FYStock(value = "按指定交易日和版块代码获取每只股票最后一次计算结果")
    @Cacheable(/*value = "REALTIME-MARKET-CALCULATION",*/ key = "'getMarketDataByTradeDateAndSectionCode:' + #tradeDate+'sectionCode:' + #sectionCode")
    @Override
    public Map<String, List<RealTimeMarketCalculationModel>> getMarketDataByTradeDateAndSectionCode(String tradeDate, String sectionCode) {
        List<RealTimeMarketCalculation> realTimeMarketCalculations = realTimeMarketCalculationMapper.getMarketDataByTradeDateAndSectionCode(tradeDate, sectionCode);

        List<RealTimeMarketCalculationModel> results = new ArrayList<>();
        realTimeMarketCalculations.forEach(o -> results.add(RealTimeMarketCalculationMapping.toModel(o)));

        return this.groupAndOrderTop10List(results, 10);
    }

    /**
     * 按指定交易日和版块代码获取市值最大的Top N只股票最后一次计算结果
     *
     * @param tradeDate
     * @param sectionCode
     * @param top
     * @return
     */
    @Log4FYStock(value = "按指定交易日和版块代码获取市值最大的Top N只股票最后一次计算结果")
    @Cacheable(/*value = "REALTIME-MARKET-CALCULATION",*/ key = "'getMarketDataByTradeDateAndSectionCodeAndTopN:' + #tradeDate+'sectionCode:' + #sectionCode +'TOP:' + #top")
    @Override
    public Map<String, List<RealTimeMarketCalculationModel>> getMarketDataByTradeDateAndSectionCodeAndTopN(String tradeDate, String sectionCode, int top) {
        List<RealTimeMarketCalculation> realTimeMarketCalculations = realTimeMarketCalculationMapper.getMarketDataByTradeDateAndSectionCodeAndTopN(tradeDate, sectionCode, top);

        List<RealTimeMarketCalculationModel> results = new ArrayList<>();
        realTimeMarketCalculations.forEach(o -> results.add(RealTimeMarketCalculationMapping.toModel(o)));

        return this.groupAndOrderTop10List(results, top);
    }

    /**
     * 对每个版块下的数据进行分组排序,取出前10支市值最高的股票
     *
     * @param realTimeMarketCalculationModels
     * @param top
     * @return
     */
    private Map<String, List<RealTimeMarketCalculationModel>> groupAndOrderTop10List(List<RealTimeMarketCalculationModel> realTimeMarketCalculationModels, int top) {
        Map<String, List<RealTimeMarketCalculationModel>> results = new HashMap<>();
        //分组
        Map<String, List<RealTimeMarketCalculationModel>> groupByInduCodeA = realTimeMarketCalculationModels.stream().collect(Collectors.groupingBy(RealTimeMarketCalculationModel::getInduCodeA));
        //遍历分组
        for (Map.Entry<String, List<RealTimeMarketCalculationModel>> entry : groupByInduCodeA.entrySet()) {
            String key = entry.getKey();
            List<RealTimeMarketCalculationModel> entryUserList = entry.getValue();

            //对当前分组中的股票进行排序
            entryUserList.sort((o1, o2) -> Double.compare(Double.parseDouble(o2.getTotalMarketValue()), Double.parseDouble(o1.getTotalMarketValue())));

            //取出前排前10的股票,不足10支就取全部
            //subList参数说明：
            //fromIndex：用于指定新列表的起始点（包括该点）。
            //toIndex：用于指定新列表的结束点（不包括该点）。
            List<RealTimeMarketCalculationModel> temp10 = entryUserList.subList(0, entryUserList.size() < 10 ? entryUserList.size() : top);

            List<RealTimeMarketCalculationModel> top10 = new ArrayList<>(temp10);
            results.put(key, top10);
        }

        return results;
    }

    /**
     * 对每个版块下的缓存数据进行分组返回
     *
     * @param realTimeMarketCalculationCacheModel
     * @return
     */
    private Map<String, List<RealTimeMarketCalculationModel>> getCacheAndAssembleList(List<RealTimeMarketCalculationCacheModel> realTimeMarketCalculationCacheModel) {
        Map<String, List<RealTimeMarketCalculationModel>> results = new HashMap<>();

        for (RealTimeMarketCalculationCacheModel cacheModel : realTimeMarketCalculationCacheModel) {
            results.put(cacheModel.getData().getInduCode(), RealTimeMarketCalculationMapping.toModels(cacheModel));
        }
        return results;
    }
}
