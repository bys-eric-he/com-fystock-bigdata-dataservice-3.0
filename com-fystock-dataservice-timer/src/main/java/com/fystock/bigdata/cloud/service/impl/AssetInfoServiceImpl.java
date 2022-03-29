package com.fystock.bigdata.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fystock.bigdata.cloud.annotation.DataSource;
import com.fystock.bigdata.cloud.entity.mysql.AssetInfo;
import com.fystock.bigdata.cloud.enums.DataSourceEnum;
import com.fystock.bigdata.cloud.mapper.msyql.AssetInfoMapper;
import com.fystock.bigdata.cloud.service.AssetInfoService;
import com.fystock.bigdata.cloud.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.session.ExecutorType;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 股票基本信息
 *
 * @author He.Yong
 * @since 2021-05-14 14:25:16
 */
@Slf4j
@Service
public class AssetInfoServiceImpl extends ServiceImpl<AssetInfoMapper, AssetInfo> implements AssetInfoService {

    @Autowired
    private AssetInfoMapper assetInfoMapper;

    /*
     @Autowired
     private SqlSessionFactory sqlSessionFactory;
    */

    /**
     * 获取所有股票信息
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceEnum.sunlineMktInfoMySql)
    public List<AssetInfo> getAllAssetInfoSourceList() {
        List<AssetInfo> assetInfos = assetInfoMapper.getAllAssetInfoSourceList();
        log.info("----从mktinfo获取股票AssetInfo数据表信息,数据条数: {} 条!-----", assetInfos.size());
        return assetInfos;
    }

    /**
     * 清空表数据
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceEnum.bigdataMarketMysql)
    public int truncateAssetInfoTable() {
        log.info("----清空股票AssetInfo数据表信息-----");
        int count = assetInfoMapper.truncateAssetInfoTable();
        log.info("----TRUNCATE TABLE方式,清空股票AssetInfo数据表信息完成,影响数: {} 行!-----", count);
        return count;
    }

    /**
     * 批量插入股票信息
     *
     * @param assetInfoList
     * @return
     */
    @Override
    @DataSource(value = DataSourceEnum.bigdataMarketMysql)
    public int insertAssetInfoBatch(List<AssetInfo> assetInfoList) {
        log.info("----批量插入股票AssetInfo数据表信息-----");
        long beginTime = System.currentTimeMillis();

        int results = 0;
        int pages = 1;
        int pageSize = 1000;
        int totalSize = assetInfoList.size();
        try {
            while (PageUtil.startAssetInfoPage(assetInfoList, pages, pageSize).size() > 0) {
                List<AssetInfo> list = PageUtil.startAssetInfoPage(assetInfoList, pages, pageSize);
                results += assetInfoMapper.insertAssetInfoBatch(list);
                log.info("----已插入第: {} 页  共: {} 条数据!----", pages, results);
                //如果当前插入的总数等于记录总数,则认为已经提取完所有数据
                if (results == totalSize) {
                    log.info("----数据分页插入完成! 共 : {} 页,  共: {} 条数据!----", pages, results);
                    break;
                }
                pages++;
            }
        } catch (Exception exception) {
            log.error("****批量插入股票AssetInfo信息异常! 异常信息：", exception);
        }
        long endTime = System.currentTimeMillis();
        log.info("----批量插入股票AssetInfo信息完成, 耗时 ：{}  毫秒, 影响行数: {} 行!----", (endTime - beginTime), results);
        return results;


        /* MyBatis批量插入几千条数据慎用foreach
         * 当表的列数较多（20+），以及一次性插入的行数较多（5000+）时，整个插入的耗时十分漫长
         * int count = assetInfoMapper.insertAssetInfoBatch(assetInfoList);
         */

        /*
        使用MyBatis Plus的批量保存方法，需要在AssetInfo 实体打上@TableName @TableField @TableId(type = IdType.AUTO)注解，
        否则会报 com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: error: can not execute. because can not find column for id from entity! 异常
        this.saveOrUpdateBatch(assetInfoList, 1000);
        int count = assetInfoList.size();
        */

        /*
         * 新获取一个模式为BATCH，自动提交为false的session
         * 如果自动提交设置为true,将无法控制提交的条数，改为最后统一提交，可能导致内存溢出
         *
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        AssetInfoMapper mapper = session.getMapper(AssetInfoMapper.class);
        int results = 0;
        try {
            for (int i = 0; i < assetInfoList.size(); i++) {
                results += mapper.insert(assetInfoList.get(i));
                if (i % 1000 == 0 || i == assetInfoList.size() - 1) {
                    //手动每1000个一提交，提交后无法回滚
                    session.commit();
                    //清理缓存，防止溢出
                    session.clearCache();
                }
            }
        } catch (Exception exception) {
            //没有提交的数据可以回滚
            session.rollback();
            log.error("----批量插入股票AssetInfo信息异常! 异常信息：", exception);
        } finally {
            session.close();
        }
        long endTime = System.currentTimeMillis();
        log.info("----批量插入股票AssetInfo信息完成, 耗时 ：{}  毫秒, 影响行数: {} 行!----", (endTime - beginTime), results);
        return results;
        */
    }
}
