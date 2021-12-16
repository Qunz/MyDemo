package my.qunz.myDemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.qunz.myDemo.entity.AppService;
import my.qunz.myDemo.mapper.AppServiceMapper;
import my.qunz.myDemo.service.IAppServiceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 首页应用表 服务实现类
 * </p>
 *
 * @author zouzhaowei
 * @since 2021-10-15
 */
@Service
public class AppServiceServiceImpl extends ServiceImpl<AppServiceMapper, AppService> implements IAppServiceService {

}
