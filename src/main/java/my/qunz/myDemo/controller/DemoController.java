package my.qunz.myDemo.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cccc.bim.common.core.model.Res;
import my.qunz.myDemo.dataSource.DynamicDataSource;
import my.qunz.myDemo.entity.AppService;
import my.qunz.myDemo.service.IAppServiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DemoController {

    @Resource
    private IAppServiceService appServiceService;

    @GetMapping("/app")
    public Res list() {
        LambdaQueryWrapper<AppService> queryWrapper = Wrappers.lambdaQuery(AppService.class).orderByAsc(AppService::getSort);
        return Res.createBySuccess("success", appServiceService.list(queryWrapper));
    }

    @GetMapping("/app1")
    public Res list1() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://124.70.107.137:3306/bim_platform_home_test?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("Cccc@bim123");
        DynamicDataSource.dataSourcesMap.put("test", druidDataSource);
        DynamicDataSource.setDataSource("test");
//        此时数据源已切换到druidDataSource ，调用自己的业务方法即可。
//        使用完后调用DynamicDataSource.clear();重置为默认数据源。
        LambdaQueryWrapper<AppService> queryWrapper = Wrappers.lambdaQuery(AppService.class).orderByAsc(AppService::getSort);
        List<AppService> list = appServiceService.list(queryWrapper);
        DynamicDataSource.clear();
        return Res.createBySuccess("success", list);
    }

    @GetMapping("/app2")
    public Res list2() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://124.70.83.151:3306/bim_platform_home_prod?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("Cccc@bim123");
        DynamicDataSource.dataSourcesMap.put("prod", druidDataSource);
        DynamicDataSource.setDataSource("prod");
//        此时数据源已切换到druidDataSource ，调用自己的业务方法即可。
//        使用完后调用DynamicDataSource.clear();重置为默认数据源。
        LambdaQueryWrapper<AppService> queryWrapper = Wrappers.lambdaQuery(AppService.class).orderByAsc(AppService::getSort);
        List<AppService> list = appServiceService.list(queryWrapper);
        DynamicDataSource.clear();
        return Res.createBySuccess("success", list);
    }

}
