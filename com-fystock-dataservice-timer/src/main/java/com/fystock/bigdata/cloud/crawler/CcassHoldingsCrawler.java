package com.fystock.bigdata.cloud.crawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.fystock.bigdata.cloud.entity.mysql.CapitalInfo;
import com.fystock.bigdata.cloud.entity.clickhouse.CcassHoldingsInfo;
import com.fystock.bigdata.cloud.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 爬取CCASS系统数据
 *
 * @author He.Yong
 * @since 2021-08-11 14:07:25
 */
@Slf4j
public class CcassHoldingsCrawler extends BreadthCrawler {
    private final DecimalFormat decimalFormat = new DecimalFormat("00.00");

    /**
     * 股本信息
     */
    private List<CapitalInfo> capitalInfoList = new ArrayList<>();

    /**
     * CCASS持仓信息
     */
    private List<CcassHoldingsInfo> ccassHoldingsInfoList = new ArrayList<>();

    public List<CapitalInfo> getCapitalInfoList() {
        return capitalInfoList;
    }

    public void setCapitalInfoList(List<CapitalInfo> capitalInfoList) {
        this.capitalInfoList = capitalInfoList;
    }

    public List<CcassHoldingsInfo> getCcassHoldingsInfoList() {
        return ccassHoldingsInfoList;
    }

    public void setCcassHoldingsInfoList(List<CcassHoldingsInfo> ccassHoldingsInfoList) {
        this.ccassHoldingsInfoList = ccassHoldingsInfoList;
    }

    public CcassHoldingsCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        // 正则规则设置
        this.addRegex("https://webb-site.com/ccass/*");
        // 不获取这样的格式 jpg|png|gif
        this.addRegex("-.*\\.(jpg|png|gif).*");
        this.addRegex("-.*#.*");
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        try {
            Elements tables;
            Elements trs;
            Elements tds;
            Integer partId;
            String partName;
            String stockCode;
            String stockNameEn;
            BigDecimal stockHolding;
            BigDecimal stockValue;
            String stockPercentage;
            String holdDate;

            String url = page.url();
            // 用jsoup去解析这个页面
            Document doc = page.doc();
            // CCASS参与者
            partId = Integer.parseInt(url.substring(url.indexOf("=") + 1, url.indexOf("&")));
            // 更新时间串
            String updateInfoStr = doc.getElementsByTag("h3").get(0).text();

            String updateDate = updateInfoStr.substring(updateInfoStr.indexOf("on") + 3);

            Elements nameElements = doc.getElementsByTag("h2");

            if (nameElements == null || nameElements.size() == 0) {
                nameElements = doc.getElementsByTag("h3");
            }

            if (nameElements != null && nameElements.size() > 0) {
                partName = nameElements.get(0).text().trim();
            } else {
                partName = "";
            }
            // 爬取CCASS持仓数据
            tables = doc.select("table[class=optable]");

            if (null != tables && tables.size() > 0) {
                trs = tables.get(0).select("tr");
                if (null != trs && trs.size() > 1) {
                    for (int i = 1; i < trs.size(); i++) {
                        tds = trs.get(i).getElementsByTag("td");
                        if (tds.size() != 8) {
                            log.error("*****列记录异常：part_id=" + partId + ",trs.size=" + trs.size());
                            log.error(trs.get(i).outerHtml());
                        }
                        if (tds.size() == 8) {
                            stockCode = tds.get(1).text();
                            if (stockCode.length() < 5) {
                                stockCode = '0' + stockCode;
                            }
                            stockNameEn = tds.get(2).select("a").get(0).text();
                            stockHolding = new BigDecimal(tds.get(3).text().replace(",", ""));
                            stockValue = new BigDecimal(tds.get(4).text().replace(",", ""));
                            stockPercentage = decimalFormat.format(Double.valueOf(tds.get(6).select("a").get(0).text()));
                            holdDate = tds.get(7).select("a").get(0).text();

                            CcassHoldingsInfo ccassHoldingsInfo = new CcassHoldingsInfo();
                            ccassHoldingsInfo.setParticipantId(partId);
                            ccassHoldingsInfo.setParticipantName(partName);
                            ccassHoldingsInfo.setStockCode(stockCode);
                            ccassHoldingsInfo.setStockNameEn(stockNameEn);
                            ccassHoldingsInfo.setStockHolding(stockHolding);
                            ccassHoldingsInfo.setStockValue(stockValue);
                            ccassHoldingsInfo.setStakePercentage(stockPercentage);
                            ccassHoldingsInfo.setHoldDate(holdDate);
                            ccassHoldingsInfo.setUpdateDate(updateDate);
                            ccassHoldingsInfo.setCreateTime(DateTimeUtil.getCurrentDateTime());
                            ccassHoldingsInfo.setUpdateTime(DateTimeUtil.getCurrentDateTime());

                            //设置总股本
                            String finalStockCode = stockCode;
                            Optional<CapitalInfo> capitalInfoOptional = capitalInfoList.stream().filter(o -> o.getSymbol().equals(finalStockCode)).findFirst();
                            capitalInfoOptional.ifPresent(capitalInfo -> ccassHoldingsInfo.setTotalCapital(capitalInfo.getTotalCapital()));

                            //log.info("---CCASS参与者ID：{},参与者名称: {}, 的持仓数据：{}", partId, partName, ccassHoldingsInfo);
                            ccassHoldingsInfoList.add(ccassHoldingsInfo);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("*******爬取CCASS持仓数据异常:", e);
        }
    }
}
