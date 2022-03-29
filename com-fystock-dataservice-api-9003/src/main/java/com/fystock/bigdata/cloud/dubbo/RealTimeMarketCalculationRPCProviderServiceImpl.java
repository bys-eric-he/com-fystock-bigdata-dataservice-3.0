package com.fystock.bigdata.cloud.dubbo;

import com.fystock.bigdata.cloud.model.RealTimeMarketCalculationModel;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.IRealTimeMarketCalculationRPCProviderService;
import com.fystock.bigdata.cloud.service.RealTimeMarketCalculationService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 实时行情计算结果 Dubbo对内提供的RPC接口
 *
 * @author He.Yong
 * @since 2021-09-09 10:40:28
 */
@DubboService
public class RealTimeMarketCalculationRPCProviderServiceImpl implements IRealTimeMarketCalculationRPCProviderService {

    @Resource
    @Qualifier("realTimeMarketCalculationService")
    private RealTimeMarketCalculationService realTimeMarketCalculationService;


    /**
     * 按指定交易日获取所有版块中每只股票最后一次计算结果
     *
     * @param tradeDate
     * @return
     */
    @Override
    public CommonResult<Object> getMarketDataByTradeDateForTimer(String tradeDate) {
        Map<String, List<RealTimeMarketCalculationModel>> results = realTimeMarketCalculationService.getMarketDataByTradeDateForTimer(tradeDate);
        return CommonResult.success(results, "查询数据成功, Dubbo服务处理!");
    }
}
