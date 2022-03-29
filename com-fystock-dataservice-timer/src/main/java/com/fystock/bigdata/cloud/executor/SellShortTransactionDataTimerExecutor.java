package com.fystock.bigdata.cloud.executor;

import com.alibaba.fastjson.JSON;
import com.fystock.bigdata.cloud.entity.clickhouse.SellShortTransactionDataInfo;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.ISellShortTransactionDataInfoRPCProviderService;
import com.fystock.bigdata.cloud.utils.DateTimeUtil;
import com.xxl.job.core.handler.IJobHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 定时同步股票卖空交易数据
 *
 * @author He.Yong
 * @since 2021-08-04 14:58:15
 */
@Slf4j
@Component("SellShortTransactionDataTimerExecutor")
public class SellShortTransactionDataTimerExecutor extends IJobHandler {

    @Value("${hkex.sell-short-data-url}")
    private String HKEX_URL;

    /**
     * Dubbo远程服务调用
     */
    @DubboReference
    private ISellShortTransactionDataInfoRPCProviderService iSellShortTransactionDataInfoRPCProviderService;

    /**
     * 从港交所爬取即日做空成交数据
     *
     * @return
     */
    private List<SellShortTransactionDataInfo> getSellShortDataInfo() {
        try {
            Document document = Jsoup.connect(HKEX_URL).maxBodySize(Integer.MAX_VALUE).get();
            String text = document.body().child(0).text();
            //切分行
            String[] split = text.split("\\n");
            List<String> list = Arrays.stream(split).filter(new Predicate<String>() {
                @Override
                public boolean test(String s) {
                    String trim = s.trim();
                    if (trim.contains("代號")) {
                        return true;
                    }
                    String[] split1 = trim.split("\\s+");
                    try {
                        Integer.parseInt(split1[0].trim());
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
            }).collect(Collectors.toList());

            List<SellShortTransactionDataInfo> sellShortTransactionDataInfos = new ArrayList<>();

            log.info("----->转换前的, 卖空成交数据: {} 条！", list.size());
            for (int i = 1; i < list.size(); i++) {
                log.info(list.get(i));
                SellShortTransactionDataInfo sellShortTransactionDataInfo = new SellShortTransactionDataInfo();
                String[] values = list.get(i).trim().split("\\s+");
                if (values.length == 4) {
                    String stockCode = StringUtils.strip(values[0]);
                    switch (stockCode.length()) {
                        case 1:
                            stockCode = "0000".concat(stockCode);
                            break;
                        case 2:
                            stockCode = "000".concat(stockCode);
                            break;
                        case 3:
                            stockCode = "00".concat(stockCode);
                            break;
                        case 4:
                            stockCode = "0".concat(stockCode);
                            break;
                    }
                    sellShortTransactionDataInfo.setStockCode(stockCode);
                    sellShortTransactionDataInfo.setStockName(StringUtils.strip(values[1]));
                    sellShortTransactionDataInfo.setCount(Long.valueOf(values[2].trim().replaceAll(",", "")));
                    sellShortTransactionDataInfo.setSum(new BigDecimal(values[3].trim().replaceAll(",", "")));
                    sellShortTransactionDataInfo.setTradeDate(DateTimeUtil.getCurrentDate());
                    sellShortTransactionDataInfo.setCreateDateTime(DateTimeUtil.getCurrentDateTime());
                    sellShortTransactionDataInfos.add(sellShortTransactionDataInfo);
                }
            }
            log.info("----->转换后的, 卖空成交数据: {} 条！", sellShortTransactionDataInfos.size());
            String result = JSON.toJSONString(sellShortTransactionDataInfos);
            log.info(result);
            return sellShortTransactionDataInfos;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 从港交所官方网站拉取即日卖空成交数据
     *
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        try {
            String tradeDate = DateTimeUtil.getCurrentDate();
            log.info("-----开始执行定时器任务,将交易日：{} 即日卖空成交数据写入ClickHouse!-------", tradeDate);
            List<SellShortTransactionDataInfo> sellShortTransactionDataInfos = this.getSellShortDataInfo();

            if (ObjectUtils.isEmpty(sellShortTransactionDataInfos)) {
                log.warn("----------------暂无即日卖空成交数据, 结束执行定时器任务,将交易日：{} 即日卖空成交数据写入ClickHouse!-------", tradeDate);
                return;
            }

            CommonResult<Object> response = iSellShortTransactionDataInfoRPCProviderService.insertSellShortTransactionData(sellShortTransactionDataInfos);
            if (ObjectUtils.isNotEmpty(response)) {
                if (response.getCode().equals(200)) {
                    log.info("-----即日卖空成交数据写入ClickHouse成功!-------");
                    log.info("-----返回数据包：" + response.getData());
                } else {
                    log.warn("******即日卖空成交数据写入ClickHouse失败! 失败原因：{}******", response);
                }
            } else {
                log.warn("******-定时任务接口调用, 即日卖空成交数据写入ClickHouse响应返回为空!******");
            }
        } catch (Exception exception) {
            log.error("**********即日卖空成交数据写入ClickHouse数据发生异常!***********", exception);
        }
    }
}
